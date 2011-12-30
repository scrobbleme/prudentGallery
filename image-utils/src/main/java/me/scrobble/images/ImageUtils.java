package me.scrobble.images;

import java.util.concurrent.ExecutionException;

import me.scrobble.images.scalers.DefaultImageScaler;
import me.scrobble.images.scalers.ImageScaler;
import me.scrobble.images.scalers.ImageScalerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to help with images.
 */
public class ImageUtils {

    private static ImageScaler IMAGE_SCALER = new DefaultImageScaler();
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * Utility class constructor.
     */
    private ImageUtils() {
        // Do nothing.
    }

    /**
     * @param imageScaler
     *            The {@link ImageScaler} to use.
     */
    public static void setImageScaler(ImageScaler imageScaler) {
        IMAGE_SCALER = imageScaler;
        LOGGER.info("Use ImageScaler: {}", imageScaler.getClass());
    }

    /**
     * @param source
     *            Path to the source image.
     * @param target
     *            Path to the target image.
     * @param width
     *            Target width
     * @param height
     *            Target height
     * @param preserveAspectRation
     *            Preserve aspect ratio?
     * @throws ExecutionException
     *             Exception.
     */
    public static void resizeImage(String sourcePath, String targetPath, int width, int height,
            boolean preserveAspectRation) throws ImageScalerException {
        IMAGE_SCALER.scale(sourcePath, targetPath, width, height, preserveAspectRation);
    }
}
