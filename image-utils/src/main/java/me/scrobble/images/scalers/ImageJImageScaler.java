package me.scrobble.images.scalers;

import ij.ImagePlus;
import ij.io.Opener;
import ij.plugin.JpegWriter;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

import java.io.File;

/**
 * ImageScaler, which uses ImageJ for scaling.
 * 
 * <a href="http://rsb.info.nih.gov/ij/">http://rsb.info.nih.gov/ij/</a>
 * 
 * @author Adrian M&ouml;rchen - <a href="http://www.scrobble.me"
 *         target="_blank">http://www.scrobble.me</a>
 * 
 */
public class ImageJImageScaler implements ImageScaler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void scale(String sourcePath, String targetPath, int width, int height, boolean preserveAspectRatio)
            throws ImageScalerException {
        scaleInternal(sourcePath, targetPath, width, height, preserveAspectRatio);
    }

    /**
     * Only one image can be scaled at once.
     * 
     * @param sourcePath
     *            Path of the source image.
     * @param targetPath
     *            Path of the target image.
     * @param width
     *            The target width.
     * @param height
     *            The target height.
     * @param preserveAspectRatio
     *            <code>True</code>, if the aspect ratio should be preserved.
     * @throws ImageScalerException
     *             Exception.
     */
    private static synchronized void scaleInternal(String sourcePath, String targetPath, int width, int height,
            boolean preserveAspectRatio) throws ImageScalerException {
        try {
            File inputFile = new File(sourcePath);
            ImagePlus image = new Opener().openImage(sourcePath);
            ImageProcessor imageProcessor = (ColorProcessor) image.getProcessor();
            imageProcessor = preserveAspectRatio ? imageProcessor.resize(width) : imageProcessor.resize(width, height);
            image.close();
            ImagePlus finalImage = new ImagePlus(inputFile.getName(), imageProcessor.createImage());
            new File(targetPath).getParentFile().mkdirs();
            JpegWriter.save(finalImage, targetPath, 90);
            finalImage.close();
        } catch (Exception e) {
            throw new ImageScalerException(e);
        }
    }
}
