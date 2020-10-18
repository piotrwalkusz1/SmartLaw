package com.piotrwalkusz.smartlaw.xml

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object XmlMapperProvider {

    fun getXmlMapper(): XmlMapper {
        return XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule().apply {
            propertyNamingStrategy = PropertyNamingStrategy.UPPER_CAMEL_CASE
            setSerializationInclusion(JsonInclude.Include.ALWAYS)
        } as XmlMapper
    }
}