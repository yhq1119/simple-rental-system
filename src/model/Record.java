package model;

import exception.RecordStatusException;
import model.utilities.DateTime;

import java.util.Date;

public class Record implements RecordInterface{

    final String regex = "\t\t\t\t";
    final String newLine = "\n";
    final String regex1 = "_";
    final String regex2 = ":";
    String propertyId;
    String guestName;
    Date startDate;
    Date estimateReturnDate;
    Date actualReturnDate;
    String rentFee;
    String penaltyFee;

    public Record(String propertyId,
                  String guestName,
                  Date startDate,
                  Date estimateReturnDate) {
        this.propertyId = propertyId;
        this.guestName = guestName;
        this.startDate = startDate;
        this.estimateReturnDate = estimateReturnDate;
        this.actualReturnDate = null;
        this.rentFee = this.penaltyFee = "0.00";
    }

    public void finish(Date actualReturnDate,
                       double dailyPrice, double penaltyPrice)
            throws RecordStatusException {
        if (isFinished()){
            throw new RecordStatusException();
        }
        this.actualReturnDate = actualReturnDate;
        if (getActualReturnDay()<=getEstimateDay()){
        this.rentFee = String.format("%.2f",
                getActualReturnDay() * dailyPrice);}
        else  {
            this.rentFee = String.format("%.2f",
                    getEstimateDay() * dailyPrice);
            this.penaltyFee = String.format("%.2f",
                    (getActualReturnDay() - getEstimateDay()) * penaltyPrice);
        }
    }

    int getEstimateDay() {
        return DateTime.daysBetweenDates(
                startDate, estimateReturnDate);
    }

    int getActualReturnDay() {
        return DateTime.daysBetweenDates(
                startDate, actualReturnDate);
    }

    boolean isFinished() {
        return this.actualReturnDate != null;
    }


    String recordId() {
        return propertyId + regex1 +
                guestName + regex1 +
                DateTime.simpleDate(startDate);
    }


    @Override
    public String toString() {
        return recordId() + regex2 +
                dateString(startDate) + regex2 +
                dateString(estimateReturnDate) + regex2 +
                dateString(actualReturnDate) + regex2 +
                this.rentFee + regex2 +
                this.penaltyFee;
    }

    String dateString(Date date) {
        if (date != null) {
            return DateTime.simpleDateWithSlash(date);
        }
        return "none";
    }

    String secondInfo() {
        if (this.actualReturnDate != null) {
            return "Actual Return Date:" + "  \t" +
                    dateString(this.actualReturnDate) + newLine +
                    "Rental Fee:" + regex + this.rentFee + newLine +
                    "Penalty Fee:" + "\t\t\t" + this.penaltyFee;
        } else {
            return "Actual Return Date:" + "  \t" + dateString(null);
        }
    }

    public String showInfo() {
        return

                "Record Id:" + regex + recordId() + newLine +
                "Guest Name:" + regex + this.guestName + newLine +
                "Start Date:" + regex + dateString(this.startDate) + newLine +
                "Estimated Return Date:" + "  " +
                dateString(this.estimateReturnDate) + newLine +
                secondInfo();


    }

    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    @Override
    public Date getEstimateEndDate() {
        return this.estimateReturnDate;
    }

    @Override
    public Date getActualEndDate() {
        return this.actualReturnDate;
    }

    @Override
    public String getGuestName() {
        return this.guestName;
    }

    @Override
    public String getSumRentFee() {
        return this.rentFee;
    }

    @Override
    public String getSumPenaltyFee() {
        return this.penaltyFee;
    }
}
