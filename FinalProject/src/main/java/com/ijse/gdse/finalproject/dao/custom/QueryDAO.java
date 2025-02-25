package com.ijse.gdse.finalproject.dao.custom;

import com.ijse.gdse.finalproject.dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    public int getTotCount() throws SQLException;

}
