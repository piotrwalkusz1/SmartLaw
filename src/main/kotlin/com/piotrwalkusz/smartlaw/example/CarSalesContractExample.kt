package com.piotrwalkusz.smartlaw.example

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.document.Contract
import com.piotrwalkusz.smartlaw.model.document.Library
import com.piotrwalkusz.smartlaw.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.model.rule.Rule
import com.piotrwalkusz.smartlaw.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.model.rule.textformatter.SimpleRuleInvocationTextFormatter
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.TextEngineTemplate
import com.piotrwalkusz.smartlaw.template.FreeMarkerTextEngine

object CarSalesContractExample {

    val library = Library(
            id = Id("UMOWA_SPRZEDAZY_SAMOCHODU_LIBRARY", "pl.piotrwalkusz"),
            name = "Umowa sprzedaży samochodu - biblioteka",
            rules = listOf(
                    Rule(
                            id = Id("DEFINICJA_SPRZEDAJACEGO_I_KUPUJACEGO", "pl.piotrwalkusz"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                # Test
                                zawarta w ${'$'}{args.miejscowość} dnia ${'$'}{args.dzień} pomiędzy:\
                                ${'$'}{args.sprzedającyImięNazwisko}, legitymującym się dowodem osobistym\
                                o numerze ${'$'}{args.sprzedającyNumerDowoduOsobistego} wydanym przez ${'$'}{args.sprzedającyDowódOsobistWydanyPrzez}\
                                zamieszkałym w ${'$'}{args.sprzedającyMiejsceZamieszkania}, przy ul. ${'$'}{args.sprzedającyUlica}\
                                PESEL ${'$'}{args.sprzedającyPESEL} dalej Sprzedający\
                                a\
                                ${'$'}{args.kupującyImięNazwisko}, legitymującym się dowodem osobistym\
                                o numerze ${'$'}{args.kupującyNumerDowoduOsobistego} wydanym przez ${'$'}{args.kupującyDowódOsobistWydanyPrzez}\
                                zamieszkałym w ${'$'}{args.kupującyMiejsceZamieszkania}, przy ul. ${'$'}{args.kupującyUlica}\
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
                            elements = listOf()),
                    Rule(
                            id = Id("DEFINICJA_SAMOCHODU", "pl.piotrwalkusz"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                Przedmiotem niniejszym umowy jest przeniesienie przez Sprzedającego na Kupującego prawa własności\
                                pojazdu ${'$'}{args.markaModel} (marka/model),\
                                rok produkcji ${'$'}{args.rokProdukcji} nr silnika ${'$'}{args.nrSilnika}\
                                nr nadwozia ${'$'}{args.nrNadwozia} nr rejestracyjny ${'$'}{args.nrRejestracyjny}\
                                kolor ${'$'}{args.kolor} Przebieg ${'$'}{args.przebieg}
                                """.trimIndent()),
                            arguments = listOf(
                                    MetaArgument(name = "markaModel", type = Id("String")),
                                    MetaArgument(name = "rokProdukcji", type = Id("Integer")),
                                    MetaArgument(name = "nrSilnika", type = Id("String")),
                                    MetaArgument(name = "nrNadwozia", type = Id("String")),
                                    MetaArgument(name = "nrRejestracyjny", type = Id("String")),
                                    MetaArgument(name = "kolor", type = Id("String")),
                                    MetaArgument(name = "przebieg", type = Id("Integer"))
                            ),
                            elements = listOf()
                    ),
                    Rule(
                            id = Id("OSWIADCZENIE_SPRZEDAJACEGO", "pl.piotrwalkusz"),
                            content = StaticTemplate("Sprzedający oświadcza, że pojazd będący przedmiotem umowy stanowi jego własność i jest wolny od wad prawnych."),
                            elements = listOf()
                    ),
            Rule(id = Id("CENA_SAMOCHODU", "pl.piotrwalkusz"),
            content = TextEngineTemplate(type = "FreeMarked", template =
            """"
            Strony zgodnie ustalają cenę sprzedaży samochodu określonego w ${'$'}{context.getLinkToElement("")} niniejszej
            na kwotę: …....................................zł., słownie…………………………………………………………………………………………...")))
            """.trimIndent()
    )

    val contract = Contract(
            id = Id("UMOWA_SPRZEDAZY_SAMOCHODU", "pl.piotrwalkusz"),
            name = "Umowa sprzedaży samochodu",
            ruleInvocationTextFormatters = listOf(
                    SimpleRuleInvocationTextFormatter(RuleInvocation(
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
                    )),
                    SimpleRuleInvocationTextFormatter(RuleInvocation(
                            ruleId = Id("DEFINICJA_SAMOCHODU", "pl.piotrwalkusz"),
                            arguments = listOf(
                                    MetaPrimitiveValue("Audi A4"),
                                    MetaPrimitiveValue("1999"),
                                    MetaPrimitiveValue("XXXXXXXX"),
                                    MetaPrimitiveValue("YYYYYYYY"),
                                    MetaPrimitiveValue("ZZZZZZZZ"),
                                    MetaPrimitiveValue("czarny"),
                                    MetaPrimitiveValue("320000 km"))
                    ))
            )
    )
}
