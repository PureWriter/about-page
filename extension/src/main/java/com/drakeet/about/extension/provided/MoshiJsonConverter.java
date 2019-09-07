package com.drakeet.about.extension.provided;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.drakeet.about.extension.JsonConverter;
import com.squareup.moshi.Moshi;

/**
 * @author drakeet
 */
public class MoshiJsonConverter implements JsonConverter {

  private final Moshi moshi = new Moshi.Builder().build();

  @Override
  public @Nullable <T> T fromJson(@NonNull String json, @NonNull Class<T> classOfT) throws Exception {
    return moshi.adapter(classOfT).fromJson(json);
  }

  @Override
  public @NonNull <T> String toJson(@Nullable T src, @NonNull Class<T> classOfT) {
    return moshi.adapter(classOfT).toJson(src);
  }
}
