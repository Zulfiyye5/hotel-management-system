package com.example.hotel_management_system.dao.impl;

import com.example.hotel_management_system.dao.BookingDAO;
import com.example.hotel_management_system.entity.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingDAOImpl implements BookingDAO {
    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }

    @Override
    public void addBooking(Booking booking) {

    }

    @Override
    public Booking getBookingById(Long id) {
        return null;
    }

    @Override
    public void deleteBooking(Long id) {

    }
}
