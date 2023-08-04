package com.example.softunigamestore.configuration;

import com.example.softunigamestore.models.dto.GameAddDto;
import com.example.softunigamestore.models.entities.Game;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(GameAddDto.class, Game.class).addMappings(mapper -> mapper.map(GameAddDto::getThumbnailURL, Game::setImageThumbnail));

        Converter<String, LocalDate> localDateConverter = new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                return mappingContext.getSource() == null ? LocalDate.now() : LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        };

        modelMapper.addConverter(localDateConverter);
        return modelMapper;
    }
}
