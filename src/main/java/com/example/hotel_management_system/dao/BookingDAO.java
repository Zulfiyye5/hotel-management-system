package com.example.hotel_management_system.dao;

import com.example.hotel_management_system.entity.Booking;

import java.util.List;

public interface BookingDAO {
    List<Booking> getAllBookings();
    void addBooking(Booking booking);
    Booking getBookingById(Long id);
    void deleteBooking(Long id);


}
