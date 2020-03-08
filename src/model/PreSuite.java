package model;

import exception.DateNotValidException;
import model.utilities.DateTime;

import java.util.Date;

public class PreSuite extends Property {

    private Date lastMaintenanceDate;

    public PreSuite(int streetNum,
                    String streetName,
                    String suburb,Date lastMaintenanceDate) {
        super(streetNum, streetName, suburb, 3);
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.type = "Premium Suite";
        this.dailyPrice = 554;
        this.dailyPenalty = 662;
    }

    public Date nextMaintenanceDate() {
        return DateTime.addDay(this.lastMaintenanceDate, 15);
    }

    public boolean isValidMaintenanceDate(Date date) {
        return (!date.after(nextMaintenanceDate()));
    }

    public void setLastMaintenanceDate(Date date) throws DateNotValidException {
        if (isValidMaintenanceDate(date)){
        this.lastMaintenanceDate = date;}
        else {
            throw new DateNotValidException();
        }
    }

    public Date getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public String getLastMDate() {
        return DateTime.simpleDateWithSlash(this.lastMaintenanceDate);
    }

    public String getNextMDate() {
        return DateTime.simpleDateWithSlash(nextMaintenanceDate());
    }
}
