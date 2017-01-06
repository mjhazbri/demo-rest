package fr.blogspot.mjhazbri.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

/**
 * Creates clean error messages from Jackson mapping or parsing exceptions.
 */
public final class JacksonMessageCleaner {

	private JacksonMessageCleaner() {
		// Utility class should not have a public constructor.
	}

	/**
	 * Extracts a clean error message from a Jackson exception. Creates a
	 * message that can be sent to public API consumer by stripping java class
	 * names and preserving error location when available.
	 * 
	 * @param e
	 *            Exception to read message from.
	 * @return error message
	 * @throws NullPointerException
	 *             if e is null
	 */
	public static String extractMessage(JsonProcessingException e) {
		if (e instanceof JsonParseException) {
			return cleanParsingErrorMessage((JsonParseException) e);
		} else if (e instanceof JsonMappingException) {
			return cleanMappingErrorMessage((JsonMappingException) e);
		} else {
			return e.getMessage();
		}
	}

	private static String cleanParsingErrorMessage(JsonParseException e) {
		return "JSON syntax error at " + formatLocation(e) + ": "
				+ e.getOriginalMessage();
	}

	private static String cleanMappingErrorMessage(JsonMappingException e) {
		return "Unknown field: " + formatFieldPath(e.getPath()) + " at "
				+ formatLocation(e);
	}

	/**
	 * @param jpe
	 *            Json exception to get location from
	 * @return Formatted location extracted from jpe
	 */
	private static String formatLocation(JsonProcessingException jpe) {
		return formatLocation(jpe.getLocation());
	}

	/**
	 * @param loc
	 *            location to format
	 * @return Formatted location or {@code "undetermined location"} if loc is
	 *         null
	 */
	private static String formatLocation(JsonLocation loc) {
		if (loc == null) {
			return "undetermined location";
		} else {
			return "line " + loc.getLineNr() + ", column " + loc.getColumnNr()
					+ " (char offset " + loc.getCharOffset() + ")";
		}
	}

	/**
	 * Formats a sequence of references as a path.
	 * 
	 * @param path
	 *            path sequence
	 * @return formatted path
	 */
	private static String formatFieldPath(List<Reference> path) {
		StringBuilder buf = new StringBuilder(64);
		for (Reference r : path) {
			buf.append(formatReference(r));
		}
		return buf.toString();
	}

	/**
	 * Format a reference being a name field or an array index.
	 * 
	 * @param ref
	 *            reference to format
	 * @return formatted reference
	 */
	private static String formatReference(Reference ref) {
		if (ref.getFieldName() != null) {
			return "[\"" + ref.getFieldName() + "\"]";
		} else if (ref.getIndex() >= 0) {
			return "[" + ref.getIndex() + "]";
		} else {
			return "[?]";
		}
	}
}