package me.scrobble.images;

import java.util.concurrent.ExecutionException;

import me.scrobble.images.scalers.ImageScaler;
import me.scrobble.images.scalers.ImageScalerException;
import me.scrobble.images.scalers.IrfanViewImageScaler;

/**
 * Utility class to help with images.
 */
public class ImageUtils {

    private static final ImageScaler IMAGE_SCALER = new IrfanViewImageScaler();

    /**
     * Utility class constructor.
     */
    private ImageUtils() {
        // Do nothing
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
