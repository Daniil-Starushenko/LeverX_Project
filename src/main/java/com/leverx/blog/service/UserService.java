package com.leverx.blog.service;

import com.leverx.blog.model.dto.UserDto;
import com.leverx.blog.model.entity.User;
import com.leverx.blog.model.entity.UserStatus;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    UserDto getUser(Integer id);

    UserDto findPresentUserDto(String email);

    Optional<User> findPresentUser(String email);

    User findUser(String email);

    User saveUser(User user);

    User updateUserStatus(User user, UserStatus status);

    boolean existsUserByEmail(String email);

    String changePasswordAndGenerateJwt(Integer userId, String newPassword);

    User updateUser(User user, UserDto userDto);

    void deleteUser(User user);

    boolean isActiveUser(Principal principal);

    Optional<User> findPresentUser(Long userId);

}
