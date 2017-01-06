package fr.blogspot.mjhazbri.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parses simple types and returns null instead of throwing exceptions.
 */
final class DatesParser {

  private static final Logger LOGGER =
    LoggerFactory.getLogger(DatesParser.class);

  private DatesParser() {
    // Utility class should not have public constructors.
  }

  private static Date parseGmtDateOrNull(String dateStr, String dateFormat) {
    return doParseGmtDateOrNull(dateStr, dateFormat);
  }

  static Date parseGmtDateOrNull(String dateStr) {

    Date res = parseGmtDateOrNull(dateStr, JsonConstants.DATE_TIME_FORMAT);
    if (res == null) {
      res = parseGmtDateOrNull(dateStr, JsonConstants.DATE_FORMAT);
    }
    return res;

  }

  private static Date doParseGmtDateOrNull(String dateStr, String dateFormat) {
    try {
      DateFormat df = new SimpleDateFormat(dateFormat);
      df.setTimeZone(JsonConstants.GMT_TIMEZONE);
      return df.parse(dateStr);
    } catch (ParseException e) {
      LOGGER.debug(
        "Failed to parse date: {} using format: {}", dateStr, dateFormat);
      return null;
    }
  }

}
