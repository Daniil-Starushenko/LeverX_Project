package com.leverx.blog.service;

import com.leverx.blog.model.dto.UserDto;
import com.leverx.blog.model.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto getUser(Integer id);

    Optional<User> findPresentUser(String email);

    Optional<User> findPresentUser(Long userId);

    List<User> getUsers(Specification<User> specification, Sort sort);

    User saveUser(User user);

    User updateUser(User user, UserDto userDto);

    void deleteUser(User user);

    boolean existsUserByEmail(String email);

    boolean isActiveUser(Principal principal);

    String changeEmailAndGenerateJwt(User user, String newEmail);

    String changePasswordAndGenerateJwt(User user, String newPassword);

}
