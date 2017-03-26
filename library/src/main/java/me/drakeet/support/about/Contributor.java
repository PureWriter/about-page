package me.drakeet.support.about;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author drakeet
 */
public class Contributor {

    @DrawableRes public final int avatarResId;
    @NonNull public final String name;
    @NonNull public final String desc;
    @Nullable public String url;


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