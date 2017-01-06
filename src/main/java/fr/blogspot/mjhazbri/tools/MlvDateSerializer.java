package fr.blogspot.mjhazbri.tools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class MlvDateSerializer
  extends com.fasterxml.jackson.databind.ser.std.DateSerializer {

  @Override
  public void serialize(
    Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider
  ) throws IOException {
    DateFormat fmt = new SimpleDateFormat(JsonConstants.DATE_TIME_FORMAT);
    fmt.setTimeZone(JsonConstants.GMT_TIMEZONE);
    jsonGenerator.writeString(fmt.format(date));
  }

}
