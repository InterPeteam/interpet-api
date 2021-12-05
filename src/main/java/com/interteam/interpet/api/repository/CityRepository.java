package com.interteam.interpet.api.repository;

import com.interteam.interpet.api.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
