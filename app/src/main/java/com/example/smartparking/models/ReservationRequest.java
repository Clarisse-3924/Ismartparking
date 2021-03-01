package com.example.smartparking.models;

import java.sql.Time;

public class ReservationRequest {

    private int user_id;
    private int plate_No;
    private int duration_in_minutes;
    private int location;
    private int parking_slot_id;
    private Time Entry_time;
    private Time Exit_time;
    private String booking_date;

    public int getUser_id() {
        return user_id;
    }

    public int getParking_slot_id() {
        return parking_slot_id;
    }

    public int getPlate_No() {
        return plate_No;
    }

    public int getLocation() {
        return location;
    }

    public Time getEntry_time() {
        return Entry_time;
    }

    public Time getExit_time() {
        return Exit_time;
    }

    public int getDuration_in_minutes() {
        return duration_in_minutes;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPlate_No(int plate_No) {
        this.plate_No = plate_No;
    }

    public void setDuration_in_minutes(int duration_in_minutes) {
        this.duration_in_minutes = duration_in_minutes;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setParking_slot_id(int parking_slot_id) {
        this.parking_slot_id = parking_slot_id;
    }

    public void setEntry_time(Time entry_time) {
        Entry_time = entry_time;
    }

    public void setExit_time(Time exit_time) {
        Exit_time = exit_time;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }
}