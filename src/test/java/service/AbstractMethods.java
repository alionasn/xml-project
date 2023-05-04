package service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import mappings.DocumentXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public abstract class AbstractMethods {

    protected void writeXMLFile(Document doc, File file)
            throws TransformerFactoryConfigurationError, TransformerException {
        doc.getDocumentElement().normalize();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.setOutputProperty(OutputKeys.INDENT, "no");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes" );
        transformer.transform(source, result);
        System.out.println("XML file updated successfully");
    }

    protected void updateRepeatableXmlElement(Document doc, String tagParent, String tagChild, Object value, int index) {
        NodeList users = doc.getElementsByTagName(tagParent);
        Element user = (Element) users.item(index);
        Node nodeName = user.getElementsByTagName(tagChild).item(0).getFirstChild();
        if (value != "null" || value != "") {
            nodeName.setNodeValue(String.valueOf(value));
        } else {
            user.removeChild(nodeName);
        }
    }

    protected void updateXmlElement(Document doc, String tagParent, String tagChild, Object value) {
        NodeList users = doc.getElementsByTagName(tagParent);
        Element user = (Element) users.item(0);
        Node nodeName = user.getElementsByTagName(tagChild).item(0).getFirstChild();
        if (value != "null" || value != "") {
            nodeName.setNodeValue(String.valueOf(value));
        } else {
            user.removeChild(nodeName);
        }
    }

    protected void updateAllIdenticalXmlTags(Document doc, String tagParent, String tagChild, String value) {
        NodeList users = doc.getElementsByTagName(tagParent);
        Element user;
        for (int i = 0; i < users.getLength(); i++) {
            user = (Element) users.item(i);
            Node name = user.getElementsByTagName(tagChild).item(0).getFirstChild();
            name.setNodeValue(value);
        }
    }

    protected void updateAtribute(Document doc, String tagParent, String attribute, String value) {
        NodeList users = doc.getElementsByTagName(tagParent);
        Element user = (Element) users.item(0);
        user.setAttribute(attribute, value);
    }

    protected DocumentXml unmarshallDocument(File xmlFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(DocumentXml.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (DocumentXml) jaxbUnmarshaller.unmarshal(xmlFile);
    }
}
