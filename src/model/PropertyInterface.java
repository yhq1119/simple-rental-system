package model;

import exception.DateNotValidException;
import exception.RoomNumberException;
import exception.StatusUnavailableException;
import model.utilities.RentalStatus;

import java.util.Date;
import java.util.List;

public interface PropertyInterface {

    void performM() throws StatusUnavailableException;

    void finishM(Date date) throws StatusUnavailableException, DateNotValidException;

    double getDailyPrice() throws RoomNumberException;

    double getDailyPenalty();

    RentalStatus getStatus();

    List<Record> getRecords();

}
