package me.scrobble.images.scalers;

/**
 * Exception thrown from the ImageScaler.
 */
public class ImageScalerException extends Exception {

	private static final long serialVersionUID = 3361905134856880104L;

	/**
	 * @param cause
	 *            The root exception.
	 */
	public ImageScalerException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param msg
	 *            The message.
	 * @param cause
	 *            The root exception.
	 */
	public ImageScalerException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
