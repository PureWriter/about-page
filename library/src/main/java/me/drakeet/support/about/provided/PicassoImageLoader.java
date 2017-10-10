package me.drakeet.support.about.provided;

import android.support.annotation.NonNull;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import me.drakeet.support.about.ImageLoader;

/**
 * @author drakeet
 */
public class PicassoImageLoader implements ImageLoader {

    @Override
    public void load(@NonNull ImageView imageView, @NonNull String url) {
        Picasso.with(imageView.getContext()).load(url).into(imageView);
    }
}
