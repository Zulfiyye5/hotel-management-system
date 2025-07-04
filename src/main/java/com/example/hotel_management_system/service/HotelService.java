package com.example.hotel_management_system.service;

import com.example.hotel_management_system.dao.HotelDAO;
import com.example.hotel_management_system.dto.HotelDTO;
import com.example.hotel_management_system.entity.Hotel;
import com.example.hotel_management_system.exception.NotFoundException;
import com.example.hotel_management_system.exception.constant.ErrorCode;
import com.example.hotel_management_system.exception.constant.ErrorMessage;
import com.example.hotel_management_system.mapper.HotelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelDAO hotelDao;

    @Autowired
    private HotelMapper hotelMapper;

    public List<HotelDTO> getAllHotels() {
        return hotelDao.getAllHotels()
                .stream()
                .map(hotelMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addHotel(HotelDTO hotelDto) {
        hotelDao.addHotel(hotelMapper.toEntity(hotelDto));
    }

    public HotelDTO getHotelById(Long id) {

        try {
            Hotel hotel = hotelDao.getHotelById(id);
            return hotelMapper.toDto(hotel);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.HOTEL_NOT_FOUND);
        }
    }

    public void updateHotel(Long id, HotelDTO hotelDto) {

        try{
            getHotelById(id);
            hotelDao.updateHotel(id, hotelMapper.toEntity(hotelDto));
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.HOTEL_NOT_FOUND);
        }

    }

    public boolean deleteHotel(Long id) {
        try{
            return hotelDao.deleteHotel(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.HOTEL_NOT_FOUND);
        }
    }
}


