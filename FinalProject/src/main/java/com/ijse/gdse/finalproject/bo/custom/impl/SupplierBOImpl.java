package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.SupplierBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.SupplierDAO;
import com.ijse.gdse.finalproject.dto.SupplierDTO;
import com.ijse.gdse.finalproject.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException {
        ArrayList<Supplier> all = supplierDAO.getAll();

        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : all) {
            SupplierDTO supplierDTO = new SupplierDTO(
                    supplier.getSupplierId(),
                    supplier.getName(),
                    supplier.getNic(),
                    supplier.getEmail(),
                    supplier.getPhone(),
                    supplier.getAddress()
            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }
    @Override
    public String getNextSupplierId() throws SQLException {
        return supplierDAO.getNext();
    }

    @Override
    public boolean saveSupplier(SupplierDTO supplier) throws SQLException {
        return supplierDAO.save(new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getNic(),supplier.getEmail(),supplier.getPhone(),supplier.getAddress()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplier) throws SQLException {
        return supplierDAO.update(new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getNic(),supplier.getEmail(),supplier.getPhone(),supplier.getAddress()));
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws SQLException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public ArrayList<String> getAllSupplierIds() throws SQLException {
        return null;
    }

    @Override
    public SupplierDTO findById(String selectedSupplierId) throws SQLException {
        Supplier supplier = supplierDAO.findById(selectedSupplierId);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getNic(),supplier.getEmail(),supplier.getPhone(),supplier.getAddress());

    }

    @Override
    public int getSupplierCount() throws SQLException {
        return supplierDAO.getSupplierCount();
    }
}
