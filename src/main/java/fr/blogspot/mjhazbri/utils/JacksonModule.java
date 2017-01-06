package fr.blogspot.mjhazbri.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;

import java.math.BigInteger;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * A Jackson module allowing to use custom behavior. (Initially created to
 * managed dates deserialization.)
 */
class JacksonModule extends Module {

	private static final Deserializers deserializers = new MailevaDeserializers();
	private static final Serializers serializers = new MailevaSerializers();

	@Override
	public String getModuleName() {
		return "Jackson Module";
	}

	@Override
	public Version version() {
		return Version.unknownVersion();
	}

	@Override
	public void setupModule(SetupContext context) {
		context.addDeserializers(deserializers);
		context.addSerializers(serializers);
		context.setMixInAnnotations(XMLGregorianCalendar.class, MixIn.class);
	}

	/**
	 * Static inner class to wrap our custom deserializers.
	 */
	private static class MailevaDeserializers extends Deserializers.Base {

		private static final MlvDateDeserializer DATE_DESERIALIZER = new MlvDateDeserializer();

		@Override
		public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config,
				BeanDescription beanDesc) throws JsonMappingException {

			if (Date.class.isAssignableFrom(type.getRawClass())) {
				return DATE_DESERIALIZER;
			} else {
				return super.findBeanDeserializer(type, config, beanDesc);
				// Or return null, but calling super is more "long term" ;)
			}
		}

	}

	/**
	 * Static inner class to wrap our custom serializers.
	 */
	private static class MailevaSerializers extends Serializers.Base {

		private static final XMLGregorianCalendarSerializer XML_GREGORIAN_CALENDAR_SERIALIZER = new XMLGregorianCalendarSerializer();
		private static final MlvDateSerializer DATE_SERIALIZER = new MlvDateSerializer();

		@Override
		public JsonSerializer<?> findSerializer(SerializationConfig serialCfg, JavaType type,
				BeanDescription beanDesc) {
			if (Date.class.isAssignableFrom(type.getRawClass())) {
				return DATE_SERIALIZER;
			} else if (XMLGregorianCalendar.class.isAssignableFrom(type.getRawClass())) {
				return XML_GREGORIAN_CALENDAR_SERIALIZER;
			} else {
				return super.findSerializer(serialCfg, type, beanDesc);
			}
		}

	}

	public static interface MixIn {
		@JsonIgnore
		public void setYear(BigInteger year);
	}
}
