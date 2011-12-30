package me.scrobble.images.scalers;

/**
 * ImageScaler, which uses IrfanView as scaler. This only works if
 * "i_view32.exe" is on the path.
 * 
 * Download IrfanView from <a
 * href="http://www.irfanview.com/">http://www.irfanview.com/</a>
 * 
 * @author Adrian M&ouml;rchen - <a href="http://www.scrobble.me"
 *         target="_blank">http://www.scrobble.me</a>
 */
public class IrfanViewImageScaler extends CommandLineImageScaler {

    private final static String COMMAND_RESIZE_PRESERVE_ASPECT_RATIO = "cmd /C i_view32.exe \"@SOURCE@\" /resize=(0,@HEIGHT@) /aspectratio /resample /convert=\"@TARGET@\"";
    private final static String COMMAND_RESIZE_AND_CROP = "cmd /C i_view32.exe \"@SOURCE@\" /resize=(@WIDTH@,@HEIGHT@)  /resample /convert=\"@TARGET@\"";

    /**
     * Constructor.
     */
    public IrfanViewImageScaler() {
        super(COMMAND_RESIZE_AND_CROP, COMMAND_RESIZE_PRESERVE_ASPECT_RATIO);
    }
}
