package com.wynk.test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Serializer utility class
 */
public class JsonDeserializer {

	private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule())
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static <T> T deserialize(String json, Class<T> clazz) {
		try {
			return MAPPER.readValue(json, clazz);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T deserialize(String json, TypeReference<T> typeReference) {
		try {
			return MAPPER.readValue(json, typeReference);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
