package com.piotrwalkusz.smartlaw

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.document.Library
import com.piotrwalkusz.smartlaw.model.element.`interface`.Interface
import com.piotrwalkusz.smartlaw.model.element.`interface`.InterfaceProperty
import com.piotrwalkusz.smartlaw.model.element.`interface`.InterfacePropertyType
import com.piotrwalkusz.smartlaw.model.element.common.GenericParameter
import com.piotrwalkusz.smartlaw.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.model.element.common.type.GenericType
import com.piotrwalkusz.smartlaw.model.element.definition.Definition
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate

fun main() {

    val coreLibrary = Library(
            id = Id("Core"),
            name = "Core",
            elements = listOf(
                    Definition(
                            id = StaticTemplate(Id("Asset")),
                            name = StaticTemplate("Asset")),
                    Interface(
                            id = StaticTemplate(Id("AssetDelegator")),
                            name = StaticTemplate("AssetDelegator"),
                            properties = StaticTemplate(listOf(
                                    InterfaceProperty(
                                            name = StaticTemplate("count"),
                                            propertyType = StaticTemplate(InterfacePropertyType.Getter),
                                            type = StaticTemplate(DefinitionRef(StaticTemplate(Id("uint"))))),
                                    InterfaceProperty(
                                            name = StaticTemplate("count"),
                                            propertyType = StaticTemplate(InterfacePropertyType.Setter),
                                            type = StaticTemplate(DefinitionRef(StaticTemplate(Id("uint"))))),
                                    InterfaceProperty(
                                            name = StaticTemplate("delegatedTo"),
                                            propertyType = StaticTemplate(InterfacePropertyType.Getter),
                                            type = StaticTemplate(DefinitionRef(
                                                    definition = StaticTemplate(Id("Id")),
                                                    parameters = StaticTemplate(listOf(GenericType(StaticTemplate("R"))))))),
                                    InterfaceProperty(
                                            name = StaticTemplate("delegatedTo"),
                                            propertyType = StaticTemplate(InterfacePropertyType.Setter),
                                            type = StaticTemplate(DefinitionRef(
                                                    definition = StaticTemplate(Id("Id")),
                                                    parameters = StaticTemplate(listOf(GenericType(StaticTemplate("R"))))))))),
                            parameters = StaticTemplate(listOf(
                                    GenericParameter(
                                            name = StaticTemplate("T"),
                                            baseType = StaticTemplate(DefinitionRef(StaticTemplate(Id("Asset"))))),
                                    GenericParameter(
                                            name = StaticTemplate("R"),
                                            baseType = StaticTemplate(DefinitionRef(
                                                    definition = StaticTemplate(Id("Collection")),
                                                    parameters = StaticTemplate(listOf(
                                                            DefinitionRef(
                                                                    definition = StaticTemplate(Id("AssetDelegator")),
                                                                    parameters = StaticTemplate(listOf(
                                                                            GenericType(StaticTemplate("T")),
                                                                            GenericType(StaticTemplate("R")))))))))))))))

    val xmlMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule().apply {
        propertyNamingStrategy = PropertyNamingStrategy.UPPER_CAMEL_CASE
    }

    val strObject = xmlMapper.writeValueAsString(coreLibrary);

    println(strObject)

    xmlMapper.readValue(strObject, Library::class.java)
}
