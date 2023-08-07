package com.example.softunigamestore.services;

import com.example.softunigamestore.models.dto.GameAddDto;

import java.math.BigDecimal;

public interface GameService {
    void addGame(GameAddDto gameAddDto);

    void editGame(Long id, String changeOne, String changeTwo);

    void deleteGame(Long id);
}
