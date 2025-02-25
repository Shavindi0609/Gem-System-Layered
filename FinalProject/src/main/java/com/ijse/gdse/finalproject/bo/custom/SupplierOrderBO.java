package com.ijse.gdse.finalproject.bo.custom;

import com.ijse.gdse.finalproject.bo.SuperBO;
import com.ijse.gdse.finalproject.dto.GemDTO;
import com.ijse.gdse.finalproject.dto.SupplierDTO;
import com.ijse.gdse.finalproject.dto.SupplierOrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderBO extends SuperBO {

    String getNextSupplierOrderId() throws SQLException;
    String getNextSupplierPaymentId() throws SQLException;
    public boolean saveSupplierOrder(SupplierOrderDTO supplierOrder) throws SQLException;

    ArrayList<String> getAllSupplierIds() throws SQLException;
    public SupplierDTO findByIdSupplier(String selectedItemId) throws SQLException;

    ArrayList<String> getAllGemIds() throws SQLException;
    public GemDTO findByIdGem(String selectedItemId) throws SQLException;
}
