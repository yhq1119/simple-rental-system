package model;

import exception.DateNotValidException;
import exception.PropertyNotFoundException;
import exception.RecordStatusException;
import exception.StatusUnavailableException;
import model.utilities.RentalStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalSys implements RentalSysInterface {

    List<Property> properties;

    public RentalSys() {
        this.properties = new ArrayList<>();
    }

    @Override
    public void addNewP(Property property) {
        properties.add(property);
    }

    public Property findProperty(String id) throws PropertyNotFoundException {
        for (Property p : properties) {
            if (p.propertyId().equals(id)) {
                return p;
            }
        }
        throw new PropertyNotFoundException();
    }

    private boolean hasSameID(String id) {
        boolean k = false;
        for (Property p : properties) {
            if (p.propertyId().equals(id)) {
                k = true;
                break;
            }
        }
        return k;
    }

    public Property findProperty(int streetNum,
                                 String streetName,
                                 String suburb)
            throws PropertyNotFoundException {
        for (Property p : properties) {
            if ((p.getStreetNum() == streetNum)
                    && (p.getStreetName().toUpperCase()
                    .equals(streetName.toUpperCase()))
                    && (p.getSuburb().toUpperCase()
                    .equals(suburb.toUpperCase()))) {
                return p;
            }
        }
        throw new PropertyNotFoundException();
    }

    @Override
    public void rentP(Property property, String guest,
                      Date startDate, Date estimateEndDate)
            throws StatusUnavailableException, DateNotValidException {
        if (startDate.after(estimateEndDate)) {
            throw new DateNotValidException();
        }
        if (property.status.equals(RentalStatus.AVAILABLE)) {
            property.addNewRecord(new Record(
                    property.propertyId(),
                    guest,
                    startDate,
                    estimateEndDate
            ));
            property.setStatus(RentalStatus.RENTED);
        } else {
            throw new StatusUnavailableException();
        }
    }


    @Override
    public void returnP(Property property, Date actualReturnDate)
            throws StatusUnavailableException, RecordStatusException {
        if (property.getStatus().equals(RentalStatus.RENTED)) {
            property.finishRecord(actualReturnDate);
            property.setStatus(RentalStatus.AVAILABLE);
        } else {
            throw new StatusUnavailableException();
        }
    }

    @Override
    public void maintainP(Property property)
            throws StatusUnavailableException {
        property.performM();
    }

    @Override
    public void finishMaintenanceP(Property property, Date date)
            throws StatusUnavailableException, DateNotValidException {
        if (property.getStatus().equals(RentalStatus.UNDER_MAINTAINANCE)) {
            property.finishM(date);
        }
    }

    @Override
    public void displayAll() {
        System.out.println("----------------------------");
        if (properties.size() > 0) {
            for (Property p : properties) {
                System.out.println(p.showInfo());
                p.displayRecords();
            }
        } else {

            System.out.println("    No Properties Yet");
        }
        System.out.println("----------------------------");
        System.out.println("Currently has [" + properties.size() + "] property(ies)");
    }

    @Override
    public void exit() {
        System.exit(1);
    }

    @Override
    public void createTables() {

    }

    @Override
    public void deleteTables() {

    }

    @Override
    public void insertDataIntoTables() {

    }

    @Override
    public void updateDateInTables() {

    }

    public List<Property> getProperties() {
        return properties;
    }
}
