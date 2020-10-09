package com.daleb.backend.api.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.daleb.backend.api.rest.converters.ZonedDateTimeReadConverter;
import com.daleb.backend.api.rest.converters.ZonedDateTimeWriteConverter;

@SpringBootApplication
public class BackendApiRestApplication {
	@Bean
	public MongoCustomConversions customConversions() {
		List<Converter<?, ?>> converters = new ArrayList<>();
		converters.add(new ZonedDateTimeReadConverter());
		converters.add(new ZonedDateTimeWriteConverter());
		return new MongoCustomConversions(converters);
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApiRestApplication.class, args);
	}

}
