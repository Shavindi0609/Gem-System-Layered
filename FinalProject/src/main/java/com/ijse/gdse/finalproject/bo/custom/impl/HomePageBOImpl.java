package com.ijse.gdse.finalproject.bo.custom.impl;

import com.ijse.gdse.finalproject.bo.custom.HomePageBO;
import com.ijse.gdse.finalproject.dao.DAOFactory;
import com.ijse.gdse.finalproject.dao.custom.*;
import com.ijse.gdse.finalproject.dto.AppointmentDTO;
import com.ijse.gdse.finalproject.entity.Appointment;

import java.sql.SQLException;
import java.util.ArrayList;

public class HomePageBOImpl implements HomePageBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.APPOINTMENT);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public int getCustomerCount() throws SQLException {
        return customerDAO.getCustomerCount();
    }

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

    @Override
    public int getTotCount() throws SQLException {
        return queryDAO.getTotCount();
    }

    @Override
    public int getEmployeeCount() throws SQLException {
        return employeeDAO.getEmployeeCount();
    }

    @Override
    public int getSupplierCount() throws SQLException {
        return supplierDAO.getSupplierCount();
    }
}
