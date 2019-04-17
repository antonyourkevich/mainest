package model;

public class Employee
{
    private int id;
    private String name;
    private String gender;
    private int age;
    private String role;
    private int salary;
    private int time;
    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public int getSalary()
    {
        return salary;
    }

    public void setSalary(int salary)
    {
        this.salary = salary;
    }
    
    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }
    
    @Override
    public String toString()
    {
        return "Employee:: ID=" + this.id + " Name=" + this.name + " Age=" + this.age + " Gender=" + this.gender
                + " Role=" + this.role + " Salary=" + this.salary + "Time =" + this.time ;
    }

}
