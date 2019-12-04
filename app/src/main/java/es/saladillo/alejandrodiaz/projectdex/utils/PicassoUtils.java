package es.saladillo.alejandrodiaz.projectdex.utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;

import es.saladillo.alejandrodiaz.projectdex.R;

public class PicassoUtils {

    private PicassoUtils() {
    }

    public static void loadUrl(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext()).load(url).placeholder(R.drawable.ic_no_img)
                .error(R.drawable.ic_no_img).into(imageView);
    }

}