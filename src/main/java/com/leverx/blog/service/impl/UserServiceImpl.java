package com.leverx.blog.service.impl;


import com.leverx.blog.exception.EntityNotFoundException;
import com.leverx.blog.model.dto.UserDto;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.repository.mysql.UserRepository;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;



/**
* Optional type for throwing exception with null returning
*/
    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Integer id) {
        return userRepository.findUserById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new EntityNotFoundException("there is no users with given id: " + id));
    }

    @Override
    public Optional<User> findPresentUser(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findPresentUser(Long userId) {
        return Optional.empty();
    }

    @Override
    public List<User> getUsers(Specification<User> specification, Sort sort) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setEmail(user.getEmail());
        user.setPassword(encryptedPassword);
        log.info("Save user: {}", user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsUserByEmail(String email) {
        log.info("Chek if there is the user with the e-mail: {}", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isActiveUser(Principal principal) {
        return false;
    }

    @Override
    public String changeEmailAndGenerateJwt(User user, String newEmail) {
        return null;
    }

    @Override
    public String changePasswordAndGenerateJwt(User user, String newPassword) {
        return null;
    }
}