package com.example.hotel_management_system.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingStatus {
    ACTIVE("active"),
    CANCELLED("cancelled");

    private final String value;

     BookingStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static RoomStatus fromString(String key) {
        for (RoomStatus status : RoomStatus.values()) {
            if (status.getValue().equalsIgnoreCase(key)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid BookingStatus: " + key);
    }
}
