package com.example.hotel_management_system.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum RoomStatus {
    BOOKED("booked"),
    AVAILABLE("available");

    private final String value;

    RoomStatus(String value) {
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
        throw new IllegalArgumentException("Invalid RoomStatus: " + key);
    }
}
