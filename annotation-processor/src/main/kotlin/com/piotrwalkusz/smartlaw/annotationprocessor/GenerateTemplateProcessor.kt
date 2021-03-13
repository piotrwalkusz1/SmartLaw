package com.piotrwalkusz.smartlaw.annotationprocessor

import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import java.io.File
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.type.TypeMirror

@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions(GenerateTemplateProcessor.KAPT_KOTLIN_GENERATED_OPTION_NAME)
class GenerateTemplateProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    private data class Parameter(val name: String, val type: TypeName)

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(GenerateTemplate::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment?): Boolean {
        roundEnvironment?.getElementsAnnotatedWith(GenerateTemplate::class.java)?.forEach { element ->
            generateTemplate(element as TypeElement)
        }
        return true
    }

    private fun generateTemplate(modelClass: TypeElement) {
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]
        val packageName = processingEnv.elementUtils.getPackageOf(modelClass).toString()
        val modelClassName = modelClass.simpleName.toString()
        val templateClassName = "${modelClassName}Template"
        val parameters = getParameters(modelClass)

        val fileSpec = FileSpec.builder(packageName, templateClassName)
                .addType(TypeSpec.classBuilder(templateClassName)
                        .addAnnotation(AnnotationSpec.builder(ClassName("com.fasterxml.jackson.annotation", "JsonTypeName"))
                                .addMember("\"$templateClassName\"")
                                .build())
                        .addModifiers(KModifier.DATA)
                        .addSuperinterface(getBaseClass(packageName, modelClassName))
                        .primaryConstructor(generateConstructor(parameters))
                        .addProperties(parameters.map { PropertySpec.builder(it.name, it.type).initializer(it.name).build() })
                        .addFunction(generateGetValueFunction(modelClass.asClassName(), parameters))
                        .build())
                .build()

        fileSpec.writeTo(File(kaptKotlinGeneratedDir, "$templateClassName.kt"))
    }

    private fun getBaseClass(packageName: String, modelClassName: String): TypeName {
        return ClassName("com.piotrwalkusz.smartlaw.core.model.template", "ComplexTemplate")
                .parameterizedBy(ClassName(packageName, modelClassName))
    }

    private fun getParameters(modelClass: TypeElement): List<Parameter> {
        return getFirstConstructor(modelClass).parameters.map {
            val nullable = it.getAnnotation(org.jetbrains.annotations.NotNull::class.java) == null && !it.asType().kind.isPrimitive
            Parameter(
                    name = it.simpleName.toString(),
                    type = convertTypeToTemplate(it.asType().asTypeName().copy(nullable = nullable))
            )
        }
    }

    private fun convertTypeToTemplate(type: TypeName): TypeName {
        return ClassName("com.piotrwalkusz.smartlaw.core.model.template", "Template")
                .parameterizedBy(mapJavaTypeToKotlinType(type))
    }

    private fun getFirstConstructor(typeElement: TypeElement): ExecutableElement {
        return typeElement.enclosedElements.first { it.kind === ElementKind.CONSTRUCTOR } as ExecutableElement
    }

    private fun generateConstructor(parameters: List<Parameter>): FunSpec {
        val constructorSpec = FunSpec.constructorBuilder()
        parameters.forEach {
            constructorSpec.addParameter(ParameterSpec.builder(it.name, it.type).build())
        }

        return constructorSpec.build()
    }

    private fun generateGetValueFunction(modelClass: ClassName, parameters: List<Parameter>): FunSpec {
        return FunSpec.builder("getValue")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter("templateProcessor", ClassName("com.piotrwalkusz.smartlaw.core.model.template", "ComplexTemplate.TemplateProcessor"))
                .returns(modelClass)
                .addCode("return ${modelClass.simpleName}(\n")
                .addCode(parameters.map { it.name }.joinToString(",\n") { "  $it = templateProcessor.processTemplate($it)" })
                .addCode("\n)")
                .build()
    }

    private fun mapJavaTypeToKotlinType(javaType: TypeName): TypeName {
        val nullable = javaType.isNullable
        val javaTypeName = javaType.copy(nullable = false).toString()
        if (javaTypeName == "java.lang.String") {
            return String::class.asTypeName().copy(nullable = nullable)
        }
        if (javaTypeName == "java.lang.Integer") {
            return Int::class.asTypeName().copy(nullable = nullable)
        }
        if (javaType is ParameterizedTypeName && javaType.rawType == ClassName("java.util", "List")) {
            return List::class.asClassName().parameterizedBy(mapJavaTypeToKotlinType(javaType.typeArguments[0]))
        }
        if (javaType is ParameterizedTypeName && javaType.rawType == ClassName("java.util", "Set")) {
            return Set::class.asClassName().parameterizedBy(mapJavaTypeToKotlinType(javaType.typeArguments[0]))
        }
        return javaType
    }
}