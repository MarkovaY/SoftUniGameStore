package com.example.softunigamestore.services.impl;

import com.example.softunigamestore.models.dto.UserRegisterDto;
import com.example.softunigamestore.models.entities.User;
import com.example.softunigamestore.repositories.UserRepository;
import com.example.softunigamestore.services.UserService;
import com.example.softunigamestore.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {

        if(!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())){
            System.out.println("Password does not match!");
            return;
        }
        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.violation(userRegisterDto);

        if(!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        User user = modelMapper.map(userRegisterDto, User.class);

        userRepository.save(user);
    }
}
