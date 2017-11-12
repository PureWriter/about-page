package me.drakeet.support.about;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author drakeet
 */
public class Category {

    public @NonNull final String value;
    public final @DrawableRes int rightIcon;
    public final @Nullable String rightText;


    public Category(@NonNull String value) {
        this(value, -1, null);
    }


    public Category(@NonNull String value, int rightIcon, @Nullable String rightText) {
        this.value = value;
        this.rightIcon = rightIcon;
        this.rightText = rightText;
    }
}
