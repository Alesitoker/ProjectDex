package es.saladillo.alejandrodiaz.projectdex.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class PicassoUtils {

    private PicassoUtils() {
    }

    public static void loadUrl(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }

}