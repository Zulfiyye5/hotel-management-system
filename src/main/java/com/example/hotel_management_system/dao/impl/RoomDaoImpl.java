package com.example.hotel_management_system.dao.impl;

import com.example.hotel_management_system.dao.RoomDAO;
import com.example.hotel_management_system.entity.Hotel;
import com.example.hotel_management_system.entity.Room;
import com.example.hotel_management_system.enums.RoomStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RoomDaoImpl implements RoomDAO {
    private final RowMapper<Room> rowMapper = (rs,rowNum)->{
        Room room =new Room();
        room.setId(rs.getLong("id"));
        Hotel  hotel = new Hotel();
        hotel.setId(rs.getLong("hotel_id"));
        room.setHotel(hotel);
        room.setRoomNumber(rs.getString("room_number"));
        room.setPrice(rs.getBigDecimal("price"));

        room.setStatus(RoomStatus.valueOf(rs.getString("status").toUpperCase()));
        room.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

        return  room;
    };

    private  final JdbcTemplate jdbcTemplate;
    public RoomDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate  =jdbcTemplate;
    }


    @Override
    public void addRoom(Room room) {
        String status = room.getStatus().name();


        if (room.getCreatedAt() == null) {
            room.setCreatedAt(LocalDateTime.now());
        }

        String sql = "INSERT INTO rooms(hotel_id, room_number, price, status, created_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                room.getHotel().getId(),
                room.getRoomNumber(),
                room.getPrice(),
                status.toLowerCase(),
                room.getCreatedAt()
        );
    }


    @Override
    public List<Room> getRoomsByHotelId(Long hotelId) {
       String sql = "SELECT * FROM rooms WHERE hotel_id = ?";
       return  jdbcTemplate.query(sql,rowMapper,hotelId);


    }

    @Override
    public Room getRoomById(Long id) {
        String sql=  "SELECT * FROM rooms WHERE id = ?";
        return  jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    @Override
    public void updateRoom(Long id, Room updatedRoom) {
        String sql = "UPDATE rooms SET hotel_id = ?, room_number = ?, status = ?, price = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(
                sql,
                updatedRoom.getHotel().getId(),
                updatedRoom.getRoomNumber(),
                updatedRoom.getStatus().getValue(),
                updatedRoom.getPrice(),
                id
        );
        if (rowsAffected == 0) {
            throw new EmptyResultDataAccessException("No room found with id " + id, 1);
        }
    }

    @Override
    public boolean deleteRoom(Long id) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM rooms WHERE id = ?", id);
        if (rowsAffected == 0) {
            throw new EmptyResultDataAccessException("No room found with id " + id, 1);
        }
        return true;
    }
}
