package com.interteam.interpet.api.repository;

import com.interteam.interpet.api.controller.offer.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
    List<Application> findByOfferId(Integer offerId);
}
