package es.saladillo.alejandrodiaz.projectdex.utils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;

import static es.saladillo.alejandrodiaz.projectdex.utils.ColorTypeUtils.obtainColor;

public class TypesUtils {

    public static void showType(Context context, List<Type> types, TextView type1, TextView type2) {
        int color;
        for (Type type : types) {
            if (type.getSlot() == 1) {
                type1.setText(type.getType().getName());
                color = obtainColor(type);
                DrawableCompat.setTint(
                        DrawableCompat.wrap(type1.getBackground()),
                        ContextCompat.getColor(context, color)
                );
                if (types.size() == 1) {
                    type2.setVisibility(View.GONE);
                }
            } else {
                type2.setVisibility(View.VISIBLE);
                type2.setText(type.getType().getName());
                DrawableCompat.setTint(
                        DrawableCompat.wrap(type2.getBackground()),
                        ContextCompat.getColor(context, obtainColor(type))
                );
            }
        }
    }
}
