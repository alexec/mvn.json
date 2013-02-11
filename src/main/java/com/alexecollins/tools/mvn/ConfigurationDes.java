package com.alexecollins.tools.mvn;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class ConfigurationDes extends JsonDeserializer<Object> {
	@Override
	public Object deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		return null;
	}
}
