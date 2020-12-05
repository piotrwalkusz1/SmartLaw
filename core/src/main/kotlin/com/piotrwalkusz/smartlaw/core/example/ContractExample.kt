package com.piotrwalkusz.smartlaw.core.example

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.document.Contract
import com.piotrwalkusz.smartlaw.core.model.document.Library
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.template.TextEngineTemplate

object ContractExample {

    val library = Library(
            id = Id("EAMPLE_LIBRARY", "pl.piotrwalkusz.example"),
            name = "Przykładowa biblioteka",
            rules = listOf(
                    Rule(
                            id = Id("", "pl.piotrwalkusz.example"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                zawarta w ${'$'}{args.miejscowość} dnia ${'$'}{args.dzień} pomiędzy:
                                ${'$'}{args.sprzedającyImięNazwisko}, legitymującym się dowodem osobistym
                                o numerze ${'$'}{args.sprzedającyNumerDowoduOsobistego} wydanym przez ${'$'}{args.sprzedającyDowódOsobistWydanyPrzez}
                                zamieszkałym w ${'$'}{args.sprzedającyMiejsceZamieszkania}, przy ul. ${'$'}{args.sprzedającyUlica}
                                PESEL ${'$'}{args.sprzedającyPESEL} dalej Sprzedający
                                a
                                ${'$'}{args.kupującyImięNazwisko}, legitymującym się dowodem osobistym
                                o numerze ${'$'}{args.kupującyNumerDowoduOsobistego} wydanym przez ${'$'}{args.kupującyDowódOsobistWydanyPrzez}
                                zamieszkałym w ${'$'}{args.kupującyMiejsceZamieszkania}, przy ul. ${'$'}{args.kupującyUlica}
                                PESEL ${'$'}{args.kupującyPESEL} dalej Kupujący
                                """.trimIndent()),
                            arguments = listOf(
                                    MetaArgument(name = "miejscowość", type = Id("String")),
                                    MetaArgument(name = "dzień", type = Id("LocalDate")),
                                    MetaArgument(name = "sprzedającyImięNazwisko", type = Id("String")),
                                    MetaArgument(name = "sprzedającyNumerDowoduOsobistego", type = Id("String")),
                                    MetaArgument(name = "sprzedającyDowódOsobistWydanyPrzez", type = Id("String")),
                                    MetaArgument(name = "sprzedającyMiejsceZamieszkania", type = Id("String")),
                                    MetaArgument(name = "sprzedającyUlica", type = Id("String")),
                                    MetaArgument(name = "sprzedającyPESEL", type = Id("String")),
                                    MetaArgument(name = "kupującyImięNazwisko", type = Id("String")),
                                    MetaArgument(name = "kupującyNumerDowoduOsobistego", type = Id("String")),
                                    MetaArgument(name = "kupującyDowódOsobistWydanyPrzez", type = Id("String")),
                                    MetaArgument(name = "kupującyMiejsceZamieszkania", type = Id("String")),
                                    MetaArgument(name = "kupującyUlica", type = Id("String")),
                                    MetaArgument(name = "kupującyPESEL", type = Id("String"))
                            ),
                            elements = listOf())
            ))

    val contract = Contract(
            id = Id("UMOWA_SPRZEDAZY_SAMOCHODU", "pl.piotrwalkusz"),
            name = "Umowa sprzedaży samochodu",
            presentationElements = listOf(
                    RuleInvocationPresentationElement(RuleInvocation(
                            ruleId = Id("DEFINICJA_SPRZEDAJACEGO_I_KUPUJACEGO", "pl.piotrwalkusz"),
                            arguments = listOf(
                                    MetaPrimitiveValue("Gdańsk"),
                                    MetaPrimitiveValue("2020-10-10"),
                                    MetaPrimitiveValue("Jan Kowalski"),
                                    MetaPrimitiveValue("CAG 123456"),
                                    MetaPrimitiveValue("Prezydent Miasta Warszawa"),
                                    MetaPrimitiveValue("Warszawa"),
                                    MetaPrimitiveValue("Długa"),
                                    MetaPrimitiveValue("12345678901"),
                                    MetaPrimitiveValue("Sławomir Nowak"),
                                    MetaPrimitiveValue("CAG 234567"),
                                    MetaPrimitiveValue("Prezydent Miasta Kraków"),
                                    MetaPrimitiveValue("Kraków"),
                                    MetaPrimitiveValue("Krótka"),
                                    MetaPrimitiveValue("23456789012"))
                    ))
            )
    )
}