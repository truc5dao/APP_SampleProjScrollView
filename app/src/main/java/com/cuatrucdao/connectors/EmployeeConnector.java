package com.cuatrucdao.connectors;

import com.cuatrucdao.models.ListEmployee;
import com.cuatrucdao.models.Employee;
public class EmployeeConnector {


    public Employee login(String usr, String pwd)
    {
        ListEmployee le = new ListEmployee();
        le.gen_dataset();

        for (Employee emp : le.getEmployees())
        {
            if (emp.getUsername().equalsIgnoreCase(usr) && emp.getPassword().equals(pwd))
            {
                return emp;

            }
        }
        return null;
    }
}
