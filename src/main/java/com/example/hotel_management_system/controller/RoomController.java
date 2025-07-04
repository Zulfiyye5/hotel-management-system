package com.example.hotel_management_system.controller;


import com.example.hotel_management_system.dto.HotelDTO;
import com.example.hotel_management_system.dto.RoomDTO;
import com.example.hotel_management_system.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private  final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<String> addRoom( @RequestBody RoomDTO roomDTO) {
        roomService.addRoom(roomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Room is created");
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getRoomsByHotelId(@RequestParam Long hotelId) {
        List<RoomDTO> rooms = roomService.getRoomsByHotelId(hotelId);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        RoomDTO room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {

        roomService.updateRoom(id, roomDTO);

        return ResponseEntity.ok("Room updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }



}
