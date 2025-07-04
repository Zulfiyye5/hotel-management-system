package com.example.hotel_management_system.dao;

import com.example.hotel_management_system.entity.Hotel;

import java.util.List;
public interface HotelDAO {
    List<Hotel>  getAllHotels();
    void addHotel(Hotel hotel);
    Hotel getHotelById(Long id);
    void updateHotel(Long id, Hotel updatedHotel);
    boolean deleteHotel(Long id);

}
