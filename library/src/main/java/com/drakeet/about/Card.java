package com.drakeet.about;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

/**
 * @author drakeet
 */
public class Card {

  public @NonNull final CharSequence content;

  public final int lineSpacingExtra;

  @SuppressLint("NewApi")
  public Card(@NonNull CharSequence content) {
    this(content, 0);
  }

  @RequiresApi(16)
  public Card(@NonNull CharSequence content, int lineSpacingExtra) {
    this.content = content;
    this.lineSpacingExtra = lineSpacingExtra;
  }
}
