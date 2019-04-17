package sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Employee;

public class EmployeeHandler extends DefaultHandler
{
    private List<Employee> employees;
    private Employee employee;
    boolean bAge = false;
    boolean bName = false;
    boolean bGender = false;
    boolean bRole = false;
    boolean bSalary = false;
    boolean bTime = false;

    public List<Employee> getEmployees()
    {
        return employees;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {

        if (qName.equalsIgnoreCase("Employee"))
        {
            String id = attributes.getValue("id");
            employee = new Employee();
            employee.setId(Integer.parseInt(id));
            if (employees == null)
            {
                employees = new ArrayList<>();
            }
        }
        else if (qName.equalsIgnoreCase("name"))
        {
            bName = true;
        }
        else if (qName.equalsIgnoreCase("age"))
        {
            bAge = true;
        }
        else if (qName.equalsIgnoreCase("gender"))
        {
            bGender = true;
        }
        else if (qName.equalsIgnoreCase("role"))
        {
            bRole = true;
        }
        else if (qName.equalsIgnoreCase("salary"))
        {
            bSalary = true;
        }
        else if (qName.equalsIgnoreCase("time"))
        {
            bTime = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (qName.equalsIgnoreCase("Employee"))
        {
            employees.add(employee);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException
    {

        if (bAge)
        {
            employee.setAge(Integer.parseInt(new String(ch, start, length)));
            bAge = false;
        }
        else if (bName)
        {
            employee.setName(new String(ch, start, length));
            bName = false;
        }
        else if (bRole)
        {
            employee.setRole(new String(ch, start, length));
            bRole = false;
        }
        else if (bGender)
        {
            employee.setGender(new String(ch, start, length));
            bGender = false;
        }
        
        else if (bSalary)
        {
            employee.setGender(new String(ch, start, length));
            bSalary = false;
        }
        else if (bTime)
        {
            employee.setTime(new String(ch, start, length));
            bTime = false;
        }
        
    }
}
