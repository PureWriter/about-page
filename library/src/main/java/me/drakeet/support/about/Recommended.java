package me.drakeet.support.about;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author drakeet
 */
@Keep
public class Recommended {

    public int id;
    public String appName;
    public String iconUrl;
    public String packageName;
    public String description;
    public String downloadURL;
    public String createdAt;
    public String updatedAt;
    public @Nullable String deletedAt;
    public double downloadSize;


    public Recommended() {}


    public Recommended(
        int id,
        @NonNull String appName,
        @NonNull String iconUrl,
        @NonNull String packageName,
        @NonNull String description,
        @NonNull String downloadURL,
        @NonNull String createdAt,
        @NonNull String updatedAt,
        @Nullable String deletedAt,
        double downloadSize) {
        this.id = id;
        this.appName = appName;
        this.iconUrl = iconUrl;
        this.packageName = packageName;
        this.description = description;
        this.downloadURL = downloadURL;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.downloadSize = downloadSize;
    }
}