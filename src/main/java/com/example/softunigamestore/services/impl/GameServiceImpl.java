package com.example.softunigamestore.services.impl;

import com.example.softunigamestore.models.dto.GameAddDto;
import com.example.softunigamestore.models.entities.Game;
import com.example.softunigamestore.repositories.GameRepository;
import com.example.softunigamestore.services.GameService;
import com.example.softunigamestore.util.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Override
    public void editGame(Long id, String changeOne, String changeTwo) {

        Game game = gameRepository.findById(id).orElse(null);
        if (game != null){
            String[] changeOneInfo = changeOne.split("=");
            String[] changeTwoInfo = changeTwo.split("=");
            String changeOneField = changeOneInfo[0];
            String changeOneValue = changeOneInfo[1];

            String changeTwoField = changeTwoInfo[0];
            String changeTwoValue = changeTwoInfo[1];

            switch (changeOneField) {
                case "title" -> game.setTitle(changeOneValue);
                case "trailer" -> game.setTrailer(changeOneValue);
                case "imageThumbnail" -> game.setImageThumbnail(changeOneValue);
                case "size" -> game.setSize(Double.parseDouble(changeOneValue));
                case "price" -> game.setPrice(new BigDecimal(changeOneValue));
                case "description" -> game.setDescription(changeOneValue);
                case "releaseDate" ->
                        game.setReleaseDate(LocalDate.parse(changeOneValue, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
            switch (changeTwoField) {
                case "title" -> game.setTitle(changeTwoValue);
                case "trailer" -> game.setTrailer(changeTwoValue);
                case "imageThumbnail" -> game.setImageThumbnail(changeTwoValue);
                case "size" -> game.setSize(Double.parseDouble(changeTwoValue));
                case "price" -> game.setPrice(new BigDecimal(changeTwoValue));
                case "description" -> game.setDescription(changeTwoValue);
                case "releaseDate" ->
                        game.setReleaseDate(LocalDate.parse(changeTwoValue, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }

            gameRepository.save(game);
            System.out.printf("Edited %s%n", game.getTitle());
        }

    }

    @Override
    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id).orElse(null);

        if(game != null) {
            gameRepository.delete(game);

            System.out.printf("Deleted %s%n", game.getTitle());
        }
    }

}
