package com.piotrwalkusz.betterlaw

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.piotrwalkusz.betterlaw.model.common.Id
import com.piotrwalkusz.betterlaw.model.element.definition.Definition

fun main() {
//    val coreLibrary = Library().apply {
//        rules = listOf(
//                CoreDefinitionRule().apply { id = "uint" },
//                CoreDefinitionRule().apply { id = "bool" },
//                CoreDefinitionRule().apply { id = "address" },
//                CoreDefinitionRule().apply { id = "bytes32" },
//                CoreDefinitionRule().apply { id = "uint" },
//                DefinitionRule().apply { id = "Asset"; name = "Asset" },
//                DefinitionRule().apply { id = "CountableAsset"; name = "Countable asset"; extends = "Asset" },
//                DefinitionRule().apply { id = "AssetCounter"; name = "Asset counter"; arguments = listOf(RuleArgument().apply { name = "Asset"; type = GenericRuleArgumentType().apply { baseType = "Countable asset" } }) },
//                DefinitionRule().apply { id = "CountableAssetOwner"; name = "Countable asset owner"; arguments = listOf(RuleArgument().apply { name = "Asset"; type = GenericRuleArgumentType().apply { baseType = "Countable asset" } }) }
//        )
//        ruleInvocations = listOf(
//                RuleInvocation().apply { rule = "uint" },
//                RuleInvocation().apply { rule = "bool" },
//                RuleInvocation().apply { rule = "address" },
//                RuleInvocation().apply { rule = "bytes32" },
//                RuleInvocation().apply { rule = "uint" },
//                RuleInvocation().apply { rule = "asset" },
//                RuleInvocation().apply { rule = "countableAsset" }
//        )
//    }

//    val votingLibrary = Library().apply {
//        rules = listOf(
//                TypeDefinitionRule().apply {
//                    id = "Voter"
//                    typeId = "Voter"
//                },
//                TypeDefinitionRule().apply {
//                    id = "Proposal"
//                    typeId = "Proposal"
//                    properties = listOf(Property().apply { name = "name"; type = PropertyType().apply { name = "bytes32" } })
//                },
//                StateRule().apply {
//                    id = "chairperson"
//                    public = true
//                    property = Property().apply { name = "chairperson"; type = PropertyType().apply { name = "address" } }
//                },
//                StateRule().apply {
//                    id = "proposals"
//                    public = true
//                    property = Property().apply { name = "proposals"; type = PropertyType().apply { name = "array"; arguments = listOf("Proposal") } }
//                }
//        )
//    }
//
//    val contract = Contract().apply {
//        id = "BALLOT"
//        namespace = "pl.piotrwalkusz"
//        name = "Ballot"
//        description = "Voting with delegation"
//    }

//    val coreLibrary = Library(
//            id = Id("core", "com.betterlaw"),
//            name = "Core",
//            description = "Basic definitions",
//            rules = listOf(
//                    DefinitionRule(
//                            id = Id("asset"),
//                            textTemplate = TextEngineTemplate("static", "Asset is something that can be possessed"),
//                            type = TypeDefinition(Id("Asset"))),
//                    DefinitionRule(
//                            id = Id("countableAsset"),
//                            textTemplate = TextEngineTemplate("static", "Countable asset is asset that has denomination"),
//                            type = TypeDefinition(
//                                    id = Id("CountableAsset"),
//                                    parameters = listOf(GenericParameter("T", TypeDefinitionRef(Id("Asset")))),
//                                    baseTypes = listOf(TypeDefinitionRef(Id("Asset"))))),
//                    DefinitionRule(
//                            id = Id("countableAssetCount"),
//                            textTemplate = TextEngineTemplate("static", "Countable asset count is count of countable asset"),
//                            type = TypeDefinition(
//                                    id = Id("CountableAssetCount"),
//                                    parameters = listOf(GenericParameter("T", TypeDefinitionRef(Id("CountableAsset")))),
//                                    properties = listOf(Property("count", TypeDefinitionRef(Id("uint")), "Count of asset")))),
//                    DefinitionRule(
//                            id = Id("countableAssetOwner"),
//                            textTemplate = TextEngineTemplate("static", "Countable asset owner is entity that can own countable asset"),
//                            type = TypeDefinition(
//                                    id = Id("CountableAssetOwner"),
//                                    parameters = listOf(GenericParameter("T", TypeDefinitionRef(Id("CountableAsset")))),
//                                    properties = listOf(Property("wallet", TypeDefinitionRef(Id("CountableAssetCount")), "Count of asset")))),
//                    DefinitionRule(
//                            id = Id("countableAssetDelegator"),
//                            textTemplate = TextEngineTemplate("static", "Countable asset delegator is entity that can send all own current and future assets to other entity"),
//                            type = TypeDefinition(
//                                    id = Id("CountableAssetDelegator"),
//                                    parameters = listOf(GenericParameter("T", TypeDefinitionRef(Id("CountableAsset")))),
//                                    properties = listOf(Property("delegatedTo", PointerType(TypeDefinitionRef(Id("countableAssetOwner"), listOf(GenericType("T")))), "Where asset should be delegated")))))),
//
//
//    var votingLibrary = Library(
//            id = Id("voting", namespace = "com.piotrwalkusz"),
//            name = "Voting",
//            description = "Collection of utilities to creating voting contracts")
//
//    var votingContract = Contract(
//            id = Id("votingContractExample", namespace = "com.piotrwalkusz"),
//            name = "Voting with delegate",
//            ruleInvocations = listOf(
//                    InlineRuleInvocation(DefinitionRule(
//                            id = Id("voter"),
//                            textTemplate = TextEngineTemplate("static", ""),
//                            type = TypeDefinition(
//                                    id = Id("Voter"),
//                                    baseTypes = listOf(
//                                            TypeDefinitionRef(Id("countableAssetDelegator"))
//                                    )
//                            )
//                    ))
//            )
//    )

    val xmlMapper = XmlMapper(JacksonXmlModule().apply {
        setDefaultUseWrapper(false)
    }).registerKotlinModule().apply {
        propertyNamingStrategy = PropertyNamingStrategy.UPPER_CAMEL_CASE
    }

    val strObject = xmlMapper.writeValueAsString(Definition(id = Id(""), name = ""))

    println(strObject)

    xmlMapper.readValue(strObject, Definition::class.java)
}
