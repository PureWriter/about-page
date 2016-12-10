package me.drakeet.support.about;

import android.support.annotation.NonNull;

/**
 * @author drakeet
 */
public class Card {

    @NonNull public final String content;
    @NonNull public final String action;


    public Card(@NonNull String content, @NonNull String action) {
        this.content = content;
        this.action = action;
    }
}
