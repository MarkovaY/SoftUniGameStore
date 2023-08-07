package com.example.softunigamestore.configuration;

import com.example.softunigamestore.models.dto.GameAddDto;
import com.example.softunigamestore.models.entities.Game;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(GameAddDto.class, Game.class).addMappings(mapper -> mapper.map(GameAddDto::getThumbnailURL, Game::setImageThumbnail));

        return modelMapper;
    }
}
