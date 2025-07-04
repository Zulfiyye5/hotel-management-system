package com.example.hotel_management_system.service;


import com.example.hotel_management_system.dao.HotelDAO;
import com.example.hotel_management_system.dao.RoomDAO;
import com.example.hotel_management_system.dto.RoomDTO;
import com.example.hotel_management_system.entity.Room;
import com.example.hotel_management_system.exception.NotFoundException;
import com.example.hotel_management_system.exception.constant.ErrorCode;
import com.example.hotel_management_system.exception.constant.ErrorMessage;
import com.example.hotel_management_system.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    private RoomMapper roomMapper;


    @Autowired
    private HotelDAO hotelDAO;

    public void addRoom(RoomDTO roomDTO) {
        Long hotelId = roomDTO.getHotelId();
        try {
            hotelDAO.getHotelById(hotelId);
            Room room = roomMapper.toEntity(roomDTO);
            roomDAO.addRoom(room);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.HOTEL_NOT_FOUND);
        }


    }

    public List<RoomDTO> getRoomsByHotelId(Long hotelId) {

        List<Room> rooms = roomDAO.getRoomsByHotelId(hotelId);

        if (rooms.isEmpty()) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.ROOMS_NOT_FOUND + hotelId);
        }

        return rooms.stream()
                .map(roomMapper::toDTO)
                .collect(Collectors.toList());


    }

    public RoomDTO getRoomById(Long id){
        try{
            Room room = roomDAO.getRoomById(id);
            return  roomMapper.toDTO(room);

        }
        catch (EmptyResultDataAccessException ex){
            throw new NotFoundException(ErrorCode.NOT_FOUND,ErrorMessage.ROOM_NOT_FOUND);

        }
    }
    public void updateRoom(Long id, RoomDTO roomDTO) {
        Long hotelId = roomDTO.getHotelId();

        try {
            hotelDAO.getHotelById(hotelId);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.HOTEL_NOT_FOUND);
        }

        try {
            Room existingRoom = roomDAO.getRoomById(id);

            Room updatedRoom = roomMapper.toEntity(roomDTO);
            updatedRoom.setId(id);
            updatedRoom.setHotel(existingRoom.getHotel());

            roomDAO.updateRoom(id, updatedRoom);
        } catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.ROOM_NOT_FOUND);
        }
    }

    public boolean deleteRoom(Long id){
        try{
            return roomDAO.deleteRoom(id);
        }
        catch (EmptyResultDataAccessException ex) {
            throw new NotFoundException(ErrorCode.NOT_FOUND, ErrorMessage.ROOM_NOT_FOUND);
        }
    }




}
