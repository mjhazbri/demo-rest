package fr.blogspot.mjhazbri.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.util.TimeZone;

public final class JsonConstants {

  /**
   * JSON date format.
   */
  public static final String DATE_FORMAT = "yyyy-MM-dd";

  /**
   * JSON datetime format.
   */
  public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  /**
   * GMT timezone id.
   */
  private static final String GMT_TIMEZONE_ID = "GMT";

  /**
   * GMT timezone.
   */
  public static final TimeZone GMT_TIMEZONE =
    TimeZone.getTimeZone(GMT_TIMEZONE_ID);

  /**
   * Jackson object mapper.
   */
  public static final ObjectMapper MAPPER = new ObjectMapper()
    .setPropertyNamingStrategy(
      PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES
    )
    .registerModule(new JacksonModule());


  private JsonConstants() {
    // Utility classes should not have a public constructor.
  }
}
