package com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.exporter

import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageDocument
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageProvision
import com.piotrwalkusz.smartlaw.compiler.converter.naturallanguage.model.NaturalLanguageSection
import org.docx4j.XmlUtils
import org.docx4j.jaxb.Context
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart
import org.docx4j.wml.*
import org.docx4j.wml.PPrBase.NumPr
import org.docx4j.wml.PPrBase.NumPr.Ilvl
import org.docx4j.wml.PPrBase.NumPr.NumId
import java.io.File
import java.math.BigInteger


class DocxExporter : NaturalLanguageExporter {

    private val NUMBERING_TEMPLATE = "<w:numbering xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:w14=\"http://schemas.microsoft.com/office/word/2010/wordml\" mc:Ignorable=\"w14\"><w:abstractNum w:abstractNumId=\"1\"><w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"720\"/></w:tabs><w:ind w:left=\"720\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl><w:lvl w:ilvl=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%2.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"1080\"/></w:tabs><w:ind w:left=\"1080\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl><w:lvl w:ilvl=\"2\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%3.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"1440\"/></w:tabs><w:ind w:left=\"1440\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl><w:lvl w:ilvl=\"3\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%4.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"1800\"/></w:tabs><w:ind w:left=\"1800\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl><w:lvl w:ilvl=\"4\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%5.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"2160\"/></w:tabs><w:ind w:left=\"2160\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl><w:lvl w:ilvl=\"5\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%6.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"2520\"/></w:tabs><w:ind w:left=\"2520\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl><w:lvl w:ilvl=\"6\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%7.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"2880\"/></w:tabs><w:ind w:left=\"2880\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl><w:lvl w:ilvl=\"7\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%8.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"3240\"/></w:tabs><w:ind w:left=\"3240\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl><w:lvl w:ilvl=\"8\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%9.\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"3600\"/></w:tabs><w:ind w:left=\"3600\" w:hanging=\"360\"/></w:pPr><w:rPr></w:rPr></w:lvl></w:abstractNum><w:abstractNum w:abstractNumId=\"2\"><w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"2\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"3\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"4\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"5\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"6\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"7\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl><w:lvl w:ilvl=\"8\"><w:start w:val=\"1\"/><w:numFmt w:val=\"none\"/><w:suff w:val=\"nothing\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:tabs><w:tab w:val=\"num\" w:pos=\"0\"/></w:tabs><w:ind w:left=\"0\" w:hanging=\"0\"/></w:pPr></w:lvl></w:abstractNum><w:num w:numId=\"1\"><w:abstractNumId w:val=\"1\"/></w:num><w:num w:numId=\"2\"><w:abstractNumId w:val=\"2\"/></w:num></w:numbering>"

    override fun export(document: NaturalLanguageDocument) {
        val wordPackage = WordprocessingMLPackage.createPackage()
        val mainDocumentPart = wordPackage.mainDocumentPart

        mainDocumentPart.addStyledParagraphOfText("Title", document.title)

        var listIndex = 1L
        document.objects.forEach { documentObject ->
            when (documentObject) {
                is NaturalLanguageProvision -> {
                    mainDocumentPart.addParagraphOfText(documentObject.content)
                }
                is NaturalLanguageSection -> {
                    mainDocumentPart.addObject(prepareSectionHeader(documentObject.title))
                    if (documentObject.nestedNaturalLanguageDocumentObjects.size > 1) {
                        mainDocumentPart.addTargetPart(prepareListDefinition(listIndex))
                        documentObject.nestedNaturalLanguageDocumentObjects.forEach { provision ->
                            mainDocumentPart.addObject(prepareListParagraph(listIndex, (provision as NaturalLanguageProvision).content))
                        }
                        listIndex++
                    } else {
                        documentObject.nestedNaturalLanguageDocumentObjects.forEach { provision ->
                            mainDocumentPart.addParagraphOfText((provision as NaturalLanguageProvision).content)
                        }
                    }
                }
            }
        }

        wordPackage.save(File("output.docx"))
    }

    private fun prepareListDefinition(listIndex: Long): NumberingDefinitionsPart {
        val ndp = NumberingDefinitionsPart()
        ndp.jaxbElement = XmlUtils.unmarshalString(NUMBERING_TEMPLATE) as Numbering
        ndp.jaxbElement.abstractNum.get(0).abstractNumId = BigInteger.valueOf(listIndex)

        return ndp
    }

    private fun prepareListParagraph(listIndex: Long, content: String): P {
        val objCreator = Context.getWmlObjectFactory()
        val paragraph: P = objCreator.createP()

        val ppr: PPr = objCreator.createPPr()

        val numpr: NumPr = objCreator.createPPrBaseNumPr()
        val style = objCreator.createPPrBasePStyle()

        val numId: NumId = objCreator.createPPrBaseNumPrNumId()
        numId.setVal(BigInteger.valueOf(listIndex))
        numpr.numId = numId
        val run: R = objCreator.createR()
        val t = objCreator.createText()
        t.value = content
        run.content.add(t)
        val iLevel: Ilvl = objCreator.createPPrBaseNumPrIlvl()

        numpr.setIlvl(iLevel)
        iLevel.setVal(BigInteger.valueOf(0))

        ppr.setNumPr(numpr)
        ppr.ind = objCreator.createPPrBaseInd()
        style.setVal("ListParagraph")

        ppr.setPStyle(style)
        paragraph.setPPr(ppr)
        paragraph.getContent().add(run)

        return paragraph
    }

    private fun prepareSectionHeader(title: String): P {
        val factory = Context.getWmlObjectFactory()
        val paragraph = factory.createP()
        val run = factory.createR()
        val text = factory.createText()
        text.value = title
        run.content.add(text)
        paragraph.content.add(run)

        val paragraphProperties = factory.createPPr()
        val justification = factory.createJc()
        justification.setVal(JcEnumeration.CENTER)
        paragraphProperties.jc = justification
        paragraph.pPr = paragraphProperties

        return paragraph
    }
}