package com.example.hotel_management_system.dto;
import com.example.hotel_management_system.entity.Hotel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    @NotNull
    private Long id;
    @Size(min = 1, max = 255) @NotNull
    private String name;
    private String location;
    private LocalDateTime createdAt;


    public HotelDTO(Hotel hotel) {
    }
}