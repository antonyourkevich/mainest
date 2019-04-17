package dom;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Employee;

public class DomParser
{
    public List<Employee> parse(Document document) throws FileNotFoundException, XMLStreamException
    {
        NodeList nodeList = document.getElementsByTagName("Employee");
        List<Employee> employees = new ArrayList<Employee>();
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            employees.add(getEmployee(nodeList.item(i)));
        }
        return employees;
    }

    private static Employee getEmployee(Node node)
    {
        Employee employee = new Employee();
        Element element = (Element) node;
        employee.setName(getTagValue("name", element));
        employee.setAge(Integer.parseInt(getTagValue("age", element)));
        employee.setGender(getTagValue("gender", element));
        employee.setRole(getTagValue("role", element));
        employee.setSalary(Integer.parseInt(getTagValue("salary", element)));
        employee.setId(Integer.parseInt(element.getAttribute("id")));
        employee.setTime(Integer.parseInt(getTagValue("time", element)));
        return employee;
    }

   

	

	private static String getTagValue(String tag, Element element)
    {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
