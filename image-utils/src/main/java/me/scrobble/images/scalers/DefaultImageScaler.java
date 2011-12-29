package me.scrobble.images.scalers;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Default class for scaling images- This will be used, if no other scaler is
 * available.
 * 
 * @author Adrian M&ouml;rchen - <a href="http://www.scrobble.me"
 *         target="_blank">http://www.scrobble.me</a>
 * 
 */
public class DefaultImageScaler implements ImageScaler {

    /**
     * {@inheritDoc}
     */
    public void scale(String sourcePath, String targetPath, int width, int height, boolean preserveAspectRatio)
            throws ImageScalerException {
        try {
            BufferedImage originalImage = ImageIO.read(new File(sourcePath));
            width = preserveAspectRatio ? (int) ((double) width * ((double) width / (double) height)) : width;
            BufferedImage resizedImage = getScaledInstance(originalImage, width, height,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
            File targetFile = new File(targetPath);
            targetFile.mkdirs();
            ImageIO.write(resizedImage, "jpeg", targetFile);
        } catch (Exception e) {
            throw new ImageScalerException(e);
        }
    }

    /**
     * From http://today.java.net/pub/a/today/2007/04/03/perils-of-image-
     * getscaledinstance.html
     * 
     * Convenience method that returns a scaled instance of the provided
     * {@code BufferedImage}.
     * 
     * @param img
     *            the original image to be scaled
     * @param targetWidth
     *            the desired width of the scaled instance, in pixels
     * @param targetHeight
     *            the desired height of the scaled instance, in pixels
     * @param hint
     *            one of the rendering hints that corresponds to
     *            {@code RenderingHints.KEY_INTERPOLATION} (e.g.
     *            {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
     *            {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
     *            {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
     * @param higherQuality
     *            if true, this method will use a multi-step scaling technique
     *            that provides higher quality than the usual one-step technique
     *            (only useful in downscaling cases, where {@code targetWidth}
     *            or {@code targetHeight} is smaller than the original
     *            dimensions, and generally only when the {@code BILINEAR} hint
     *            is specified)
     * @return a scaled version of the original {@code BufferedImage}
     */
    private BufferedImage getScaledInstance(BufferedImage img, int targetWidth, int targetHeight, Object hint,
            boolean higherQuality) {
        int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
                : BufferedImage.TYPE_INT_ARGB;
        BufferedImage ret = (BufferedImage) img;
        int w, h;
        if (higherQuality) {
            // Use multi-step technique: start with original size, then
            // scale down in multiple passes with drawImage()
            // until the target size is reached
            w = img.getWidth();
            h = img.getHeight();
        } else {
            // Use one-step technique: scale directly from original
            // size to target size with a single drawImage() call
            w = targetWidth;
            h = targetHeight;
        }

        do {
            if (higherQuality && w > targetWidth) {
                w /= 2;
                if (w < targetWidth) {
                    w = targetWidth;
                }
            }

            if (higherQuality && h > targetHeight) {
                h /= 2;
                if (h < targetHeight) {
                    h = targetHeight;
                }
            }

            BufferedImage tmp = new BufferedImage(w, h, type);
            Graphics2D g2 = tmp.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
            g2.drawImage(ret, 0, 0, w, h, null);
            g2.dispose();

            ret = tmp;
        } while (w != targetWidth || h != targetHeight);

        return ret;
    }
}
