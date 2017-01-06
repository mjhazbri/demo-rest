package fr.blogspot.mjhazbri.tools;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * A custom Date deserializer relying on {@link DatesParser} to parse a
 * date or a date time.
 */
class MlvDateDeserializer extends StdScalarDeserializer<Date> {

  private static final long serialVersionUID = 1L;

  public MlvDateDeserializer() {
    super(Date.class);
  }

  @Override
  public Date deserialize(JsonParser p, DeserializationContext context)
    throws IOException {
    return DatesParser.parseGmtDateOrNull(p.getText());
  }

}
