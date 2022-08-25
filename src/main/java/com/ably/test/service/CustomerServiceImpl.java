package com.ably.test.service;

import java.util.Arrays;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.ably.test.dto.UserDTO;
import com.ably.test.entity.Role;
import com.ably.test.entity.User;
import com.ably.test.exception.DuplicatedUsernameException;
import com.ably.test.repository.RoleRepository;
import com.ably.test.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("user not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }

    @Override
    public void save(UserDTO userDTO) {
        System.out.println("sever UserService save");

        User fetchedUser = this.userRepository.findByUsername(userDTO.getUsername());

        if (fetchedUser != null) {
            System.out.println("Duplicated :: " + fetchedUser.toString() );
            throw new DuplicatedUsernameException("username already taken");
        }

        User user = new User();

        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        user.setRealName(userDTO.getRealName());
        user.setNickName(userDTO.getNickName());
        user.setEmail(userDTO.getEmail());

        Role role = this.roleRepository.findbyName("ROLE_EMPLOYEE");
        user.setRoles(Arrays.asList(role));
        userDTO.setRoles(Arrays.asList(role.getName()));

        LOGGER.debug(user.toString());
        this.userRepository.save(user);

        System.out.println(user.toString());
        userDTO.setId(user.getId());
    }

    @Override
    @Transactional
    public UserDTO findUserByUsername(String username) {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("user not found");
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRealName(user.getRealName());
        userDTO.setNickName(user.getNickName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles().stream().map(role-> role.getName())
                .collect(Collectors.toList()));

        return userDTO;
    }
}