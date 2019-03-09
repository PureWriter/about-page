package me.drakeet.support.about;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import android.view.View;

/**
 * @author drakeet
 */
public interface OnRecommendationClickedListener {

  @CheckResult
  boolean onRecommendationClicked(@NonNull View itemView, @NonNull Recommendation recommendation);
}
