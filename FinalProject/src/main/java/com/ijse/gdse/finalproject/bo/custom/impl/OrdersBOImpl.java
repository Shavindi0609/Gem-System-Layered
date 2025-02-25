package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.OrdersBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.SQLUtil;
import com.ijse.gdse.finalproject.dao.custom.*;
import com.ijse.gdse.finalproject.db.DBConnection;
import com.ijse.gdse.finalproject.dto.CustomerDTO;
import com.ijse.gdse.finalproject.dto.GemDTO;
import com.ijse.gdse.finalproject.dto.OrdersDTO;
import com.ijse.gdse.finalproject.entity.Customer;
import com.ijse.gdse.finalproject.entity.Gem;
import com.ijse.gdse.finalproject.entity.Orders;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrdersBOImpl implements OrdersBO {

    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    GemDAO gemDAO = (GemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.GEM);
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PAYMENT);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDERDETAILS);


    @Override
    public String getNextOrderId() throws SQLException {
        return ordersDAO.getNext();
    }

    @Override
    public String getNextPaymentId() throws SQLException {
        return paymentDAO.getNext();
    }

    @Override
    public boolean saveOrder(OrdersDTO order) throws SQLException {
//        return ordersDAO.save(order)
      Orders orders =  new Orders(
              order.getOrderId(),
              order.getCustomerId(),
              order.getOrderDate(),
              order.getPaymentId(),
              order.getTotalAmount(),
              order.getMethod(),
              order.getOrderDetailsDTOS()
      );

            System.out.println("clicked");
            Connection connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Start transaction

            try {
                //save payment
//                boolean isPaymentSaved = SQLUtil.execute(
//                        "INSERT INTO payment (payment_id, method, total_amount) VALUES (?, ?, ?)",
//                        order.getPaymentId(),
//                        order.getMethod(),
//                        order.getTotalAmount()
//                );
                boolean isSavePayment = paymentDAO.save(orders);
                if (!isSavePayment) {
                    connection.rollback();
                    return false;
                }
                // Save the order
//                boolean isOrderSaved = SQLUtil.execute(
//                        "INSERT INTO orders (order_id, customer_id, order_date, payment_id) VALUES (?, ?, ?, ?)",
//                        order.getOrderId(),
//                        order.getCustomerId(),
//                        order.getOrderDate(),
//                        order.getPaymentId()
//                );
                boolean isSaveOrder = ordersDAO.save(orders);
                if (!isSaveOrder) {
                    connection.rollback();
                    return false;
                }

                // Save order details
                boolean isOrderDetailListSaved = orderDetailsDAO.saveOrderDetailsList(orders.getOrderDetailsDTOS());
                if (!isOrderDetailListSaved) {
                    connection.rollback();
                    return false;
                }

                // Commit transaction
                connection.commit();
                return true;

            } catch (Exception e) {
                e.printStackTrace();
                connection.rollback(); // Roll back on error
                return false;

            } finally {
                connection.setAutoCommit(true); // Restore auto-commit
            }


    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException {
        return customerDAO.getAllCustomerIds();
    }

    @Override
    public CustomerDTO findByIdCustomer(String selectedItemId) throws SQLException {
        Customer customer = customerDAO.findById(selectedItemId);
        return new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getNic(),customer.getEmail(),customer.getPhone(),customer.getAddress(),customer.getUserId());
    }

    @Override
    public ArrayList<String> getAllGemIds() throws SQLException {
        return gemDAO.getAllGemIds();
    }

    @Override
    public GemDTO findByIdGem(String selectedItemId) throws SQLException {
        Gem gem = gemDAO.findById(selectedItemId);
        return new GemDTO(gem.getGem_id(),gem.getGem_name(),gem.getGem_color(),gem.getSize(),gem.getPrice(),gem.getQty(),gem.getIs_cetified(),gem.getCategory_id());
    }
}
