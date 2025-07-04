package com.example.hotel_management_system.dao;
import java.util.List;
import com.example.hotel_management_system.entity.Room;

public interface RoomDAO {

    void addRoom(Room room);

    List<Room> getRoomsByHotelId(Long hotelId);

    Room getRoomById(Long id);

    void updateRoom(Long id, Room updatedRoom);

    boolean deleteRoom(Long id);
}