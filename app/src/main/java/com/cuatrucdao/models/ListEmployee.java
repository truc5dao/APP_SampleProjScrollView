package com.cuatrucdao.models;

import java.util.ArrayList;

public class ListEmployee {

    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void gen_dataset()
    {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("john");
        e1.setEmail("john@gmail.com");
        e1.setPhone("08929408049");
        e1.setUsername("john");
        e1.setPassword("123");
        employees.add(e1);

        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("john");
        e2.setEmail("john@gmail.com");
        e2.setPhone("08929408049");
        e2.setUsername("john");
        e2.setPassword("223");
        employees.add(e2);


        Employee e3 = new Employee();
        e3.setId(3);
        e3.setName("john");
        e3.setEmail("john@gmail.com");
        e3.setPhone("08929408049");
        e3.setUsername("john");
        e3.setPassword("323");
        employees.add(e3);

    }
}
