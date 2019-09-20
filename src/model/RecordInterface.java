package model;

import java.util.Date;

public interface RecordInterface {
    Date getStartDate();

    Date getEstimateEndDate();

    Date getActualEndDate();

    String getGuestName();

    String getSumRentFee();

    String getSumPenaltyFee();
}
