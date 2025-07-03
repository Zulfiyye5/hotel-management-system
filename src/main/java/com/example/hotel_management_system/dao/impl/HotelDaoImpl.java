package com.example.hotel_management_system.dao.impl;

import com.example.hotel_management_system.dao.HotelDAO;
import com.example.hotel_management_system.entity.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
@Repository
public class HotelDaoImpl implements HotelDAO {
    private  final RowMapper<Hotel> rowMapper = (rs,rowNum)->{
        Hotel hotel = new Hotel();
        hotel.setId(rs.getLong("id"));
        hotel.setName(rs.getString("name"));
        hotel.setLocation(rs.getString("location"));

        return  hotel;
    };

    private final JdbcTemplate jdbcTemplate;

    public HotelDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Hotel> getAllHotels() {
        String sql = "SELECT * FROM hotels";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Hotel(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("location"),
                rs.getTimestamp("created_at").toLocalDateTime()
        ));
    }

    @Override
    public void addHotel(Hotel hotel) {
        String sql = "INSERT INTO hotels(name,location) VALUES(?,?)";
        jdbcTemplate.update(sql,hotel.getName(),hotel.getLocation());

    }

    @Override
    public Hotel getHotelById(Long id) {
         String sql=  "SELECT * FROM hotels WHERE id = ?";
         return  jdbcTemplate.queryForObject(sql,rowMapper,id);

    }

    @Override
    public boolean updateHotel(Long id, Hotel updatedHotel) {
        String sql = "UPDATE hotels SET name = ?, location = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, updatedHotel.getName(), updatedHotel.getLocation(), id);
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteHotel(Long id) {
        String sql = "DELETE FROM hotels WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql,id);
        return rowsAffected>0;
    }
}
