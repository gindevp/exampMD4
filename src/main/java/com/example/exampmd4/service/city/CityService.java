package com.example.exampmd4.service.city;

import com.example.exampmd4.model.City;
import com.example.exampmd4.service.GeneralService;

public interface CityService extends GeneralService<City> {
    boolean existsByUserName(String name);
}
