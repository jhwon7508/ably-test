package com.ably.test.service;

import com.ably.test.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
    void save(UserDTO userDTO);

    UserDTO findUserByUsername(String username);
}