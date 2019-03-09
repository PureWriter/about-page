package me.drakeet.support.about.extension;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.annotation.NonNull;
import me.drakeet.support.about.AbsAboutActivity;
import okhttp3.Call;

/**
 * If use the delegate class, you have to use the support:appcompat-v7 26.1.0 or above1,
 * and keep all class of android lifecycle with proguard:
 * <pre>
 * ## Android architecture components: Lifecycle
 * -keep class android.arch.lifecycle.** { *; }
 * </pre>
 * Otherwise, you could just use the {@link RecommendationLoader} instead without any keep and no need
 * support libraries 26.1.0.
 *
 * @author drakeet
 */
public class RecommendationLoaderDelegate implements LifecycleObserver {

  private final @NonNull AbsAboutActivity aboutActivity;
  private final int index;
  private RecommendationLoader loader;
  private boolean showDefaultCategory;
  private @Nullable Call call;
  private JsonConverter jsonConverter;

  private RecommendationLoaderDelegate(@NonNull AbsAboutActivity aboutActivity, int index, boolean showDefaultCategory, @NonNull JsonConverter jsonConverter) {
    this.aboutActivity = aboutActivity;
    this.index = index;
    this.showDefaultCategory = showDefaultCategory;
    this.jsonConverter = jsonConverter;
  }

  public static void attach(@NonNull AbsAboutActivity activity, int index, @NonNull JsonConverter jsonConverter) {
    attach(activity, index, true, jsonConverter);
  }

  public static void attach(@NonNull AbsAboutActivity activity, int index, boolean showDefaultCategory, @NonNull JsonConverter jsonConverter) {
    RecommendationLoaderDelegate delegate = new RecommendationLoaderDelegate(activity, index, showDefaultCategory, jsonConverter);
    activity.getLifecycle().addObserver(delegate);
    delegate.start();
  }

  private void start() {
    loader = RecommendationLoader.getInstance();
    call = loader.loadInto(aboutActivity, index, showDefaultCategory, jsonConverter);
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  void onDestroy() {
    if (call != null) {
      call.cancel();
    }
  }
}
