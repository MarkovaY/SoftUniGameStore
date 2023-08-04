package com.example.softunigamestore;

import com.example.softunigamestore.models.dto.UserLoginDto;
import com.example.softunigamestore.models.dto.UserRegisterDto;
import com.example.softunigamestore.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final UserService userService;

    public CommandLineRunnerImpl(UserService userService) {
        this.userService = userService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        while(true){
            System.out.println("Enter command:");
            String[] commands = bufferedReader.readLine().split("\\|");
            String command = commands[0];

            switch (command) {
                case "RegisterUser":
                    userService.registerUser(new UserRegisterDto(commands[1], commands[2], commands[3], commands[4]));
                    break;
                case "LoginUser":
                    userService.loginUser(new UserLoginDto(commands[1], commands[2]));
                    break;
                case "Logout":
                    userService.logout();
                    break;
            }
        }
    }
}
