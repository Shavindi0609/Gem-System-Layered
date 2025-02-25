package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.AppointmentBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.AppointmentDAO;
import com.ijse.gdse.finalproject.dto.AppointmentDTO;
import com.ijse.gdse.finalproject.dto.Gem2DTO;
import com.ijse.gdse.finalproject.entity.Appointment;
import com.ijse.gdse.finalproject.entity.Gem2;


import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBOImpl implements AppointmentBO {

    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.APPOINTMENT);

    @Override
    public ArrayList<AppointmentDTO> getAllAppointment() throws SQLException {
        ArrayList<Appointment> all = appointmentDAO.getAll();

        ArrayList<AppointmentDTO> appointmentDTOS = new ArrayList<>();

        for (Appointment appointment : all) {
            AppointmentDTO appointmentDTO = new AppointmentDTO(
                    appointment.getAppointmentId(),
                    appointment.getCustomerId(),
                    appointment.getDate(),
                    appointment.getTime(),
                    appointment.getIsAttendance()
            );
            appointmentDTOS.add(appointmentDTO);
        }
        return appointmentDTOS;
    }

//    @Override
//    public ArrayList<Gem2DTO> getAllCategory() throws SQLException {
//        ArrayList<Gem2> all = gemDAO.getAll();
//
//        ArrayList<Gem2DTO> gem2DTOS = new ArrayList<>();
//
//        for (Gem2 gem2 : all) {
//            Gem2DTO gem2DTO = new Gem2DTO(
//                    gem2.getCategory_id(),
//                    gem2.g
//            );
//            gem2DTOS.add(gem2DTO);
//        }
//        return gem2DTOS;
//    }

    @Override
    public String getNextAppointmentId() throws SQLException {
        return appointmentDAO.getNext();
    }

    @Override
    public boolean saveAppointment(AppointmentDTO appointment) throws SQLException {
        return appointmentDAO.save(new Appointment(appointment.getAppointmentId(),appointment.getCustomerId(),appointment.getDate(),appointment.getTime(),appointment.getIsAttendance()));
    }

    @Override
    public boolean deleteAppointment(String appointmentId) throws SQLException {
        return appointmentDAO.delete(appointmentId);
    }

    @Override
    public boolean updateAppointment(AppointmentDTO appointment) throws SQLException {
        return appointmentDAO.update(new Appointment(appointment.getAppointmentId(),appointment.getCustomerId(),appointment.getDate(),appointment.getTime(),appointment.getIsAttendance()));
    }
}
