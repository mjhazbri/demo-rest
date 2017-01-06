/**
 * 
 */
package fr.blogspot.mjhazbri.utils.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.Dependent;

/**
 * @author jhazbri
 *
 */
@Dependent
public class KeyGenerator {
	public Key generateKey() {
		String keyString = "rest-demo-key";
		Key key = new SecretKeySpec(keyString.getBytes(), 0,
				keyString.getBytes().length, "DES");
		return key;
	}
}
