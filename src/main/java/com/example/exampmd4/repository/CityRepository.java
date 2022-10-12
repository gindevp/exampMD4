package com.example.exampmd4.repository;

import com.example.exampmd4.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    Boolean existsByName(String name);
}
