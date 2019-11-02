package es.saladillo.alejandrodiaz.projectdex.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }



}
