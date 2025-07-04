package com.example.hotel_management_system.dto;

import com.example.hotel_management_system.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    @NotNull
    private Long id;
    @NotNull
    private Long hotelId;

    @Size(min = 1, max = 255) @NotNull
    private String customerName;

    @Size(min = 1, max = 255) @NotNull
    private String customerEmail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus bookingStatus;
    private LocalDateTime createdAt;

}
