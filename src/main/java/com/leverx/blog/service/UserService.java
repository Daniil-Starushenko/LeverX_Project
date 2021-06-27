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

    Optional<User> findPresentUser(Long userId);

    User saveUser(User user);

    User updateUser(User user, UserDto userDto);

    User updateUserStatus(User user, UserStatus status);

    void deleteUser(User user);

    boolean existsUserByEmail(String email);

    boolean isActiveUser(Principal principal);

    String changePasswordAndGenerateJwt(Integer userId, String newPassword);

}
