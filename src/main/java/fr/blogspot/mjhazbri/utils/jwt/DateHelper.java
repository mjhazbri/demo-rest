/**
 * 
 */
package fr.blogspot.mjhazbri.utils.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author jhazbri
 *
 */
public interface DateHelper {

	public static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault())
				.toInstant());
	}
}
