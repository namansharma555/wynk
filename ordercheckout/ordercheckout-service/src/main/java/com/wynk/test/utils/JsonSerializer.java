package com.wynk.test.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Serializer utility class
 *
 */
public class JsonSerializer {

	private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule())
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static String serialize(Object object) {
		if (object == null) {
			return null;
		}
		try {
			return MAPPER.writeValueAsString(object);
		} catch (final Exception e) {
			// log.error("Error while serializing the object to json.", e);
			throw new RuntimeException(e);
		}
	}
}
