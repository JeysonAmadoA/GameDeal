package com.keons.Users.Infrastructure.Services;

import com.keons.Users.Application.Repositories.UserRepository;
import com.keons.Users.Application.Services.UserService;
import com.keons.Users.Domain.Dto.UpdateUserDto;
import com.keons.Users.Domain.Dto.UserDto;
import com.keons.Users.Domain.Entities.User;
import com.keons.Users.Domain.Exceptions.RegisterUserException;
import com.keons.Users.Domain.Exceptions.UserNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.keons.Users.Application.Mappers.UserMapper.UserDtoMapper;
import static com.keons.Auth.Domain.Helpers.AuthHelper.verifyUserAccess;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto filterUserById(Long userId) {
        verifyUserAccess(userId);
        User userFound = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userFound != null ? UserDtoMapper.toDto(userFound) : null;
    }

    @Cacheable("getAllUsers")
    @Override
    public List<UserDto> findAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(UserDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> filterUsersByEmail(String email) {
        List<User> usersByEmail = userRepository.findByEmailLike("%"+email+"%");
        return usersByEmail.stream()
                .map(UserDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> filterUsersByUsername(String username) {
        List<User> usersByName = userRepository.findByUsernameLike("%"+ username +"%");
        return usersByName.stream()
                .map(UserDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto updateUser(UpdateUserDto userDto, Long userId) {
        User userUpdated;
        try {
            User userFound = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            userUpdated = UserDtoMapper.update(userFound, userDto);
            userRepository.save(userUpdated);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return UserDtoMapper.toDto(userUpdated);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        try {
            userRepository.save(user);
        } catch (Exception exception){
            throw new RegisterUserException(exception.getMessage());
        }
    }
}
