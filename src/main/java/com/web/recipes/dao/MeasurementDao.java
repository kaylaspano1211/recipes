package com.web.recipes.dao;

import com.web.recipes.model.Measurements;

public interface MeasurementDao {

    Measurements retrieveMeasurementById (int id);

    Measurements retrieveMeasurementByName (String measurementName);
}
