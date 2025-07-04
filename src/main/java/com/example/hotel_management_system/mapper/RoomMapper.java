package com.example.hotel_management_system.mapper;

import com.example.hotel_management_system.dto.RoomDTO;
import com.example.hotel_management_system.entity.Hotel;
import com.example.hotel_management_system.entity.Room;
import com.example.hotel_management_system.enums.RoomStatus;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomDTO toDTO(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getHotel() != null ? room.getHotel().getId() : null,
                room.getRoomNumber(),
                room.getStatus(),
                room.getPrice(),
                room.getCreatedAt()
        );
    }

    public Room toEntity(RoomDTO dto) {
        Room room = new Room();
        room.setId(dto.getId());
        room.setRoomNumber(dto.getRoomNumber());
        room.setPrice(dto.getPrice());
        room.setCreatedAt(dto.getCreatedAt());

        Hotel hotel = new Hotel();
        hotel.setId(dto.getHotelId());
        room.setHotel(hotel);


        room.setStatus(dto.getStatus());

        return room;
    }
}
