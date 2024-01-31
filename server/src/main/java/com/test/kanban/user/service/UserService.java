package com.test.kanban.user.service;

import com.test.kanban.user.dto.UserDto;
import com.test.kanban.user.model.User;

import java.util.List;

public interface UserService {
    UserDto addUser(User user);

    UserDto updateUser(Long userId, UserDto userDto);

    List<UserDto> getAllUsers();

    User getUserById(long id);

    UserDto getUserDtoById(long id);

    void deleteUserById(long userId);

}
