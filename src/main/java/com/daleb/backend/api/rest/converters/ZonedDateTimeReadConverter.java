package com.daleb.backend.api.rest.converters;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class ZonedDateTimeReadConverter implements Converter<Date, ZonedDateTime> {
	@Override
	public ZonedDateTime convert(Date date) {
		ZoneId zoneIdCol = ZoneId.of("America/Bogota");
		return date.toInstant().atZone(zoneIdCol);
	}
}