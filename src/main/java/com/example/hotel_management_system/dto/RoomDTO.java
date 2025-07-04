package com.example.hotel_management_system.dto;

import com.example.hotel_management_system.enums.RoomStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long hotelId;
    @NotNull
    private String roomNumber;
    private RoomStatus status;
    @NotNull
    private BigDecimal price;
    private LocalDateTime createdAt;
}
