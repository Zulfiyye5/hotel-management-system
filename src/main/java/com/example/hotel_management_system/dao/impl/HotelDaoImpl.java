package com.example.hotel_management_system.dao.impl;

import com.example.hotel_management_system.dao.HotelDAO;
import com.example.hotel_management_system.entity.Hotel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public class HotelDaoImpl implements HotelDAO {
    private final RowMapper<Hotel> rowMapper = (rs, rowNum) -> {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getLong("id"));
        hotel.setName(rs.getString("name"));
        hotel.setLocation(rs.getString("location"));
        hotel.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return hotel;
    };


    private final JdbcTemplate jdbcTemplate;

    public HotelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Hotel> getAllHotels() {
        String sql = "SELECT * FROM hotels";
        return jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    public void addHotel(Hotel hotel) {
        String sql = "INSERT INTO hotels(name, location, created_at) VALUES (?, ?, ?)";
        if (hotel.getCreatedAt() == null) {
            hotel.setCreatedAt(LocalDateTime.now());
        }
        jdbcTemplate.update(
                sql,
                hotel.getName(),
                hotel.getLocation(),
                hotel.getCreatedAt()
        );
    }


    @Override
    public Hotel getHotelById(Long id) {
         String sql=  "SELECT * FROM hotels WHERE id = ?";
         return  jdbcTemplate.queryForObject(sql,rowMapper,id);

    }

    @Override
    public void updateHotel(Long id, Hotel updatedHotel) {
        String sql = "UPDATE hotels SET name = ?, location = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, updatedHotel.getName(), updatedHotel.getLocation(), id);
        if (rowsAffected == 0) {
            throw new EmptyResultDataAccessException("No hotel found with id " + id, 1);
        }

    }

    @Override
    public boolean deleteHotel(Long id) {
        int rowsAffected = jdbcTemplate.update("DELETE FROM hotels WHERE id = ?", id);
        if (rowsAffected == 0) {
            throw new EmptyResultDataAccessException("No hotel found with id " + id, 1);
        }
        return true;
    }

}
