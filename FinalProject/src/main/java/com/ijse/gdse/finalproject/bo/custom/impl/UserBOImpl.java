package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.UserBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.UserDAO;
import com.ijse.gdse.finalproject.dto.UserDTO;
import com.ijse.gdse.finalproject.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);

    @Override
    public String getNextUserId() throws SQLException {
        return userDAO.getNext();
    }


    @Override
    public boolean saveUser(UserDTO user) throws SQLException {
        return userDAO.save(new User(user.getUserId(),user.getUsername(),user.getRole(),user.getPassword()));
    }

    @Override
    public UserDTO getUserDetails(String username) throws SQLException {
        User user = userDAO.getUserDetails(username);
        return new UserDTO(user.getUserId(),user.getUsername(),user.getRole(),user.getPassword());

    }

    @Override
    public ArrayList<String> getAllUserIds() throws SQLException {
        return userDAO.getAllUserIds();
    }
}
