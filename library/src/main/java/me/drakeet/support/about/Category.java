package me.drakeet.support.about;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author drakeet
 */
public class Category {

  public @NonNull final String title;
  public @Nullable final Drawable actionIcon;
  public @Nullable final String actionIconContentDescription;
  private @Nullable View.OnClickListener onActionClickListener;

  public Category(@NonNull String title) {
    this(title, null, null);
  }

  public Category(@NonNull String title, @Nullable Drawable actionIcon) {
    this(title, actionIcon, null);
  }

  public Category(@NonNull String title, @Nullable Drawable actionIcon, @Nullable String actionIconContentDescription) {
    this.title = title;
    this.actionIcon = actionIcon;
    this.actionIconContentDescription = actionIconContentDescription;
  }

  public void setOnActionClickListener(@Nullable View.OnClickListener onActionClickListener) {
    this.onActionClickListener = onActionClickListener;
  }

  @Nullable
  public View.OnClickListener getOnActionClickListener() {
    return onActionClickListener;
  }
}
