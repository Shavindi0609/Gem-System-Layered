package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.GemBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.GemDAO;
import com.ijse.gdse.finalproject.dto.Gem2DTO;
import com.ijse.gdse.finalproject.dto.GemDTO;
import com.ijse.gdse.finalproject.dto.OrderDetailsDTO;
import com.ijse.gdse.finalproject.dto.SupplierOrderDetailsDTO;
import com.ijse.gdse.finalproject.entity.Gem;
import com.ijse.gdse.finalproject.entity.Gem2;
import com.ijse.gdse.finalproject.entity.OrderDetails;
import com.ijse.gdse.finalproject.entity.SupplierOrderDetails;


import java.sql.SQLException;
import java.util.ArrayList;

public class GemBOImpl implements GemBO {

    GemDAO gemDAO = (GemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.GEM);


    @Override
    public ArrayList<Gem2DTO> getAllCategory() throws SQLException {
       ArrayList<Gem2> all  = gemDAO.getAllCategory();
       ArrayList<Gem2DTO> gem2DTOS = new ArrayList<>();
       for (Gem2 gem2 : all) {
           Gem2DTO gem2DTO = new Gem2DTO(
               gem2.getCategoryId(),
               gem2.getCategoryName()
           );

           gem2DTOS.add(gem2DTO);
       }
       return gem2DTOS;
    }

    @Override
    public String getNextGemId() throws SQLException {
        return gemDAO.getNext();
    }

    @Override
    public boolean saveGem(GemDTO gem) throws SQLException {
        return gemDAO.save(new Gem(gem.getGem_id(),gem.getGem_name(),gem.getGem_color(),gem.getSize(),gem.getPrice(),gem.getQty(),gem.getIs_cetified(),gem.getCategory_id()));
    }

    @Override
    public ArrayList<GemDTO> getAllGem() throws SQLException {
        ArrayList<Gem> all  = gemDAO.getAll();
        ArrayList<GemDTO> gemDTOS = new ArrayList<>();
        for (Gem gem : all) {
            GemDTO gemDTO = new GemDTO(
                    gem.getGem_id(),
                    gem.getGem_name(),
                    gem.getGem_color(),
                    gem.getSize(),
                    gem.getPrice(),
                    gem.getQty(),
                    gem.getIs_cetified(),
                    gem.getCategory_id()
            );

            gemDTOS.add(gemDTO);
        }
        return gemDTOS;
    }

    @Override
    public boolean deleteGem(String gemId) throws SQLException {
        return gemDAO.delete(gemId);
    }

    @Override
    public boolean updateGem(GemDTO gem) throws SQLException {
        return gemDAO.update(new Gem(gem.getGem_id(),gem.getGem_name(),gem.getGem_color(),gem.getSize(),gem.getPrice(),gem.getQty(),gem.getIs_cetified(),gem.getCategory_id()));
    }

    @Override
    public ArrayList<String> getAllGemIds() throws SQLException {
        return gemDAO.getAllGemIds();
    }

    public GemDTO findById(String selectedItemId) throws SQLException {
        Gem gem = gemDAO.findById(selectedItemId);
        return new GemDTO(gem.getGem_id(),gem.getGem_name(),gem.getGem_color(),gem.getSize(),gem.getPrice(),gem.getQty(),gem.getIs_cetified(),gem.getCategory_id());
    }

    @Override
    public boolean reduceQty(OrderDetailsDTO orderDetails) throws SQLException {
       return gemDAO.reduceQty(new OrderDetails(orderDetails.getOrderId(),orderDetails.getGemId(),orderDetails.getQuantity(),orderDetails.getPrice()));

    }

    @Override
    public boolean reduceeQty(SupplierOrderDetailsDTO supplierOrderDetails) throws SQLException {
        return gemDAO.reduceeQty(new SupplierOrderDetails(supplierOrderDetails.getSupplierOrderId(),supplierOrderDetails.getGemId(),supplierOrderDetails.getQuantity(),supplierOrderDetails.getPrice()));
    }
}
