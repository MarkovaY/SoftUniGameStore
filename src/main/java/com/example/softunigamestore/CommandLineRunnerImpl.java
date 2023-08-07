package com.example.softunigamestore;

import com.example.softunigamestore.models.dto.GameAddDto;
import com.example.softunigamestore.models.dto.UserLoginDto;
import com.example.softunigamestore.models.dto.UserRegisterDto;
import com.example.softunigamestore.services.GameService;
import com.example.softunigamestore.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;
    private final GameService gameService;

    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        while(true){
            System.out.println("Enter command:");
            String[] commands = bufferedReader.readLine().split("\\|");
            String command = commands[0];

            switch (command) {
                case "RegisterUser" ->
                        userService.registerUser(new UserRegisterDto(commands[1], commands[2], commands[3], commands[4]));
                case "LoginUser" -> userService.loginUser(new UserLoginDto(commands[1], commands[2]));
                case "Logout" -> userService.logout();
                case "AddGame" ->
                        gameService.addGame(new GameAddDto(commands[1], new BigDecimal(commands[2]), Double.parseDouble(commands[3]), commands[4], commands[5], commands[6], LocalDate.parse(commands[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                case "EditGame" -> gameService.editGame(Long.parseLong(commands[1]), commands[2], commands[3]);
                case "DeleteGame" -> gameService.deleteGame(Long.parseLong(commands[1]));
            }
        }
    }
}
