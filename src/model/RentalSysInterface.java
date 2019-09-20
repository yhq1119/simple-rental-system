package model;

import exception.DateNotValidException;
import exception.RecordStatusException;
import exception.StatusUnavailableException;

import java.util.Date;

public interface RentalSysInterface {
    void addNewP(Property property);

    void rentP(Property property,String guest,
               Date startDate,Date estimateEndDate)
            throws StatusUnavailableException, DateNotValidException;

    void returnP(Property property, Date actualReturnDate)
            throws StatusUnavailableException, RecordStatusException;

    void maintainP(Property property)
            throws StatusUnavailableException;

    void finishMaintenanceP(Property property, Date date)
            throws StatusUnavailableException, DateNotValidException;

    void displayAll();

    void exit();

    void createTables();

    void deleteTables();

    void insertDataIntoTables();

    void updateDateInTables();
}
