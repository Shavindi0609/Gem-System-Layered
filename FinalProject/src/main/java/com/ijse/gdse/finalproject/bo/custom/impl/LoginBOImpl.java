package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.LoginBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.LoginDAO;
import com.ijse.gdse.finalproject.dto.AppointmentDTO;
import com.ijse.gdse.finalproject.dto.LoginDTO;
import com.ijse.gdse.finalproject.entity.Appointment;
import com.ijse.gdse.finalproject.entity.Login;


import java.sql.SQLException;
import java.util.ArrayList;

public class LoginBOImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.LOGIN);

    @Override
    public ArrayList<LoginDTO> getAllIdAndPassword() throws SQLException {
        ArrayList<Login> all = loginDAO.getAllIdAndPassword();

        ArrayList<LoginDTO> loginDTOS = new ArrayList<>();

        for (Login login : all) {
            LoginDTO loginDTO = new LoginDTO(
                   login.getUsername(),
                    login.getPassword()
            );
            loginDTOS.add(loginDTO);
        }
        return loginDTOS;
    }
}
