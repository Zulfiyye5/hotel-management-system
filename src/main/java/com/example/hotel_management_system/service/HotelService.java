package com.example.hotel_management_system.service;

import com.example.hotel_management_system.dao.HotelDAO;
import com.example.hotel_management_system.dto.HotelDTO;
import com.example.hotel_management_system.entity.Hotel;
import com.example.hotel_management_system.exception.NotFoundException;
import com.example.hotel_management_system.exception.constant.ErrorCode;
import com.example.hotel_management_system.exception.constant.ErrorMessage;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final List<Hotel> hotels = new ArrayList<>();

    @Autowired
    private HotelDAO hotelDao;

    public List<HotelDTO> getAllHotels () {
        return hotelDao.getAllHotels()
                .stream()
                .map(h -> new HotelDTO(h.getId(), h.getName(),h.getLocation(),h.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public void addHotel(Hotel hotel) {
        hotelDao.addHotel(hotel);
    }


    public Hotel getHotelById(Long id) {
        try {
            return hotelDao.getHotelById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.HOTEL_NOT_FOUND);
        }
    }

    public boolean updateHotel(Long id, Hotel updatedHotel) {
        return  hotelDao.updateHotel(id,updatedHotel);
    }
    public boolean deleteHotel(Long id){
        return  hotelDao.deleteHotel(id);
    }


}
