package me.drakeet.support.about;

import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import me.drakeet.multitype.Item;

/**
 * @author drakeet
 */
public class Contributor implements Item {

    @DrawableRes public final int avatarResId;
    @NonNull public final String name;
    @NonNull public final String desc;


    public Contributor(@DrawableRes int avatarResId, @NonNull String name, @NonNull String desc) {
        this.avatarResId = avatarResId;
        this.name = name;
        this.desc = desc;
    }
}