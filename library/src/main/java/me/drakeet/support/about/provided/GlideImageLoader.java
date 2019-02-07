package me.drakeet.support.about.provided;

import androidx.annotation.NonNull;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import me.drakeet.support.about.ImageLoader;

/**
 * @author drakeet
 */
public class GlideImageLoader implements ImageLoader {

  @Override
  public void load(@NonNull ImageView imageView, @NonNull String url) {
    Glide.with(imageView.getContext()).load(url).into(imageView);
  }
}
