package com.interteam.interpet.api.repository;

import com.interteam.interpet.api.controller.user.UserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDto, Integer> {
    @Query("SELECT t FROM Thing t WHERE t.fooIn = ?1 AND t.bar = ?2")
    static UserDto find(String email) //todo
    {
        return null;
    }
}
