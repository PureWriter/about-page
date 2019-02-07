package me.drakeet.support.about.extension;

import android.annotation.SuppressLint;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import android.util.Log;
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
public class RecommendedLoader {

  private static final String TAG = "RecommendedLoader";

  private final OkHttpClient client = new OkHttpClient();
  private Call call;

  public static @NonNull RecommendedLoader getInstance() {
    return new RecommendedLoader();
  }

  public @NonNull Call loadInto(@NonNull final AbsAboutActivity activity, int index, @NonNull JsonConverter jsonConverter) {
    return loadInto(activity, index, true, jsonConverter);
  }

  public @NonNull Call loadInto(@NonNull final AbsAboutActivity activity, int index, final boolean showDefaultCategory, @NonNull final JsonConverter jsonConverter) {
    final List<Object> items = activity.getItems();
    final int finalIndex;
    if (index > items.size()) {
      finalIndex = items.size();
    } else {
      finalIndex = index;
    }
    call = requestRecommendedList(activity.getPackageName(), new Callback() {
      @Override
      public void onFailure(@NonNull Call call, @NonNull IOException e) {
        Log.e(TAG, "RequestRecommendedList failed", e);
      }

      @Override
      public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        final RecommendedResponse recommendedResponse;
        try {
          @SuppressWarnings("ConstantConditions")
          String body = response.body().string();
          recommendedResponse = jsonConverter.fromJson(body, RecommendedResponse.class);
        } catch (Throwable throwable) {
          Log.e(TAG, "Json parse failed", throwable);
          return;
        }
        if (recommendedResponse == null) {
          Log.e(TAG, "Json parse failed with null response");
          return;
        }
        activity.runOnUiThread(new Runnable() {
          @Override
          public void run() {
            if (showDefaultCategory) {
              items.add(finalIndex, new Category(activity.getString(R.string.about_page_app_links)));
              items.addAll(finalIndex + 1, recommendedResponse.data);
            } else {
              items.addAll(finalIndex, recommendedResponse.data);
            }
            activity.getAdapter().notifyDataSetChanged();
          }
        });
      }
    });
    return call;
  }

  @CheckResult
  private @NonNull Call requestRecommendedList(@NonNull String packageName, @NonNull Callback responseCallback) {
    Call call = client.newCall(new Request.Builder().url("https://recommend.wetolink.com/api/v2/app_recommend/pull?limit=10&package_name=" + packageName).build());
    call.enqueue(responseCallback);
    return call;
  }
}
