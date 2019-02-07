package me.drakeet.support.about;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author drakeet
 */
public class Contributor {

  public @DrawableRes final int avatarResId;
  public @NonNull final String name;
  public @NonNull final String desc;
  public @Nullable String url;

  public Contributor(@DrawableRes int avatarResId, @NonNull String name, @NonNull String desc) {
    this(avatarResId, name, desc, null);
  }

  public Contributor(
      @DrawableRes int avatarResId,
      @NonNull String name,
      @NonNull String desc,
      @Nullable String url) {

    this.avatarResId = avatarResId;
    this.name = name;
    this.desc = desc;
    this.url = url;
  }
}