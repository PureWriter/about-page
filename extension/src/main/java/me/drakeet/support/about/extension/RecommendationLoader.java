package me.drakeet.support.about.extension;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import java.util.List;
import me.drakeet.support.about.AbsAboutActivity;
import me.drakeet.support.about.Category;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author drakeet
 */
@SuppressLint("LogNotTimber")
@SuppressWarnings("WeakerAccess")
public class RecommendationLoader {

  private static final String TAG = "RecommendationLoader";

  private final OkHttpClient client = new OkHttpClient();
  private static final String KEY_LOAD_RECOMMENDATIONS = "load_recommendations";

  public static @NonNull RecommendationLoader getInstance() {
    return new RecommendationLoader();
  }

  public @Nullable Call loadInto(@NonNull final AbsAboutActivity activity, int index, @NonNull JsonConverter jsonConverter) {
    return loadInto(activity, index, true, jsonConverter);
  }

  public @Nullable Call loadInto(@NonNull final AbsAboutActivity activity, int index, final boolean showDefaultCategory, @NonNull final JsonConverter jsonConverter) {
    final SharedPreferences preferences = activity.getSharedPreferences("about_page_extension", Context.MODE_PRIVATE);
    boolean loadRecommendations = preferences.getBoolean(KEY_LOAD_RECOMMENDATIONS, true);
    if (!loadRecommendations) {
      return null;
    }
    final List<Object> items = activity.getItems();
    final int finalIndex;
    if (index > items.size()) {
      finalIndex = items.size();
    } else {
      finalIndex = index;
    }
    return requestRecommendationList(activity.getPackageName(), new Callback() {
      @Override
      public void onFailure(@NonNull Call call1, @NonNull IOException e) {
        Log.e(TAG, "requestRecommendationList failed", e);
      }

      @Override
      public void onResponse(@NonNull Call call1, @NonNull Response response) {
        final RecommendationResponse recommendationResponse;
        try {
          @SuppressWarnings("ConstantConditions")
          String body = response.body().string();
          recommendationResponse = jsonConverter.fromJson(body, RecommendationResponse.class);
        } catch (Throwable throwable) {
          Log.e(TAG, "Json parse failed", throwable);
          return;
        }
        if (recommendationResponse == null) {
          Log.e(TAG, "Json parse failed with null response");
          return;
        }
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            if (showDefaultCategory) {
              final Category category = new Category(activity.getString(R.string.about_page_app_links), ContextCompat.getDrawable(activity, R.drawable.about_page_ic_close_day_night_24dp));
              category.setOnActionClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  preferences.edit().putBoolean(KEY_LOAD_RECOMMENDATIONS, false).apply();
                  items.remove(category);
                  items.removeAll(recommendationResponse.data);
                  activity.getAdapter().notifyDataSetChanged();
                }
              });
              items.add(finalIndex, category);
              items.addAll(finalIndex + 1, recommendationResponse.data);
            } else {
              items.addAll(finalIndex, recommendationResponse.data);
            }
            activity.getAdapter().notifyDataSetChanged();
          }
        });
      }
    });
  }

  @CheckResult
  private @NonNull Call requestRecommendationList(@NonNull String packageName, @NonNull Callback responseCallback) {
    Call call = client.newCall(new Request.Builder().url("https://recommend.wetolink.com/api/v2/app_recommend/pull?limit=10&package_name=" + packageName).build());
    call.enqueue(responseCallback);
    return call;
  }
}
