package com.test.kanban.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.test.kanban.exception.*;
import com.test.kanban.user.dto.UserDto;
import com.test.kanban.user.mapper.UserMapper;
import com.test.kanban.user.model.User;
import com.test.kanban.user.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto addUser(User user) {
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Transactional
    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id %d не найден", userId)));
        int i = 0;
        if ((userDto.getName() != null) && (!userDto.getName().isBlank())) {
            i++;
            user.setName(userDto.getName());
        }
        if ((userDto.getEmail() != null) && (!userDto.getEmail().isBlank())) {
            i++;
            user.setEmail(userDto.getEmail());
        }
        if (i > 0) {
            userRepository.save(user);
        }
        return UserMapper.toUserDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return UserMapper.toUserDtoList(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с таким id %d не найден", id)));
    }

    @Transactional
    public UserDto getUserDtoById(long id) {
        return UserMapper.toUserDto(getUserById(id));
    }

    @Transactional
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }
}
