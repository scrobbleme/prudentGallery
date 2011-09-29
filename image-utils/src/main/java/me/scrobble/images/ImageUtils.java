package me.scrobble.images;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.imageio.ImageIO;

import me.scrobble.images.scalers.ImageScalerException;
import me.scrobble.images.scalers.IrfanViewImageScaler;

/**
 * Utility class to help with images.
 */
public class ImageUtils {

	private static final IrfanViewImageScaler IRFAN_VIEW_IMAGE_SCALER = new IrfanViewImageScaler();

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
	public static void resizeImage(String sourcePath, String targetPath,
			int width, int height, boolean preserveAspectRation)
			throws ImageScalerException {
		IRFAN_VIEW_IMAGE_SCALER.scale(sourcePath, targetPath, width, height,
				preserveAspectRation);
	}

	/**
	 * Loads and image from a path and resizes it.
	 * 
	 * @param path
	 *            The path to the image.
	 * @param width
	 *            The new width.
	 * @param height
	 *            The new height
	 * @param preserveAspectRatio
	 *            If true, the aspect ratio of the image will be reserved, else
	 *            not.
	 * @return The new image as byte array in jpeg format.
	 * @throws IOException
	 *             Exception
	 */
	public static byte[] loadAndResizeImage(String path, int width, int height,
			boolean preserveAspectRatio) throws IOException {
		BufferedImage originalImage = ImageIO.read(new File(path));
		width = preserveAspectRatio ? (int) ((double) width * ((double) width / (double) height))
				: width;
		BufferedImage resizedImage = getScaledInstance(originalImage, width,
				height, RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(resizedImage, "jpeg", outputStream);
		return outputStream.toByteArray();
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
	public static BufferedImage getScaledInstance(BufferedImage img,
			int targetWidth, int targetHeight, Object hint,
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
