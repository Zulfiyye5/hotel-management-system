package com.example.hotel_management_system.controller;


import com.example.hotel_management_system.dto.HotelDTO;
import com.example.hotel_management_system.entity.Hotel;
import com.example.hotel_management_system.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService2;

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelDTO> getHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {
        hotelService.addHotel(hotel);
        return "Hotel is created";
    }

    @GetMapping("/{id}")
    public Hotel getHotel(@PathVariable Long id) {
        return hotelService.getHotelById(id);

    }

    @PutMapping("/{id}")
    public String updateHotel(@PathVariable Long id, @RequestBody Hotel updatedHotel) {
        boolean updated = hotelService.updateHotel(id, updatedHotel);
        if (updated) {
            return "Hotel updated successfully";
        } else {
            return "Hotel not found";
        }
    }
}