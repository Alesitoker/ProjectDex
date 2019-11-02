package es.saladillo.alejandrodiaz.projectdex.utils;

import android.text.TextWatcher;

public class TextUtils {

    private TextUtils() {

    }

    public interface AfterTextChanged extends TextWatcher {
        @Override
        default void beforeTextChanged(CharSequence s, int start, int count, int after) {}


        @Override
        default void onTextChanged(CharSequence s, int start, int before, int count) {}
    }

}
