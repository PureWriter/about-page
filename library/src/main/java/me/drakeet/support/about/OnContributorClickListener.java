package me.drakeet.support.about;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.View;

public interface OnContributorClickListener {

    @CheckResult
    boolean onContributorClick(@NonNull View itemView, @NonNull Contributor contributor);

}
