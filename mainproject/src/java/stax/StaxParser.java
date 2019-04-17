package stax;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import model.Employee;

public class StaxParser
{
    private static final String ROLE = "role";
    private static final String GENDER = "gender";
    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String ID = "id";
    private static final String SALARY = "salary";
    
    private static final String EMPLOYEE = "Employee";
    private Employee employee;
    List<Employee> employees = new ArrayList<>();

    public List<Employee> parse(XMLEventReader xmlEventReader) throws FileNotFoundException, XMLStreamException
    {
        while (xmlEventReader.hasNext())
        {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            proceedStartElement(xmlEvent, xmlEventReader);
            proceedEndElement(xmlEvent);
        }
        return employees;
    }

    private void proceedStartElement(XMLEvent xmlEvent, XMLEventReader xmlEventReader) throws XMLStreamException
    {
        if (xmlEvent.isStartElement())
        {
            StartElement startElement = xmlEvent.asStartElement();
            if (isTagNameEqual(startElement, EMPLOYEE))
            {
                employee = new Employee();
                Attribute attribute = startElement.getAttributeByName(new QName(ID));
                if (attribute != null)
                {
                    employee.setId(Integer.parseInt(attribute.getValue()));
                }
            }
            // set the other varibles from xml elements
            else if (isTagNameEqual(startElement, AGE))
            {
                employee.setAge(Integer.parseInt(xmlEventReader.nextEvent().asCharacters().getData()));
            }
            else if (isTagNameEqual(startElement, NAME))
            {
                employee.setName(xmlEventReader.nextEvent().asCharacters().getData());
            }
            else if (isTagNameEqual(startElement, GENDER))
            {
                employee.setGender(xmlEventReader.nextEvent().asCharacters().getData());
            }
            else if (isTagNameEqual(startElement, ROLE))
            {
                employee.setRole(xmlEventReader.nextEvent().asCharacters().getData());
            }
           
        }
    }

    private void proceedEndElement(XMLEvent xmlEvent)
    {
        if (xmlEvent.isEndElement())
        {
            EndElement endElement = xmlEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals(EMPLOYEE))
            {
                employees.add(employee);
            }
        }
    }

    private boolean isTagNameEqual(StartElement startElement, String tagName)
    {
        return startElement.getName().getLocalPart().equals(tagName);
    }
}
