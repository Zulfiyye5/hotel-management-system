package com.example.hotel_management_system.mapper;

import com.example.hotel_management_system.dto.HotelDTO;
import com.example.hotel_management_system.entity.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public HotelDTO toDto(Hotel hotel) {
        return new HotelDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getLocation(),
                hotel.getCreatedAt()
        );
    }

    public Hotel toEntity(HotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setId(dto.getId()); 
        hotel.setName(dto.getName());
        hotel.setLocation(dto.getLocation());
        hotel.setCreatedAt(dto.getCreatedAt());
        return hotel;
    }
}
