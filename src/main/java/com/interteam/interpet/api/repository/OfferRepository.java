package com.interteam.interpet.api.repository;

import com.interteam.interpet.api.controller.offer.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {
    List<Offer> findByEndDateGreaterThan(Date date);
    List<Offer> findByUserId(Integer userId);
}
