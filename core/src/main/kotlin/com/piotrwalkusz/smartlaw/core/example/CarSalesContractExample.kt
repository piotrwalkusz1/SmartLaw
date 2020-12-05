package com.piotrwalkusz.smartlaw.core.example

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.document.Contract
import com.piotrwalkusz.smartlaw.core.model.document.Library
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.state.State
import com.piotrwalkusz.smartlaw.core.model.meta.MetaArgument
import com.piotrwalkusz.smartlaw.core.model.meta.MetaPrimitiveValue
import com.piotrwalkusz.smartlaw.core.model.rule.Rule
import com.piotrwalkusz.smartlaw.core.model.rule.RuleInvocation
import com.piotrwalkusz.smartlaw.core.model.presentation.IndentationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.presentation.RuleInvocationPresentationElement
import com.piotrwalkusz.smartlaw.core.model.template.StaticTemplate
import com.piotrwalkusz.smartlaw.core.model.template.TextEngineTemplate

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
                                    MetaArgument(name = "miejscowość", type = Id("String"), displayName = "Miejscowość"),
                                    MetaArgument(name = "dzień", type = Id("LocalDate"), displayName = "Dzień"),
                                    MetaArgument(name = "sprzedającyImięNazwisko", type = Id("String"), displayName = "Imię i nazwisko sprzedającego"),
                                    MetaArgument(name = "sprzedającyNumerDowoduOsobistego", type = Id("String"), displayName = "Numer dowodu osobistego sprzedającego"),
                                    MetaArgument(name = "sprzedającyDowódOsobistWydanyPrzez", type = Id("String"), displayName = "Wystawca dowodu osobistego sprzedającego"),
                                    MetaArgument(name = "sprzedającyMiejsceZamieszkania", type = Id("String"), displayName = "Adres sprzedającego: miesjcowość"),
                                    MetaArgument(name = "sprzedającyUlica", type = Id("String"), displayName = "Adres sprzedającego: ulica"),
                                    MetaArgument(name = "sprzedającyPESEL", type = Id("String"), displayName = "PESEL sprzedającego"),
                                    MetaArgument(name = "kupującyImięNazwisko", type = Id("String"), displayName = "Imię i nazwisko kupującego"),
                                    MetaArgument(name = "kupującyNumerDowoduOsobistego", type = Id("String"), displayName = "Numer dowodu osobistego kupującego"),
                                    MetaArgument(name = "kupującyDowódOsobistWydanyPrzez", type = Id("String"), displayName = "Wystawca dowodu osobistego kupującego"),
                                    MetaArgument(name = "kupującyMiejsceZamieszkania", type = Id("String"), displayName = "Adres kupującego: miesjcowość"),
                                    MetaArgument(name = "kupującyUlica", type = Id("String"), displayName = "Adres kupującego: ulica"),
                                    MetaArgument(name = "kupującyPESEL", type = Id("String"), displayName = "PESEL kupującego")
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
                                kolor ${'$'}{args.kolor} Przebieg ${'$'}{args.przebieg} km
                                """.trimIndent()),
                            arguments = listOf(
                                    MetaArgument(name = "markaModel", type = Id("String"), displayName = "Marka i model"),
                                    MetaArgument(name = "rokProdukcji", type = Id("Integer"), displayName = "Rok produkcji"),
                                    MetaArgument(name = "nrSilnika", type = Id("String"), displayName = "Numer silnika"),
                                    MetaArgument(name = "nrNadwozia", type = Id("String"), displayName = "Numer nadwozia"),
                                    MetaArgument(name = "nrRejestracyjny", type = Id("String"), displayName = "Numer rejestracyjny"),
                                    MetaArgument(name = "kolor", type = Id("String"), displayName = "Kolor"),
                                    MetaArgument(name = "przebieg", type = Id("Integer"), displayName = "Przebieg")
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
                                niniejszej umowy na kwotę: ${'$'}{args.kwota?string["000.00"]} zł., słownie ${'$'}{args.kwotaSlownie} złotych
                                """.trimIndent()),
                            arguments = listOf(
                                    MetaArgument(name = "kwota", type = Id("Integer"), displayName = "Kwota"),
                                    MetaArgument(name = "kwotaSlownie", type = Id("String"), displayName = "Kwota - słownie")),
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
                            arguments = listOf(MetaArgument(name = "sposobZaplaty", type = Id("String"), displayName = "Sposób zapłaty")),
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
                    )),
                    IndentationPresentationElement(listOf(RuleInvocationPresentationElement(RuleInvocation(
                            ruleId = Id("DEFINICJA_SAMOCHODU", "pl.piotrwalkusz"),
                            arguments = listOf(
                                    MetaPrimitiveValue("Audi A4"),
                                    MetaPrimitiveValue("1999"),
                                    MetaPrimitiveValue("XXXXXXXX"),
                                    MetaPrimitiveValue("YYYYYYYY"),
                                    MetaPrimitiveValue("ZZZZZZZZ"),
                                    MetaPrimitiveValue("czarny"),
                                    MetaPrimitiveValue("320000"))
                    )))),
                    IndentationPresentationElement(listOf(RuleInvocationPresentationElement(RuleInvocation(
                            ruleId = Id("OSWIADCZENIE_SPRZEDAJACEGO", "pl.piotrwalkusz")
                    )))),
                    IndentationPresentationElement(listOf(RuleInvocationPresentationElement(RuleInvocation(
                            ruleId = Id("CENA_SAMOCHODU", "pl.piotrwalkusz"),
                            arguments = listOf(
                                    MetaPrimitiveValue("10000"),
                                    MetaPrimitiveValue("dziesięć tysięcy"))
                    )))),
                    IndentationPresentationElement(listOf(
                            RuleInvocationPresentationElement(RuleInvocation(
                                    ruleId = Id("PRZENIESIENIE_WLASNOSCI", "pl.piotrwalkusz")
                            )),
                            RuleInvocationPresentationElement(RuleInvocation(
                                    ruleId = Id("SPOSOB_ZAPLATY", "pl.piotrwalkusz"),
                                    arguments = listOf(MetaPrimitiveValue("gotówka"))
                            )),
                            RuleInvocationPresentationElement(RuleInvocation(
                                    ruleId = Id("POTWIERDZENIE_ODBIORU", "pl.piotrwalkusz")
                            )),
                            RuleInvocationPresentationElement(RuleInvocation(
                                    ruleId = Id("PRZEKAZANIE_RZECZY_SLUZACYCH_DO_KORZYSTANIA_Z_SAMOCHODU", "pl.piotrwalkusz"),
                                    arguments = listOf(MetaPrimitiveValue("dwie sztuki kluczyków"))
                            )))),
                    IndentationPresentationElement(listOf(RuleInvocationPresentationElement(RuleInvocation(
                            ruleId = Id("OSWIADCZENIE_KUPUJACEGO", "pl.piotrwalkusz")
                    )))),
                    IndentationPresentationElement(listOf(RuleInvocationPresentationElement(RuleInvocation(
                            ruleId = Id("OBCIAZENIE_KOSZTAMI_TRANZAKCYJNYMI", "pl.piotrwalkusz")
                    )))),
                    IndentationPresentationElement(listOf(
                            RuleInvocationPresentationElement(RuleInvocation(
                                    ruleId = Id("ROZTRZYGANIE_SPRAW_NIEUREGOLOWANYCH", "pl.piotrwalkusz")
                            )),
                            RuleInvocationPresentationElement(RuleInvocation(
                                    ruleId = Id("ZMIANA_UMOWY", "pl.piotrwalkusz")
                            )),
                            RuleInvocationPresentationElement(RuleInvocation(
                                    ruleId = Id("KOPIE_UMOWY", "pl.piotrwalkusz")
                            ))))
            )
    )
}
