package src;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParsing {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Logger log = Logger.getLogger(XMLParsing.class.getName());
        log.info("Enter the path of xml File");
        String path = sc.nextLine();
        Document inputdoc;
        inputdoc = getDocumentFromPath(path);
        addElePassion(inputdoc);
        addAttrID(inputdoc);
        log.info(printXML(inputdoc));
        sc.close();
    }

    private static void addAttrID(Document doc) {
        NodeList nlStudent = doc.getElementsByTagName("student");
        for (int i = 0; i < nlStudent.getLength(); i++) {
            Element eleStudent = (Element) nlStudent.item(i);
            eleStudent.setAttribute("id", String.valueOf(i));
        }
    }

    private static void addElePassion(Document doc) {
        NodeList nlroot = doc.getElementsByTagName("student");
        for (int i = 0; i < nlroot.getLength(); i++) {
            Element elePassion = doc.createElement("Passion");
            elePassion.appendChild(doc.createTextNode("Singing"));
            Element eleStudent = (Element) nlroot.item(i);
            eleStudent.appendChild(elePassion);
        }
    }

    public static String printXML(Document doc) throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter stringWriter = new StringWriter(0);
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(stringWriter);
        transformer.transform(domSource, streamResult);

        return stringWriter.toString();
    }

    public static Document getDocumentFromPath(String pathString)
            throws ParserConfigurationException, SAXException, IOException {
        File ioFile = new File(pathString);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(ioFile);
    }
}
