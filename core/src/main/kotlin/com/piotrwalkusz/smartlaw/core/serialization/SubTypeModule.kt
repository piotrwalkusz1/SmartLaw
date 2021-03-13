package com.piotrwalkusz.smartlaw.core.serialization

import com.fasterxml.jackson.annotation.JsonTypeName
import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.jsontype.NamedType
import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import io.github.classgraph.ScanResult
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class SubTypeModule(private val prefix: String, private val parentTypes: List<KClass<*>>) : Module() {

    override fun getModuleName(): String = "SubType"

    override fun version(): Version = Version(1, 0, 0, "", "com.piotrwalkusz.smartlaw", "subtype")

    override fun setupModule(context: SetupContext) {
        context.registerSubtypes(*findJsonSubTypes().toTypedArray())
    }

    private fun findJsonSubTypes(): List<NamedType> {
        val classes: ScanResult = scanClasses()
        return parentTypes.flatMap { classes.filterJsonSubTypes(it) }
    }

    private fun scanClasses(): ScanResult = ClassGraph().enableClassInfo().whitelistPackages(prefix).scan()

    private fun ScanResult.filterJsonSubTypes(type: KClass<*>): Iterable<NamedType> =
            getClassesImplementing(type.java.name)
                    .map(ClassInfo::loadClass)
                    .map {
                        NamedType(it, it.findJsonTypeAnnotation())
                    }

    private fun Class<*>.findJsonTypeAnnotation(): String = kotlin.findAnnotation<JsonTypeName>()?.value ?: "unknown"
}