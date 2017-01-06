package fr.blogspot.mjhazbri.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

public class XMLGregorianCalendarSerializer extends StdScalarSerializer<XMLGregorianCalendar> {

	private static final long serialVersionUID = 1L;

	public XMLGregorianCalendarSerializer() {
		super(XMLGregorianCalendar.class);
	}

	@Override
	public void serialize(XMLGregorianCalendar value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		SimpleDateFormat sdf = new SimpleDateFormat(JsonConstants.DATE_TIME_FORMAT);
		sdf.setTimeZone(JsonConstants.GMT_TIMEZONE);
		jgen.writeString(sdf.format(value.toGregorianCalendar().getTime()));
	}

}
