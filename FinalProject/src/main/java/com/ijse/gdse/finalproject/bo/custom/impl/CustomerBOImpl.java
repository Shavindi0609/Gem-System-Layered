package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.CustomerBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.CustomerDAO;
import com.ijse.gdse.finalproject.dao.custom.UserDAO;
import com.ijse.gdse.finalproject.dto.CustomerDTO;
import com.ijse.gdse.finalproject.entity.Customer;


import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);


    @Override
    public String getNextCustomerId() throws SQLException {
        return customerDAO.getNext();
    }

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException {
        return customerDAO.save(new Customer(customer.getCustomerId(),customer.getName(),customer.getNic(),customer.getEmail(),customer.getPhone(),customer.getAddress(),customer.getUserId()));
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException {
        ArrayList<Customer> all = customerDAO.getAll();

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : all) {
            CustomerDTO customerDTO = new CustomerDTO(
                   customer.getCustomerId(),
                    customer.getName(),
                    customer.getNic(),
                    customer.getEmail(),
                    customer.getPhone(),
                    customer.getAddress(),
                    customer.getUserId()
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }


    @Override
    public boolean updateCustomer(CustomerDTO customer) throws SQLException {
        return customerDAO.update(new Customer(customer.getCustomerId(),customer.getName(),customer.getNic(),customer.getEmail(),customer.getPhone(),customer.getAddress(),customer.getUserId()));
    }

    @Override
    public boolean deleteCustomer(String customerId) throws SQLException {
        return customerDAO.delete(customerId);
    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        return userDAO.getAllUserIds();
    }


    @Override
    public int getCustomerCount() throws SQLException {
        return customerDAO.getCustomerCount();
    }

    @Override
    public CustomerDTO findById(String selectedItemId) throws SQLException {
        Customer customer = customerDAO.findById(selectedItemId);
        CustomerDTO customerDTO = new CustomerDTO(
                customer.getCustomerId(),
                customer.getName(),
                customer.getNic(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getUserId()
        );
        return customerDTO;
    }
}
