package me.drakeet.support.about;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * @author drakeet
 */
public interface OnRecommendedClickedListener {

    @CheckResult
    boolean onRecommendedClicked(@NonNull View itemView, @NonNull Recommended recommended);
}
