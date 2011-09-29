package me.scrobble.images.scalers;

import java.io.IOException;

/**
 * ImageScaler, which uses IrfanView as scaler. This only works if
 * "i_view32.exe" is on the path.
 * 
 * <a href="http://www.irfanview.com/">http://www.irfanview.com/</a>F
 */
public class IrfanViewImageScaler implements ImageScaler {

	private final static String COMMAND_RESIZE_PRESERVE_ASPECT_RATIO = "cmd /C i_view32.exe \"@SOURCE@\" /resize=(0,@HEIGHT@) /aspectratio /resample /convert=\"@TARGET@\"";
	private final static String COMMAND_RESIZE_AND_CROP = "cmd /C i_view32.exe \"@SOURCE@\" /resize=(@WIDTH@,@HEIGHT@)  /resample /convert=\"@TARGET@\"";

	/**
	 * {@inheritDoc}
	 */
	public void scale(String sourcePath, String targetPath, int width,
			int height, boolean preserveAspectRation)
			throws ImageScalerException {
		String command = preserveAspectRation ? COMMAND_RESIZE_PRESERVE_ASPECT_RATIO
				: COMMAND_RESIZE_AND_CROP;
		command = command.replace("@SOURCE@", sourcePath);
		command = command.replace("@TARGET@", targetPath);
		command = command.replace("@WIDTH@", Integer.toString(width));
		command = command.replace("@HEIGHT@", Integer.toString(height));
		try {
			System.out.println(command);
			Process exec = Runtime.getRuntime().exec(command);
			exec.waitFor();
		} catch (IOException e) {
			throw new ImageScalerException(e);
		} catch (InterruptedException e) {
			throw new ImageScalerException(e);
		}

	}
}
