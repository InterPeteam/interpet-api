package com.interteam.interpet.api.repository;

import com.interteam.interpet.api.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query(value = "SELECT r FROM Rate r WHERE r.user.id=:id")
    List<Rate> findRatesByUserId(@Param("id") Long id);
}
