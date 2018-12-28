package com.rovin.pokharel.myshow.model;

import java.util.ArrayList;

public class SeatBooking {
    private String seatID;
    private String seatName;
    private String seatOwner;

    public SeatBooking(String seatID, String seatName, String seatOwner) {
        this.seatID = seatID;
        this.seatName = seatName;
        this.seatOwner = seatOwner;
    }

    public String getSeatID() {
        return seatID;
    }

    public String getSeatName() {
        return seatName;
    }

    public String getSeatOwner() {
        return seatOwner;
    }

    public void setSeatID(String seatID) {
        this.seatID = seatID;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public void setSeatOwner(String seatOwner) {
        this.seatOwner = seatOwner;
    }
}
