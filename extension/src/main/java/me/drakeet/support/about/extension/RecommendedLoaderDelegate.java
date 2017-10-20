package me.drakeet.support.about.extension;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.NonNull;
import me.drakeet.support.about.AbsAboutActivity;
import okhttp3.Call;

/**
 * If use the delegate class, you have to use the support:appcompat-v7 26.1.0 or above1,
 * and keep all class of android lifecycle with proguard:
 * <pre>
 * ## Android architecture components: Lifecycle
 * -keep class android.arch.lifecycle.** { *; }
 * </pre>
 * Otherwise, you could just use the {@link RecommendedLoader} instead without any keep and no need
 * support libraries 26.1.0.
 *
 * @author drakeet
 */
public class RecommendedLoaderDelegate implements LifecycleObserver {

    private final @NonNull AbsAboutActivity aboutActivity;
    private final int index;
    private RecommendedLoader loader;
    private boolean showDefaultCategory;
    private Call call;


    private RecommendedLoaderDelegate(@NonNull AbsAboutActivity aboutActivity, int index, boolean showDefaultCategory) {
        this.aboutActivity = aboutActivity;
        this.index = index;
        this.showDefaultCategory = showDefaultCategory;
    }


    public static void attach(@NonNull final AbsAboutActivity activity, int index) {
        attach(activity, index, true);
    }


    public static void attach(@NonNull final AbsAboutActivity activity, int index, boolean showDefaultCategory) {
        RecommendedLoaderDelegate delegate = new RecommendedLoaderDelegate(activity, index, showDefaultCategory);
        activity.getLifecycle().addObserver(delegate);
        delegate.start();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        if (call != null) {
            call.cancel();
        }
    }


    private void start() {
        loader = RecommendedLoader.getInstance();
        call = loader.loadInto(aboutActivity, index, showDefaultCategory);
    }
}
