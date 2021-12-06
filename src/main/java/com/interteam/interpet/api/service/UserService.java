package com.interteam.interpet.api.service;

import com.interteam.interpet.api.controller.auth.TokenTransfer;
import com.interteam.interpet.api.controller.user.UserDto;
import com.interteam.interpet.api.controller.user.UserMapper;
import com.interteam.interpet.api.model.City;
import com.interteam.interpet.api.model.Role;
import com.interteam.interpet.api.model.User;
import com.interteam.interpet.api.repository.CityRepository;
import com.interteam.interpet.api.repository.RoleRepository;
import com.interteam.interpet.api.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, CityRepository cityRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.cityRepository = cityRepository;
        this.roleRepository = roleRepository;
    }

    public void validateLogin(String email, String password) {
        if (email.length() == 0 || password.length() == 0) {
            throw new IllegalStateException("Puste pole są niedozwolone.");
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalStateException("Adres email jest niepoprawny.");
        }
    }

    public boolean checkIfMailExists(String email) {
        return userRepository.checkIfMailExists(email) > 0;
    }

    //TODO walidacja numeru tel, może też roli
    public void validateRegistration(String name, String surname, String phone, String email, String password) {
        if (name.length() == 0 || surname.length() == 0 ||
                email.length() == 0 || password.length() == 0) {
            throw new IllegalStateException("Puste pola są niedozwolone.");
        }

        Pattern pattern1 = Pattern.compile("^[\\p{L} .'-]+$");
        if (!pattern1.matcher(name).matches() ||
                !pattern1.matcher(surname).matches()) {
            throw new IllegalStateException("Imię lub nazwisko użytkownika jest nieprawidłowe.");
        }

        if (password.length() < 2 || name.length() < 2 ||
                surname.length() < 2) {
            throw new IllegalStateException("Długość pola jest niepoprawna.");
        }

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalStateException("Adres email jest niepoprawny.");
        }

        if (checkIfMailExists(email)) {
            throw new IllegalStateException("Ten adres email jest zajęty!");
        }
    }

    public User addNewUser(String name, String surname, String phone, String email,Long cityId, String password, Long roleId) {

        City city = cityRepository.getOne(cityId);
        Role role = roleRepository.getOne(roleId);


        User user = User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .phone(phone)
                .password(passwordEncoder.encode(password))
                .city(city)
                .role(role)
                .build();

        userRepository.save(user);
        return user;
    }

    public TokenTransfer login(String email, String password) {
        User user = userRepository.getUserByEmail(email);

        if (user == null) {
            throw new IllegalStateException("Taki użytkownik nie istnieje w bazie.");
        }

        UserMapper userMapper = new UserMapper();
        UserDto userDto = userMapper.map(user);

        verifyPassword(password, user.getPassword());
        String token = jwtService.sign(user.getEmail(), user.getRole().getName());
        return new TokenTransfer(token, userDto);
    }

    public Boolean verifyPassword(String password, String hash) {
        if (!passwordEncoder.matches(password, hash)) throw new IllegalStateException("Niepoprawne hasło!");
        return true;
    }
}
