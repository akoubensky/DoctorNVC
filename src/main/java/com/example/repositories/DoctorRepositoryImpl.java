package com.example.repositories;

import com.example.mappers.DoctorMapper;
import com.example.model.DoctorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class DoctorRepositoryImpl implements DoctorRepository {
    private final JdbcTemplate jdbc;
    private final DoctorMapper doctorMapper;

    private final String FIND_ALL_SQL =
            "Select d.id, d.name, p.name position From doctor d Inner Join position p On d.position_id = p.id";
    private final String FIND_DOCTOR_SQL =
            "Select d.id, d.name, p.name position From doctor d Inner Join position p On d.position_id = p.id Where d.name = ?";
    private final String FIND_POSITION_SQL =
            "Select id From position Where name = ?";
    private final String ADD_DOCTOR_SQL =
            "Insert Into doctor (name, position_id) values (?, ?)";
    private final String ADD_POSITION_SQL =
            "Insert Into position (name) values (?)";

    @Override
    public List<DoctorDto> findAll() {
        return jdbc.query(FIND_ALL_SQL, doctorMapper);
    }

    @Override
    @Transactional
    public int addDoctor(DoctorDto d) {
        List<Map<String, Object>> result = jdbc.queryForList(FIND_POSITION_SQL, d.getPosition());
        if (result.isEmpty()) {
            addPosition(d.getPosition());
            result = jdbc.queryForList(FIND_POSITION_SQL, d.getPosition());
        }
        Long pos_id = (Long)result.get(0).get("ID");
        return jdbc.update(ADD_DOCTOR_SQL, d.getName(), pos_id);
    }

    @Override
    public List<DoctorDto> findDoctors(String name) {
        return jdbc.query(FIND_DOCTOR_SQL, doctorMapper, name);
    }

    @Override
    public void addPosition(String name) {
        int number = jdbc.update(ADD_POSITION_SQL, name);
        if (number < 1) throw new RuntimeException("Cannot insert position " + name);
    }
}
