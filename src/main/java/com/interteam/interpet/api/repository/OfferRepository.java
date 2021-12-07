package com.interteam.interpet.api.repository;

import com.interteam.interpet.api.controller.offer.Offer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Integer> {
}
