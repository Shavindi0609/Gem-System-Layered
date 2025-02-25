package com.ijse.gdse.finalproject.dao.custom.impl;

import com.ijse.gdse.finalproject.dao.custom.QueryDAO;
import com.ijse.gdse.finalproject.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QueryDAOImpl implements QueryDAO {


    public int getTotCount() throws SQLException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String d1 = LocalDate.now().format(dateTimeFormatter);

        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "select SUM(s.total_amount) from payment s join orders o on s.payment_id = o.payment_id where order_date = ? ";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, d1);
        ResultSet rst = statement.executeQuery();
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;

    }
}
