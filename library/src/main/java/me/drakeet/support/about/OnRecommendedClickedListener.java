package me.drakeet.support.about;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import android.view.View;

/**
 * @author drakeet
 */
public interface OnRecommendedClickedListener {

  @CheckResult
  boolean onRecommendedClicked(@NonNull View itemView, @NonNull Recommended recommended);
}
