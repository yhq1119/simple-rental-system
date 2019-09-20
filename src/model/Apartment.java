package model;

import exception.RoomNumberException;

public class Apartment extends Property {

    public Apartment(int streetNum,
                     String streetName,
                     String suburb,
                     int roomNum
                    ) throws RoomNumberException {
        super(streetNum, streetName, suburb, roomNum);
        this.type = "Apartment";
        switch (roomNum) {
            case 1:
                this.dailyPrice = 143;
                break;
            case 2:
                this.dailyPrice = 210;
                break;
            case 3:
                this.dailyPrice = 319;
                break;
            default:
                throw new RoomNumberException();
        }
        this.dailyPenalty = this.dailyPrice * 1.15;
    }
}
