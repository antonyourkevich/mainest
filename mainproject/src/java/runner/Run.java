	package runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import dom.DomParser;
import model.Employee;
import sax.EmployeeHandler;
import stax.StaxParser;


public class Run
{

    private static final String EMPLOYEES_XML = "employees.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException
    {
        System.out.println(" ========================= SAX parser ==============================");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        EmployeeHandler employeeHandler = new EmployeeHandler();
        saxParser.parse(new File(EMPLOYEES_XML), employeeHandler);
        List<Employee> employees = employeeHandler.getEmployees();
        employees.forEach(employee -> System.out.println(employee));

        System.out.println(" ============================== STAX parser =========================");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(EMPLOYEES_XML));
        employees = new StaxParser().parse(xmlEventReader);
        employees.forEach(employee -> System.out.println(employee));

        System.out.println(" ============================== DOM parser =========================");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(EMPLOYEES_XML);
        employees = new DomParser().parse(document);
        employees.forEach(employee -> System.out.println(employee));
    }

}
