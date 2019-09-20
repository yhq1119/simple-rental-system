package model;

import exception.DateNotValidException;
import exception.RecordStatusException;
import exception.StatusUnavailableException;
import model.utilities.RentalStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Property implements PropertyInterface {

    final char REGEX_A = ':';
    final char REGEX_B = '_';
    protected int streetNum;
    protected String streetName;
    protected String suburb;
    protected String type;
    protected double dailyPrice;
    protected double dailyPenalty;
    protected int roomNum;
    protected RentalStatus status;
    protected List<Record> records;
    protected String imagePath;
    protected String description;

    public Property(int streetNum,
                    String streetName,
                    String suburb,
                    int roomNum) {
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.suburb = suburb;
        this.roomNum = roomNum;
        this.status = RentalStatus.AVAILABLE;
        this.records = new ArrayList<>();
        this.imagePath = "Not Set";
        this.description = "Not Set";
    }

    public String type() {
        if (this instanceof Apartment) {
            return "A";
        } else if (this instanceof PreSuite) {
            return "S";
        } else {
            return "Unknown";
        }
    }

    @Override
    public void performM() throws StatusUnavailableException {
        if (this.status.equals(RentalStatus.AVAILABLE)) {
            this.status = RentalStatus.UNDER_MAINTAINANCE;
        } else {
            throw new StatusUnavailableException();
        }
    }

    @Override
    public void finishM(Date date)
            throws StatusUnavailableException, DateNotValidException {
        if (this.status.equals(RentalStatus.UNDER_MAINTAINANCE)) {
            if (this instanceof PreSuite) {
                ((PreSuite) this).setLastMaintenanceDate(date);
            }
            this.status = RentalStatus.AVAILABLE;
        } else {
            throw new StatusUnavailableException();
        }
    }

    @Override
    public double getDailyPrice() {
        return this.dailyPrice;
    }

    @Override
    public double getDailyPenalty() {
        return this.dailyPenalty;
    }

    @Override
    public RentalStatus getStatus() {
        return this.status;
    }

    @Override
    public List<Record> getRecords() {
        return this.records;
    }

    public void addNewRecord(Record newRec) {
        this.records.add(0, newRec);
    }

    public void finishRecord(Date actualReturnDate)
            throws RecordStatusException {
        this.records.get(0).finish(
                actualReturnDate,
                this.dailyPrice,
                this.dailyPenalty);
    }

    public String propertyId() {
        String str = type().toUpperCase() + REGEX_B +
                this.streetNum +
                this.streetName.toUpperCase() +
                this.suburb.toUpperCase();
        return str;
    }


    private String statusFull() {
        String statusStr = "Unknown";
        switch (this.status) {
            case RENTED:
                statusStr = "Rented";
                break;
            case AVAILABLE:
                statusStr = "Available";
                break;
            case UNDER_MAINTAINANCE:
                statusStr = "Under Maintenance";
                break;
        }
        return statusStr;
    }

    @Override
    public String toString() {
        String temp =
                propertyId() + REGEX_A +
                        this.streetNum + REGEX_A +
                        this.streetName + REGEX_A +
                        this.suburb + REGEX_A +
                        this.type + REGEX_A +
                        this.roomNum + REGEX_A +
                        statusFull() + REGEX_A +
                        this.imagePath + REGEX_A +
                        this.description +
                        "";
        return temp;
    }

    private String status() {
        String str = "Unknown";
        switch (this.status) {
            case UNDER_MAINTAINANCE:
                str = "Under Maintenance";
                break;
            case AVAILABLE:
                str = "Available";
                break;
            case RENTED:
                str = "Rented";
                break;
        }
        return str;
    }

    private String format(double d) {
        return String.format("%.2f", d);
    }

    public String showInfo() {
        String regex = "\t\t";
        String newLine = "\n";
        String sp = "";
        if (this instanceof PreSuite) {
            sp = "Last Maintenance Day:" + "\t" + ((PreSuite) this).getLastMDate() + newLine +
                    "Next Maintenance Day:" + "\t" + ((PreSuite) this).getNextMDate()+newLine;
        }

        return
                "Property Id:    " + regex + propertyId() + newLine +
                        "Street Num:     " + regex + this.streetNum + newLine +
                        "Street Name:    " + regex + this.streetName + newLine +
                        "Suburb:         " + regex + this.suburb + newLine +
                        "Num Of Rooms:   " + regex + this.roomNum + newLine +
                        sp +
                        "Rental Price:   " + regex + format(this.dailyPrice) + newLine +
                        "Rental Penalty: " + regex + format(this.dailyPenalty) + newLine +
                        "Property Type:  " + regex + this.type + newLine +
                        "Property Status:" + regex + status() + newLine;


    }

    public void displayRecords() {
        if (this.records.size() < 1) {
            System.out.println("============Start===========");
            System.out.println("         No Records");
        } else {
            for (Record r : records) {
                System.out.println("==========Record[" + (records.indexOf(r) + 1) + "]========");
                System.out.println(r.showInfo());
            }

        }
        System.out.println("============End=============");
    }

    public void setStatus(RentalStatus rentalStatus) {
        this.status = rentalStatus;
    }

    public int getStreetNum() {
        return this.streetNum;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public String getSuburb() {
        return this.suburb;
    }
}
