package fr.blogspot.mjhazbri.tools;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.enterprise.context.Dependent;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Dependent
@Produces(value = MediaType.APPLICATION_JSON)
public class JsonWriter<T> implements MessageBodyWriter<T> {

	@Override
	public boolean isWriteable(Class aClass, Type type,
			Annotation[] annotations, MediaType mediaType) {
		return MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8").equals(
				mediaType)
				|| MediaType.APPLICATION_JSON_TYPE.equals(mediaType);
	}

	@Override
	public long getSize(Object o, Class aClass, Type type,
			Annotation[] annotations, MediaType mediaType) {
		// Official javadoc says:
		// As of JAX-RS 2.0, the method has been deprecated and the value
		// returned
		// by the method is ignored by a JAX-RS runtime. All MessageBodyWriter
		// implementations are advised to return -1 from the method.
		return -1;
	}

	@Override
	public void writeTo(T object, Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> multivaluedMap, OutputStream output)
			throws IOException {
		JsonConstants.MAPPER.writeValue(output, object);
	}
}
