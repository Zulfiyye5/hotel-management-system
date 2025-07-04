package com.example.hotel_management_system.controller;


import com.example.hotel_management_system.dto.HotelDTO;
import com.example.hotel_management_system.entity.Hotel;
import com.example.hotel_management_system.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public ResponseEntity<List<HotelDTO>> getHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @PostMapping
    public ResponseEntity<String> addHotel( @RequestBody HotelDTO hotelDto) {
        hotelService.addHotel(hotelDto);
        return ResponseEntity.status(201).body("Hotel is created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        HotelDTO hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDto) {

         hotelService.updateHotel(id, hotelDto);

        return ResponseEntity.ok("Hotel updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    }

}
