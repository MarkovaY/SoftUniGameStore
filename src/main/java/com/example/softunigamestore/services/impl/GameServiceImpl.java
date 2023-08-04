package com.example.softunigamestore.services.impl;

import com.example.softunigamestore.models.dto.GameAddDto;
import com.example.softunigamestore.models.entities.Game;
import com.example.softunigamestore.repositories.GameRepository;
import com.example.softunigamestore.services.GameService;
import com.example.softunigamestore.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void addGame(GameAddDto gameAddDto) {

        Set<ConstraintViolation<GameAddDto>> violations = validationUtil.violation(gameAddDto);

        if(!violations.isEmpty()){
            violations.stream().map(ConstraintViolation::getMessage).forEach(System.out::println);
            return;
        }

        Game game = modelMapper.map(gameAddDto, Game.class);

        gameRepository.save(game);
        System.out.printf("Added %s%n", gameAddDto.getTitle());
    }
}
