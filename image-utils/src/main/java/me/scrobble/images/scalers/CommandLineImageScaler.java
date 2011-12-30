package me.scrobble.images.scalers;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ImageScaler which calls command line arguments
 * 
 * @author Adrian M&ouml;rchen - <a href="http://www.scrobble.me"
 *         target="_blank">http://www.scrobble.me</a>
 * 
 */
public class CommandLineImageScaler implements ImageScaler {

    private final String generateThumbnailCommand;
    private final String generateLargeViewCommand;

    private final static Logger LOGGER = LoggerFactory.getLogger(CommandLineImageScaler.class);

    /**
     * The following parameters can be used as placeholders within the commands:
     * <ul>
     * <li><b>@SOURCE@</b>: Path to the source file.</li>
     * <li><b>@TARGET@</b>: Path to the target file.</li>
     * <li><b>@WIDTH@</b>: Target width.</li>
     * <li><b>@TARGET@</b>: Target height.</li>
     * </ul>
     * 
     * @param generateThumbnailCommand
     *            The command for generating the thumbnail.
     * @param generateLargeViewCommand
     *            The command for generating the large view.
     */
    public CommandLineImageScaler(String generateThumbnailCommand, String generateLargeViewCommand) {
        this.generateThumbnailCommand = generateThumbnailCommand;
        this.generateLargeViewCommand = generateLargeViewCommand;
    }

    /**
     * {@inheritDoc}
     */
    public void scale(String sourcePath, String targetPath, int width, int height, boolean preserveAspectRatio)
            throws ImageScalerException {
        String command = preserveAspectRatio ? generateLargeViewCommand : generateThumbnailCommand;
        command = command.replace("@SOURCE@", sourcePath);
        command = command.replace("@TARGET@", targetPath);
        command = command.replace("@WIDTH@", Integer.toString(width));
        command = command.replace("@HEIGHT@", Integer.toString(height));
        new File(targetPath).getParentFile().mkdirs();
        try {
            LOGGER.debug("Command: {}", command);
            Process exec = Runtime.getRuntime().exec(command);
            exec.waitFor();
            LOGGER.debug("Command exited with {}", exec.exitValue());
        } catch (IOException e) {
            throw new ImageScalerException(e);
        } catch (InterruptedException e) {
            throw new ImageScalerException(e);
        }
    }
}
