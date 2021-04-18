package com.piotrwalkusz.smartlaw.service.example

import com.piotrwalkusz.smartlaw.core.model.common.Id
import com.piotrwalkusz.smartlaw.core.model.document.LegalAct
import com.piotrwalkusz.smartlaw.core.model.element.common.type.DefinitionRef
import com.piotrwalkusz.smartlaw.core.model.element.definition.Definition
import com.piotrwalkusz.smartlaw.core.model.element.definition.DefinitionProperty
import com.piotrwalkusz.smartlaw.core.model.element.requirement.Requirement
import com.piotrwalkusz.smartlaw.core.model.element.requirement.StateRequirementImplementationValidator

object CivilCodeExample {

    val legalAct = LegalAct(
            id = Id("KodeksCywilny", "pl.prawopolskie"),
            name = "Kodeks cywilny",
            elements = listOf(
                    Requirement(
                            id = Id("UmowaKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.wymagania"),
                            annotations = listOf(),
                            parentRequirement = null,
                            requiredElementsCodes = listOf("Sprzedający", "Kupujący", "Cena", "Przemiot umowy kupna sprzedaży"),
                            validators = listOf(
                                    StateRequirementImplementationValidator(elementCode = "Sprzedający"),
                                    StateRequirementImplementationValidator(elementCode = "Kupujący"),
                                    StateRequirementImplementationValidator(elementCode = "Cena"),
                                    StateRequirementImplementationValidator(elementCode = "Przemiot umowy kupna sprzedaży")
                            )
                    ),
                    Definition(
                            id = Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Oświadczenie",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(),
                            baseDefinitions = listOf()
                    ),
                    Definition(
                            id = Id("Czynność", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Czynność",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(),
                            baseDefinitions = listOf()
                    ),
                    Definition(
                            id = Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Przedmiot umowy kupna sprzedaży",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(),
                            baseDefinitions = listOf()
                    ),
                    Definition(
                            id = Id("Samochód", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Samochód",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Marka", DefinitionRef(Id("STRING")), null),
                                    DefinitionProperty("Model", DefinitionRef(Id("STRING")), null),
                                    DefinitionProperty("Rok produkcji", DefinitionRef(Id("UINT")), null),
                                    DefinitionProperty("Nr silnika", DefinitionRef(Id("STRING")), null),
                                    DefinitionProperty("Nr nadwozia", DefinitionRef(Id("STRING")), null),
                                    DefinitionProperty("Nr rejestracyjny", DefinitionRef(Id("STRING")), null),
                                    DefinitionProperty("Przebieg", DefinitionRef(Id("UINT")), null),
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Osoba fizyczna",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("PESEL", DefinitionRef(Id("STRING")), null),
                                    DefinitionProperty("Adres", DefinitionRef(Id("STRING")), null),
                                    DefinitionProperty("Dokument tożsamości", DefinitionRef(Id("STRING")), null)
                            ),
                            baseDefinitions = listOf()
                    ),
                    Definition(
                            id = Id("PosiadaObowiązek", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Posiada obowiązek",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Osoba", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Czynność", DefinitionRef(Id("Czynność", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf()
                    ),
                    Definition(
                            id = Id("PosiadaPrawo", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Posiada obowiązek",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Osoba", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Czynność", DefinitionRef(Id("Czynność", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf()
                    ),
                    Definition(
                            id = Id("PosiadaZakaz", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Posiada obowiązek",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Osoba", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Czynność", DefinitionRef(Id("Czynność", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf()
                    ),
                    Definition(
                            id = Id("Oświadcza", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Oświadcza",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Osoba", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Oświadczenie", DefinitionRef(Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf()
                    ),
                    Definition(
                            id = Id("StanowiWyłączność", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Stanowi wyłączność",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Osoba", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Przedmiot", DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("WolnyOdWadPrawnych", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Wolny od wad prawnych",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Przedmiot", DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("WolnyOdPrawOsóbTrzecich", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Wolny od praw osób trzecich",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Przedmiot", DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("NieToczySięŻadnePostępowanie", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Nie toczy się żadne postępowanie",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Przedmiot", DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("NieStanowiPrzedmiotuZabezbieczenia", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Nie stanowi przedmiotu zabezbieczenia",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Przedmiot", DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("NiePosiadaWadTechnicznychZnanychJednejOsobieANieznanychDrugiejOsobie", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Nie posiada wad technicznych znanych jednej osobie a nieznanych drugiej osobie",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Pierwsza osoba", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Druga osoba", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Przedmiot", DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("ZapoznałSięZeStanemTechnicznym", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Zapoznał się ze stanem technicznym",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Osoba", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Przedmiot", DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Oświadczenie", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("PrzekazanieWłasności", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Przekazanie własności",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Nowy właściciel", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Przedmiot", DefinitionRef(Id("PrzedmiotUmowyKupnaSprzedaży", "pl.prawopolskie.kodekscywilny.definicje")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Czynność", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("Zapłata", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Zapłata",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Beneficjent", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Kwota", DefinitionRef(Id("STRING")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Czynność", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("Opłata", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Opłata",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Opłacana rzecz", DefinitionRef(Id("STRING")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Czynność", "pl.prawopolskie.kodekscywilny.definicje")))
                    ),
                    Definition(
                            id = Id("Pokwitowanie", "pl.prawopolskie.kodekscywilny.definicje"),
                            annotations = listOf(),
                            name = "Pokwitowanie",
                            description = null,
                            parameters = listOf(),
                            properties = listOf(
                                    DefinitionProperty("Odbiorca", DefinitionRef(Id("OsobaFizyczna", "pl.prawopolskie.kodekscywilny.definicje")), null),
                                    DefinitionProperty("Opis", DefinitionRef(Id("STRING")), null)
                            ),
                            baseDefinitions = listOf(DefinitionRef(Id("Czynność", "pl.prawopolskie.kodekscywilny.definicje")))
                    )
            ),
    )
}