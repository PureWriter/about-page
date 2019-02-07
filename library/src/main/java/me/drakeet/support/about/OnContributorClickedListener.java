package me.drakeet.support.about;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import android.view.View;

public interface OnContributorClickedListener {

  @CheckResult
  boolean onContributorClicked(@NonNull View itemView, @NonNull Contributor contributor);
}
