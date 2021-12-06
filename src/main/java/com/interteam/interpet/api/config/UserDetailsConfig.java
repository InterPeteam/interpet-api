//package com.interteam.interpet.api.config;
//
//
//import com.interteam.interpet.api.model.User;
//import com.interteam.interpet.api.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsConfig implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserRepository userRepository = new UserRepository();
//        User user = userRepository.find(email);
//        if (user == null) {
//            throw new UsernameNotFoundException(email);
//        }
//        return org.springframework.security.core.userdetails.User.withUsername(email)
//                .password(userRepository.getPassword(email))
//                .authorities(user.getRole()).build();
//    }
//}
