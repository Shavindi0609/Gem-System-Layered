package com.ijse.gdse.finalproject.bo.custom;

import com.ijse.gdse.finalproject.bo.SuperBO;
import com.ijse.gdse.finalproject.dao.custom.GemDAO;
import com.ijse.gdse.finalproject.dto.Gem2DTO;
import com.ijse.gdse.finalproject.dto.GemDTO;
import com.ijse.gdse.finalproject.dto.OrderDetailsDTO;
import com.ijse.gdse.finalproject.dto.SupplierOrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GemBO extends SuperBO {

    public ArrayList<Gem2DTO> getAllCategory() throws SQLException;
    public String getNextGemId() throws SQLException;
    public boolean saveGem(GemDTO gem) throws SQLException;
    public ArrayList<GemDTO> getAllGem() throws SQLException;
    public boolean deleteGem(String gemId) throws SQLException;
    public boolean updateGem(GemDTO gem) throws SQLException;
    public ArrayList<String> getAllGemIds() throws SQLException;
    public GemDTO findById(String selectedItemId) throws SQLException;
    public boolean reduceQty(OrderDetailsDTO orderDetails) throws SQLException;
    public boolean reduceeQty(SupplierOrderDetailsDTO supplierOrderDetails) throws SQLException;
}
