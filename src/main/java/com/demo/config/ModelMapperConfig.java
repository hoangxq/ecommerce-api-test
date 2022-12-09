package com.demo.config;

import com.demo.model.CartItem;
import com.demo.model.OrderItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.typeMap(CartItem.class, OrderItem.class)
                .addMappings(mapping -> mapping.skip(OrderItem::setId));
        return mapper;
    }
}