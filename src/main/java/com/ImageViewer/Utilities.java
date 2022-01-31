package com.ImageViewer;

import java.io.File;

/**
 * Utilities class.
 *
 * @author Dean Lonergan (20092570)
 */
public class Utilities {

    /**
     * Method to test if a file is a valid image type.
     * @param file input file.
     * @return true if the file has a .jpg or .png suffix, false otherwise.
     */
    public static Boolean validImageFile(File file) {
        return (file != null) && (file.getName().contains(".jpg") || file.getName().contains(".png"));
    }

    /**
     * Method to take in a file and calculate its size in megabytes and present it as a String.
     * @param file input file.
     * @return the size of the file in MB as a String.
     */
    public static String fileSizeMB(File file) {
        String fileSize = String.valueOf((double) file.length() / (1024*1024));
        return fileSize.substring(0,4) + "MB";
    }

    /**
     * Method that resets any variables that have been changed by the user.
     */
    public static void resetAdjustments() {
        AdjustmentController.setHue(null);
        AdjustmentController.setSaturation(null);
        AdjustmentController.setBrightness(null);
        AdjustmentController.adjustments();
    }

    /**
     * Method that returns a double to a specified number of decimal places, multiplied by 100.
     * Used to convert a double between 0.0 and 1.0 into a more user-friendly 0.0 to 100.0.
     * @param value input double.
     * @param places the desired number of decimal places.
     * @return the double rounded to the desired decimal place, and multiplied by 100.
     */
    public static double roundAndMultiplyDouble(double value, int places) {
        double scale = Math.pow(10, places);
        return (Math.round(value * scale) / scale) * 100;
    }
}
