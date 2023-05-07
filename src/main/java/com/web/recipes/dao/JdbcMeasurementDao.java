package com.web.recipes.dao;

import com.web.recipes.model.Measurements;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcMeasurementDao implements MeasurementDao{

    private JdbcTemplate jdbcTemplate;


    public JdbcMeasurementDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Measurements retrieveMeasurementById(int id) {
        Measurements measurement = null;

        String sql = "SELECT measurement_id, measurement_name FROM measurements WHERE measurement_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if(result.next()){
            measurement = mapRowToMeasurement(result);
        }
        return measurement;
    }

    @Override
    public Measurements retrieveMeasurementByName(String measurementName) {
        Measurements measurement = null;

        String sql = "SELECT measurement_id, measurement_name FROM measurements WHERE measurement_name = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, measurementName);

        if(result.next()){
            measurement = mapRowToMeasurement(result);
        }

        return measurement;
    }


    public Measurements mapRowToMeasurement(SqlRowSet results) {
        Measurements measurements = new Measurements();

        measurements.setMeasurementId(results.getInt("measurement_id"));
        measurements.setMeasurementName(results.getString("measurement_name"));

        return measurements;

    }


}
