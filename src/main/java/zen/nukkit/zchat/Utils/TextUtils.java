package zen.nukkit.zchat.Utils;

import zen.nukkit.zchat.Data.DataCache;

import java.util.regex.Pattern;

public class TextUtils {

    public static boolean stringIsAlphanumeric(String string) {
        return string == null || !string.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean textIsBlocked(String string) {
        return DataCache.blockedTexts.stream().anyMatch(text -> string.toLowerCase().contains(text.toLowerCase()));
    }

    public static String censorTexts(String string) {
        String output = string;

        for (String text : DataCache.textCensor) {
            Pattern PATTERN = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
            String xxx = new String(new char[text.length()]).replace("\u0000", "*");
            output = PATTERN.matcher(output).replaceAll(xxx);
        }
        return output;
    }

}
