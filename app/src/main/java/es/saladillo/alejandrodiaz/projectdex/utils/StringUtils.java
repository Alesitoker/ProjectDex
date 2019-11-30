package es.saladillo.alejandrodiaz.projectdex.utils;

public class StringUtils {

    private StringUtils() {}

    public static String CapitalizeFirstLetter(String string) {
        return String.format("%c%s", Character.toUpperCase(string.charAt(0)), string.substring(1));
    }
}
