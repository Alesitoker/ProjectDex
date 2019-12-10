package es.saladillo.alejandrodiaz.projectdex.utils;

import android.util.Log;

public class StringUtils {

    private StringUtils() {}

    public static String CapitalizeFirstLetter(String string) {
        return String.format("%c%s", Character.toUpperCase(string.charAt(0)), string.substring(1));
    }

    public static String pokemonNameSprite(String name) {
        String newName = name;
        switch (name) {
            case "deerling":
            case "sawsbuck":
                newName += "-winter";
                break;
            case "deoxys-normal":
            case "wormanda-plant":
            case "tornadus-incarnate":
            case "landorus-incarnate":
            case "thundurus-incarnate":
            case "keldeo-ordinary":
            case "meloetta-aria":
            case "pumpkaboo-average":
            case "gourgeist-average":
                newName = name.substring(0, newName.indexOf("-"));
                break;
            case "cherrim":
                newName = "cherrim-overcast";
                break;
            case "basculin-red-striped":
                newName = "basculin-red";
                break;
            case "basculin-blue-striped":
                newName = "basculin-blue";
                break;
            case "darmanitan-standard":
                newName = "darmanitan";
                break;
            case "nidoran-f":
            case "nidoran-m":
                newName = name.replaceAll("-", "");
                break;
        }
        return newName;
    }
}
