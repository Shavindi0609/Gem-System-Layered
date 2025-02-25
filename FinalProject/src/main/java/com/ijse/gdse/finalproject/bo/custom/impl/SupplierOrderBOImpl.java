package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.SupplierOrderBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.SQLUtil;
import com.ijse.gdse.finalproject.dao.custom.*;
import com.ijse.gdse.finalproject.db.DBConnection;
import com.ijse.gdse.finalproject.dto.GemDTO;
import com.ijse.gdse.finalproject.dto.SupplierDTO;
import com.ijse.gdse.finalproject.dto.SupplierOrderDTO;
import com.ijse.gdse.finalproject.entity.Gem;
import com.ijse.gdse.finalproject.entity.Orders;
import com.ijse.gdse.finalproject.entity.Supplier;
import com.ijse.gdse.finalproject.entity.SupplierOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderBOImpl implements SupplierOrderBO {

    SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIERORDER);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    GemDAO gemDAO = (GemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.GEM);
    SupplierOrderDetailsDAO supplierOrderDetailsDAO = (SupplierOrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIERORDERDETAILS);
    SupplierPaymentDAO supplierPaymentDAO = (SupplierPaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIERPAYMENT);

    @Override
    public String getNextSupplierOrderId() throws SQLException {
        return supplierOrderDAO.getNext();
    }

    @Override
    public String getNextSupplierPaymentId() throws SQLException {
        return supplierPaymentDAO.getNext();
    }

    @Override
    public boolean saveSupplierOrder(SupplierOrderDTO supplierOrder) throws SQLException {

        SupplierOrder supplierorder =  new SupplierOrder(
                supplierOrder.getSupplierOrderId(),
                supplierOrder.getSupplierId(),
                supplierOrder.getOrderDate(),
                supplierOrder.getPaymentId(),
                supplierOrder.getTotalAmount(),
                supplierOrder.getMethod(),
                supplierOrder.getSupplierOrderDetailsDTOS()
        );

        System.out.println("clicked");
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false); // Start transaction

        try {

            boolean isSaveSupplierPayment = supplierPaymentDAO.save(supplierorder);
            if (!isSaveSupplierPayment) {
                connection.rollback();
                return false;
            }

            boolean isSaveSupplierOrder = supplierOrderDAO.save(supplierorder);

            if (!isSaveSupplierOrder) {
                connection.rollback();
                return false;
            }

            boolean isSupplierOrderDetailListSaved = supplierOrderDetailsDAO.saveSupplierOrderDetailsList(supplierorder.getSupplierOrderDetailsDTOS());
            if (!isSupplierOrderDetailListSaved) {
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
    public ArrayList<String> getAllSupplierIds() throws SQLException {
        return supplierDAO.getAllSupplierIds();
    }

    @Override
    public SupplierDTO findByIdSupplier(String selectedItemId) throws SQLException {
        Supplier supplier = supplierDAO.findById(selectedItemId);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getNic(),supplier.getEmail(),supplier.getPhone(),supplier.getAddress());
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
