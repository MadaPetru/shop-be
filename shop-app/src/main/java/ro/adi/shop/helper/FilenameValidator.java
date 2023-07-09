package ro.adi.shop.helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FilenameValidator {


    private static final String WHITELIST_REGEX = "^[a-zA-Z0-9_. -]+$";
    private static final String[] RESERVED_FILENAMES = {"con", "prn", "aux", "nul", "com1", "com2", "com3", "com4",
            "com5", "com6", "com7", "com8", "com9", "lpt1", "lpt2", "lpt3", "lpt4", "lpt5", "lpt6", "lpt7", "lpt8",
            "lpt9"};

    public static boolean isValidFilename(String filename) {
        if (!filename.matches(WHITELIST_REGEX)) {
            return false;
        }
        if (filename.startsWith(".") || filename.endsWith(".") || filename.endsWith(" ")) {
            return false;
        }
        for (var reservedFilename : RESERVED_FILENAMES) {
            if (filename.equalsIgnoreCase(reservedFilename)) {
                return false;
            }
        }
        return !filename.contains("/") && !filename.contains("\\");
    }
}

