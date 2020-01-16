package com.drakeet.about;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author wilson
 */
public class ClickableItem {

    public @NonNull
    final String title;
    public @NonNull
    final String desc;
    public @Nullable
    View.OnClickListener onClickListener;

    public ClickableItem(@NonNull String title, @NonNull String desc) {
        this(title, desc, null);
    }

    public ClickableItem(
            @NonNull String title,
            @NonNull String desc,
            @Nullable View.OnClickListener onClickListener) {

        this.title = title;
        this.desc = desc;
        this.onClickListener = onClickListener;
    }
}