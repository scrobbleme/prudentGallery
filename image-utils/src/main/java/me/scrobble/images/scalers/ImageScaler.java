package me.scrobble.images.scalers;

/**
 * Interface for an image scaler
 * 
 * @author Adrian M&ouml;rchen - <a href="http://www.scrobble.me"
 *         target="_blank">http://www.scrobble.me</a>
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
     * @param preserveAspectRatio
     *            <code>true</code> if the aspect ratio should be reserved.
     */
    public void scale(String sourcePath, String targetPath, int width, int height, boolean preserveAspectRatio)
            throws ImageScalerException;
}
