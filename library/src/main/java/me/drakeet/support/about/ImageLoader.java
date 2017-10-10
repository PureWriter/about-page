package me.drakeet.support.about;

import android.support.annotation.NonNull;
import android.widget.ImageView;

/**
 * @author drakeet
 */
public interface ImageLoader {

    void load(@NonNull ImageView imageView, @NonNull String url);
}
