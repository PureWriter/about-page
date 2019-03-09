package me.drakeet.support.about;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

/**
 * @author drakeet
 */
@Keep
public class Recommendation {

  public int id;
  public String appName;
  public String iconUrl;
  public String packageName;
  public String description;
  public String downloadUrl;
  public String createdTime;
  public String updatedTime;
  public double downloadSize;
  public boolean openWithGooglePlay;

  public Recommendation() {}

  public Recommendation(
      int id,
      @NonNull String appName,
      @NonNull String iconUrl,
      @NonNull String packageName,
      @NonNull String description,
      @NonNull String downloadUrl,
      @NonNull String createdTime,
      @NonNull String updatedTime,
      double downloadSize,
      boolean openWithGooglePlay
  ) {
    this.id = id;
    this.appName = appName;
    this.iconUrl = iconUrl;
    this.packageName = packageName;
    this.description = description;
    this.downloadUrl = downloadUrl;
    this.createdTime = createdTime;
    this.updatedTime = updatedTime;
    this.downloadSize = downloadSize;
    this.openWithGooglePlay = openWithGooglePlay;
  }
}