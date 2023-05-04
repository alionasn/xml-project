package tests;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import mappings.DocumentXml;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import service.AbstractMethods;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static utils.Currencies.GBP;
import static utils.Currencies.LEI;
import static utils.Methods.CLRG;
import static utils.Schemas.PACS008001;

public class TestXmlFile extends AbstractMethods {

    @Test
    public void updateXmlFile() throws JAXBException {
        File xmlFile = initiateFile();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            DocumentXml initialDocumentXml = unmarshallDocument(xmlFile);
            updateXmlElement(doc, "FileAppHdr", "NbOfBtchs", 2);
            updateXmlElement(doc, "FileAppHdr", "SttlmCycl", 2);
            updateXmlElement(doc, "FinInstnId", "BICFI", RandomStringUtils.randomAlphabetic(11).toUpperCase(Locale.ROOT));
            updateXmlElement(doc, "AppHdr", "BizMsgIdr", RandomStringUtils.randomAlphanumeric(10, 35));
            updateXmlElement(doc, "AppHdr", "MsgDefIdr", PACS008001.getSchema());
            updateXmlElement(doc, "AppHdr", "CreDt", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS")));
            updateXmlElement(doc, "GrpHdr", "MsgId", RandomStringUtils.randomAlphabetic(10, 35));
            updateXmlElement(doc, "GrpHdr", "CreDtTm", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SS+03:00")));
            updateXmlElement(doc, "GrpHdr", "NbOfTxs", 2);
            updateAtribute(doc, "TtlIntrBkSttlmAmt", "Ccy", LEI.getValue());
            updateXmlElement(doc, "GrpHdr", "TtlIntrBkSttlmAmt", 1000.00);
            updateXmlElement(doc, "GrpHdr", "IntrBkSttlmDt", LocalDate.now());
            updateXmlElement(doc, "SttlmInf", "SttlmMtd", CLRG.name());
            updateRepeatableXmlElement(doc, "PmtId", "InstrId", RandomStringUtils.randomAlphanumeric(18), 0);
            updateRepeatableXmlElement(doc, "PmtId", "InstrId", RandomStringUtils.randomAlphanumeric(18), 1);
            updateAllIdenticalXmlTags(doc, "PmtId", "EndToEndId", "INVOICE" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSS")));
            updateRepeatableXmlElement(doc, "PmtId", "TxId", RandomStringUtils.randomAlphanumeric(18), 0);
            updateRepeatableXmlElement(doc, "PmtId", "TxId", RandomStringUtils.randomAlphanumeric(18), 1);
            updateAllIdenticalXmlTags(doc, "PmtId", "ClrSysRef", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHH")));
            writeXMLFile(doc, xmlFile);

            DocumentXml updatedDocumentXml = unmarshallDocument(xmlFile);

            Assertions.assertNotNull(updatedDocumentXml);
            Assertions.assertNotEquals(initialDocumentXml, updatedDocumentXml);
        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void updateAttribute() throws JAXBException {
        File xmlFile = initiateFile();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            updateAtribute(doc, "TtlIntrBkSttlmAmt", "Ccy", GBP.getValue());
            writeXMLFile(doc, xmlFile);

            DocumentXml documentXml = unmarshallDocument(xmlFile);
            Assertions.assertEquals(documentXml
                            .getCreditTransfer()
                            .getFIToFICstmrCdtTrf()
                            .getGroupHeader()
                            .getTtlIntrBkSttlmAmt()
                            .getCcy()
                    , GBP.getValue());
            System.out.println(documentXml.getCreditTransfer().getFIToFICstmrCdtTrf().getGroupHeader().getTtlIntrBkSttlmAmt().getCcy());
        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void updateRepeatableElement() {
        File xmlFile = initiateFile();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            String instrIdValue = RandomStringUtils.randomAlphanumeric(18);
            String instrIdValue2 = RandomStringUtils.randomAlphanumeric(18);
            updateRepeatableXmlElement(doc, "PmtId", "InstrId", instrIdValue, 0);
            updateRepeatableXmlElement(doc, "PmtId", "InstrId", instrIdValue2, 1);
            writeXMLFile(doc, xmlFile);
            DocumentXml updateDocumentXml = unmarshallDocument(xmlFile);
            Assertions.assertAll(
                    () -> Assertions.assertEquals(updateDocumentXml.getCreditTransfer().getFIToFICstmrCdtTrf().getTransferInfoList().get(0).getPaymentId().getInstrId(), instrIdValue),
                    () -> Assertions.assertEquals(updateDocumentXml.getCreditTransfer().getFIToFICstmrCdtTrf().getTransferInfoList().get(1).getPaymentId().getInstrId(), instrIdValue2)
            );
        } catch (SAXException | ParserConfigurationException | IOException | JAXBException | TransformerException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void updatingBICFI() {
        File xmlFile = initiateFile();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            String BICFIValue = RandomStringUtils.randomAlphabetic(11).toUpperCase(Locale.ROOT);
            updateXmlElement(doc, "FinInstnId", "BICFI", BICFIValue);
            writeXMLFile(doc, xmlFile);
            JAXBContext jaxbContext = JAXBContext.newInstance(DocumentXml.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            DocumentXml documentXml = (DocumentXml) jaxbUnmarshaller.unmarshal(xmlFile);
            String oldValue = doc.getElementsByTagName("FinInstnId").item(0).getFirstChild().getNodeValue();
            String newValue = documentXml.getAppHeader().getFinancialInstId().getBICFI();

            Assertions.assertNotEquals(oldValue, newValue);
            Assertions.assertEquals(newValue, BICFIValue);
        } catch (SAXException | ParserConfigurationException | IOException | JAXBException | TransformerException e1) {
            System.out.println("Something went wrong");
            e1.printStackTrace();
        }
    }

    private static File initiateFile() {
        File xmlFile = null;
        try {
            String pathToFile = "src/test/resources/PAYMENT.xml";
            xmlFile = new File(pathToFile);
            if (xmlFile.length() == 0) {
                throw new SecurityException("WARN:Make sure file is not empty");
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        return xmlFile;
    }
}
