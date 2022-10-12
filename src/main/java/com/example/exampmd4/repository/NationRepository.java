package com.example.exampmd4.repository;

import com.example.exampmd4.model.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationRepository extends JpaRepository<Nation,Long> {
Boolean existsByName(String name);
}
