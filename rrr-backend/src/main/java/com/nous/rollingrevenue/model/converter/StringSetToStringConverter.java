package com.nous.rollingrevenue.model.converter;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StringSetToStringConverter implements AttributeConverter<Set<String>, String> {

	public static final String DELIMITER = ", ";

	@Override
	public String convertToDatabaseColumn(Set<String> attribute) {
		if (attribute == null) {
			return null;
		}
		return attribute.stream().collect(Collectors.joining(DELIMITER));

	}

	@Override
	public Set<String> convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return Collections.emptySet();
		}
		return Stream.of(dbData.split(DELIMITER)).collect(Collectors.toSet());
	}

}
