package me.scrobble.images.scalers;

/**
 * Interface for an image scaler
 */
public interface ImageScaler {
	/**
	 * @param sourcePath
	 *            Path of the source image.
	 * @param targetPath
	 *            Path to the target image.
	 * @param width
	 *            The final width.
	 * @param height
	 *            The final height.
	 * @param preserveAspectRation
	 *            <code>true</code> if the aspect ration should be reserved.
	 */
	public void scale(String sourcePath, String targetPath, int width,
			int height, boolean preserveAspectRation)
			throws ImageScalerException;
}
