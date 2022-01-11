package com.interteam.interpet.api.repository;

import com.interteam.interpet.api.model.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Integer> {
}
