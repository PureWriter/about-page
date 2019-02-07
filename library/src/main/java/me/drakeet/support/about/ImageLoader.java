package me.drakeet.support.about;

import androidx.annotation.NonNull;
import android.widget.ImageView;

/**
 * @author drakeet
 */
public interface ImageLoader {

  void load(@NonNull ImageView imageView, @NonNull String url);
}
