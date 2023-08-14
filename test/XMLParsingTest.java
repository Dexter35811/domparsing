package test;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import src.XMLParsing;

public class XMLParsingTest {

    static XMLParsing xmlParsing;

    @Before
    public static void setUp() {
        xmlParsing = new XMLParsing();
        // Mocking the input document

    }

    @Test
    public void testXMLParsing() throws Exception {
        XMLParsing.main(null);
        // Document actualDocument = ArgumerntCapture
        Document expectDocument = XMLParsing.getDocumentFromPath("files/ExpextedDoc.xml");
        assertEquals(expectDocument, actualDocument);
    }

}
