package com.ijse.gdse.finalproject.bo.custom;

import com.ijse.gdse.finalproject.bo.SuperBO;
import com.ijse.gdse.finalproject.dto.CustomerDTO;
import com.ijse.gdse.finalproject.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {

     String getNextCustomerId() throws SQLException;
     boolean saveCustomer(CustomerDTO customer) throws SQLException;
     ArrayList<CustomerDTO> getAllCustomer() throws SQLException;
     boolean updateCustomer(CustomerDTO customer) throws SQLException;
     boolean deleteCustomer(String customerId) throws SQLException;
     ArrayList<String> getAllUserIds() throws SQLException;
     int getCustomerCount() throws SQLException;
     CustomerDTO findById(String selectedItemId) throws SQLException;
}
