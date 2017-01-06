package fr.blogspot.mjhazbri.tools;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.enterprise.context.Dependent;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

@Provider
@Dependent
@Consumes(value = MediaType.APPLICATION_JSON)
public class JsonReader<T> implements MessageBodyReader<T> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(JsonReader.class);

	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8").equals(
				mediaType)
				|| MediaType.APPLICATION_JSON_TYPE.equals(mediaType);
	}

	@Override
	public T readFrom(Class<T> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> multivaluedMap, InputStream input)
			throws IOException {
		JsonParser jp = JsonConstants.MAPPER.getFactory().createParser(input);
		try {
			T res = JsonConstants.MAPPER.readValue(jp, type);
			if (jp.nextToken() != null) {
				throw new BadRequestException(
						"Trailing data found after end of JSON input.");
			}
			return res;
		} catch (JsonProcessingException e) {
			LOGGER.debug("Bad json", e);
			throw new BadRequestException(
					JacksonMessageCleaner.extractMessage(e));
		}
	}

}
