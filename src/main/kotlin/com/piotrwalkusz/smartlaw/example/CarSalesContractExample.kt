package com.piotrwalkusz.smartlaw.example

import com.piotrwalkusz.smartlaw.model.common.Id
import com.piotrwalkusz.smartlaw.model.document.Contract
import com.piotrwalkusz.smartlaw.model.document.Library
import com.piotrwalkusz.smartlaw.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.model.element.state.State
import com.piotrwalkusz.smartlaw.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.model.rule.Rule
import com.piotrwalkusz.smartlaw.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.model.rule.textformatter.IndentationRuleInvocationTextFormatter
import com.piotrwalkusz.smartlaw.model.rule.textformatter.SimpleRuleInvocationTextFormatter
import com.piotrwalkusz.smartlaw.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.model.template.TextEngineTemplate

object CarSalesContractExample {

    val library = Library(
            id = Id("UMOWA_SPRZEDAZY_SAMOCHODU_LIBRARY", "pl.piotrwalkusz"),
            name = "Umowa sprzedaży samochodu - biblioteka",
            rules = listOf(
                    Rule(
                            id = Id("DEFINICJA_SPRZEDAJACEGO_I_KUPUJACEGO", "pl.piotrwalkusz"),
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
                            elements = listOf()),
                    Rule(
                            id = Id("DEFINICJA_SAMOCHODU", "pl.piotrwalkusz"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                Przedmiotem niniejszym umowy jest przeniesienie przez Sprzedającego na Kupującego prawa własności
                                pojazdu ${'$'}{args.markaModel} (marka/model),
                                rok produkcji ${'$'}{args.rokProdukcji} nr silnika ${'$'}{args.nrSilnika}
                                nr nadwozia ${'$'}{args.nrNadwozia} nr rejestracyjny ${'$'}{args.nrRejestracyjny}
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
                            elements = listOf(State(
                                    id = StaticTemplate(Id("SAMOCHOD", "pl.piotrwalkusz")),
                                    type = StaticTemplate(DefinitionRef(StaticTemplate(Id("SAMOCHOD_TYP", "pl.piotrwalkusz")))),
                                    name = StaticTemplate("Samochód")
                            ))
                    ),
                    Rule(
                            id = Id("OSWIADCZENIE_SPRZEDAJACEGO", "pl.piotrwalkusz"),
                            content = StaticTemplate("Sprzedający oświadcza, że pojazd będący przedmiotem umowy stanowi jego własność i jest wolny od wad prawnych."),
                            elements = listOf()
                    ),
                    Rule(
                            id = Id("CENA_SAMOCHODU", "pl.piotrwalkusz"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                Strony zgodnie ustalają cenę sprzedaży samochodu określonego w ${'$'}{context.getLinkToElement("pl.piotrwalkusz.SAMOCHOD")}
                                niniejszej umowy na kwotę: ${'$'}{args.kwota} zł., słownie ${'$'}{args.kwotaSlownie} złotych"
                                """.trimIndent()),
                            arguments = listOf(
                                    MetaArgument(name = "kwota", type = Id("Integer")),
                                    MetaArgument(name = "kwotaSlownie", type = Id("String"))),
                            elements = listOf(State(
                                    id = StaticTemplate(Id("CENA_SAMOCHODU", "pl.piotrwalkusz")),
                                    type = StaticTemplate(DefinitionRef(StaticTemplate(Id("Int", "pl.piotrwalkusz")))),
                                    name = StaticTemplate("Cena samochodu")
                            ))
                    ),
                    Rule(
                            id = Id("PRZENIESIENIE_WLASNOSCI", "pl.piotrwalkusz"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                Sprzedający przenosi na rzecz Kupującego własność pojazdu określonego w ${'$'}{context.getLinkToElement("pl.piotrwalkusz.SAMOCHOD")}
                                niniejszej umowy za kwotę określoną w ${'$'}{context.getLinkToElement("pl.piotrwalkusz.CENA_SAMOCHODU")} umowy.
                                """.trimIndent()),
                            elements = listOf()),
                    Rule(
                            id = Id("SPOSOB_ZAPLATY", "pl.piotrwalkusz"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                Sprzedający oświadcza, że zapłata przez Kupującego ceny określonej w
                                ${'$'}{context.getLinkToElement("pl.piotrwalkusz.CENA_SAMOCHODU")} zostanie dokonana w następujący sposób: ${'$'}{args.sposobZaplaty}
                                """.trimIndent()),
                            arguments = listOf(MetaArgument(name = "sposobZaplaty", type = Id("String"))),
                            elements = listOf()),
                    Rule(
                            id = Id("POTWIERDZENIE_ODBIORU", "pl.piotrwalkusz"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                Kupujący potwierdza odbiór pojazdu, określonego w ${'$'}{context.getLinkToElement("pl.piotrwalkusz.SAMOCHOD")},
                                który nastąpił w momencie podpisania niniejszej umowy.
                                """.trimIndent())),
                    Rule(
                            id = Id("PRZEKAZANIE_RZECZY_SLUZACYCH_DO_KORZYSTANIA_Z_SAMOCHODU", "pl.piotrwalkusz"),
                            content = TextEngineTemplate(type = "FreeMarker", template =
                            """
                                Sprzedawca zobowiązuje się, że wraz z wydaniem przedmiotu umowy przekaże Kupującemu wszelkie posiadane przez niego rzeczy,
                                służące do korzystania z samochodu, w tym: ${'$'}{args.rzeczy} oraz niezbędne dokumenty związane z samochodem,
                                w tym dowód rejestracyjny oraz kartę pojazdu.
                            """.trimIndent()),
                            arguments = listOf(MetaArgument(name = "rzeczy", type = Id("String")))
                    ),
                    Rule(
                            id = Id("OSWIADCZENIE_KUPUJACEGO", "pl.piotrwalkusz"),
                            content = StaticTemplate("Kupujący oświadcza, że stan techniczny pojazdu jest mu znany i nie wnosi do niego zastrzeżeń")
                    ),
                    Rule(
                            id = Id("OBCIAZENIE_KOSZTAMI_TRANZAKCYJNYMI", "pl.piotrwalkusz"),
                            content = StaticTemplate("Strony ustaliły, że wszelkiego rodzaju koszty transakcji wynikające z realizacji ustaleń niniejszej umowy, w szczególności podatek od czynności cywilnoprawnych obciążają Kupującego.")
                    ),
                    Rule(
                            id = Id("ROZTRZYGANIE_SPRAW_NIEUREGOLOWANYCH", "pl.piotrwalkusz"),
                            content = StaticTemplate("W sprawach nieuregulowanych w niniejszej umowie zastosowanie mają obowiązujące w tym zakresie przepisy kodeksu cywilnego.")
                    ),
                    Rule(
                            id = Id("ZMIANA_UMOWY", "pl.piotrwalkusz"),
                            content = StaticTemplate("Wszelkie zmiany niniejszej umowy wymagają dla swej ważności formy pisemnej pod rygorem nieważności.")
                    ),
                    Rule(
                            id = Id("KOPIE_UMOWY", "pl.piotrwalkusz"),
                            content = StaticTemplate("Niniejszą umowę sporządzono w dwóch jednobrzmiących egzemplarzach, po jednym dla każdej ze stron.")
                    )
            ))

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
                    IndentationRuleInvocationTextFormatter(listOf(SimpleRuleInvocationTextFormatter(RuleInvocation(
                            ruleId = Id("DEFINICJA_SAMOCHODU", "pl.piotrwalkusz"),
                            arguments = listOf(
                                    MetaPrimitiveValue("Audi A4"),
                                    MetaPrimitiveValue("1999"),
                                    MetaPrimitiveValue("XXXXXXXX"),
                                    MetaPrimitiveValue("YYYYYYYY"),
                                    MetaPrimitiveValue("ZZZZZZZZ"),
                                    MetaPrimitiveValue("czarny"),
                                    MetaPrimitiveValue("320000 km"))
                    )))),
                    IndentationRuleInvocationTextFormatter(listOf(SimpleRuleInvocationTextFormatter(RuleInvocation(
                            ruleId = Id("OSWIADCZENIE_SPRZEDAJACEGO", "pl.piotrwalkusz")
                    )))),
                    IndentationRuleInvocationTextFormatter(listOf(SimpleRuleInvocationTextFormatter(RuleInvocation(
                            ruleId = Id("CENA_SAMOCHODU", "pl.piotrwalkusz"),
                            arguments = listOf(
                                    MetaPrimitiveValue("10000"),
                                    MetaPrimitiveValue("dziesięć tysięcy"))
                    )))),
                    IndentationRuleInvocationTextFormatter(listOf(
                            SimpleRuleInvocationTextFormatter(RuleInvocation(
                                    ruleId = Id("PRZENIESIENIE_WLASNOSCI", "pl.piotrwalkusz")
                            )),
                            SimpleRuleInvocationTextFormatter(RuleInvocation(
                                    ruleId = Id("SPOSOB_ZAPLATY", "pl.piotrwalkusz"),
                                    arguments = listOf(MetaPrimitiveValue("gotówka"))
                            )),
                            SimpleRuleInvocationTextFormatter(RuleInvocation(
                                    ruleId = Id("POTWIERDZENIE_ODBIORU", "pl.piotrwalkusz")
                            )),
                            SimpleRuleInvocationTextFormatter(RuleInvocation(
                                    ruleId = Id("PRZEKAZANIE_RZECZY_SLUZACYCH_DO_KORZYSTANIA_Z_SAMOCHODU", "pl.piotrwalkusz"),
                                    arguments = listOf(MetaPrimitiveValue("dwie sztuki kluczyków"))
                            )))),
                    IndentationRuleInvocationTextFormatter(listOf(SimpleRuleInvocationTextFormatter(RuleInvocation(
                            ruleId = Id("OSWIADCZENIE_KUPUJACEGO", "pl.piotrwalkusz")
                    )))),
                    IndentationRuleInvocationTextFormatter(listOf(SimpleRuleInvocationTextFormatter(RuleInvocation(
                            ruleId = Id("OBCIAZENIE_KOSZTAMI_TRANZAKCYJNYMI", "pl.piotrwalkusz")
                    )))),
                    IndentationRuleInvocationTextFormatter(listOf(
                            SimpleRuleInvocationTextFormatter(RuleInvocation(
                                    ruleId = Id("ROZTRZYGANIE_SPRAW_NIEUREGOLOWANYCH", "pl.piotrwalkusz")
                            )),
                            SimpleRuleInvocationTextFormatter(RuleInvocation(
                                    ruleId = Id("ZMIANA_UMOWY", "pl.piotrwalkusz")
                            )),
                            SimpleRuleInvocationTextFormatter(RuleInvocation(
                                    ruleId = Id("KOPIE_UMOWY", "pl.piotrwalkusz")
                            ))))
            )
    )
}
