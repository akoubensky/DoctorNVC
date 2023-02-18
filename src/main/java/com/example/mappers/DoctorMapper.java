package com.example.mappers;

import com.example.model.DoctorDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DoctorMapper implements RowMapper<DoctorDto> {

    @Override
    public DoctorDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DoctorDto.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .position(rs.getString("position"))
                .build();
    }
}
