package com.interteam.interpet.api.repository;

import com.interteam.interpet.api.config.Encoder;
import com.interteam.interpet.api.model.MUser;

public class UserRepository {
    public MUser find(String email) {
        //TODO database connection
        return null;
    }

    public String getPassword(String email) {
        //TODO database connection
        return "";
    }

    public void register(String email, String password, String name, String surname, String city) {
        String hash = new Encoder().encode(password);
        //TODO database connection
    }
}
