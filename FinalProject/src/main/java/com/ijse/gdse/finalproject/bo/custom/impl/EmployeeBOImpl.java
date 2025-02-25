package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.EmployeeBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.EmployeeDAO;
import com.ijse.gdse.finalproject.dao.custom.UserDAO;
import com.ijse.gdse.finalproject.dto.EmployeeDTO;
import com.ijse.gdse.finalproject.entity.Employee;


import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);


    @Override
    public String getNextEmployeeId() throws SQLException {
        return employeeDAO.getNext();
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employee) throws SQLException {
        return employeeDAO.save(new Employee(employee.getEmployeeId(),employee.getName(),employee.getNic(),employee.getEmail(),employee.getPhone(),employee.getAddress(),employee.getUserId()));
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException {
        ArrayList<Employee> all = employeeDAO.getAll();

        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : all) {
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getNic(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getAddress(),
                    employee.getUserId()
            );
           employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public boolean deleteEmployee(String employeeId) throws SQLException {
        return employeeDAO.delete(employeeId);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee) throws SQLException {
        return employeeDAO.update(new Employee(employee.getEmployeeId(),employee.getName(),employee.getNic(),employee.getEmail(),employee.getPhone(),employee.getAddress(),employee.getUserId()));
    }

    @Override
    public int getEmployeeCount() throws SQLException {
        return employeeDAO.getEmployeeCount();
    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        return userDAO.getAllUserIds();
    }

}
