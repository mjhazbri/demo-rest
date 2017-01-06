/**
 * 
 */
package fr.blogspot.mjhazbri.tools;

/**
 * @author jhazbri
 *
 */
public class Error {
	private String code;
	private String message;

	private Error() {
		super();
	}

	private Error(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public static Error build(String code, String message) {
		return new Error(code, message);
	}
}
