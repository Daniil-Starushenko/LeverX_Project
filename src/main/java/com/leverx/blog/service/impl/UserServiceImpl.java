package com.leverx.blog.service.impl;


import com.leverx.blog.exception.EntityNotFoundException;
import com.leverx.blog.model.dto.UserDto;
import com.leverx.blog.repository.mysql.UserRepository;
import com.leverx.blog.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(Integer id) {
        return userRepository.findUserById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new EntityNotFoundException("there is no users with given id: " + id));
    }

}
