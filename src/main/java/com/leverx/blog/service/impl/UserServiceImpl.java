package com.leverx.blog.service.impl;


import com.leverx.blog.exception.entity.EntityNotFoundException;
import com.leverx.blog.model.dto.UserDto;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.model.entity.UserStatus;
import com.leverx.blog.repository.mysql.UserRepository;
import com.leverx.blog.security.jwt.JwtProvider;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JwtProvider jwtProvider;


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
    public UserDto findPresentUserDto(String email) {
        log.info("find userDto by email {}",  email);
        return userRepository.findUserByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new EntityNotFoundException("there is no users with given email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findPresentUser(String email) {
        log.info("find user by email: {}", email);
        return userRepository.findUserByEmail(email)
                .filter(user -> user.getUserStatus() != UserStatus.WAIT_ACTIVATING);
    }

    /**
     *
     * @param email is a key of user
     * @return <class>User</class> object or throw exception(optional)
     */
    @Override
    @Transactional(readOnly = true)
    public User findUser(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user with given email is not found"));
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
    public User updateUserStatus(User user, UserStatus status) {
        user.setUserStatus(status);
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsUserByEmail(String email) {
        log.info("Chek if there is the user with the e-mail: {}", email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public String changePasswordAndGenerateJwt(Integer userId, String newPassword) {
        log.info("Change password for user: {}", userId);

        String hashedNewPassword = passwordEncoder.encode(newPassword);
        User updateUser = userRepository.getOne(userId);
        updateUser.setPassword(hashedNewPassword);
        userRepository.save(updateUser);

        return jwtProvider.createToken(updateUser.getEmail(), updateUser.getUserStatus());
    }
}
