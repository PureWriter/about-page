package com.drakeet.about.extension.provided;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.drakeet.about.extension.JsonConverter;
import com.google.gson.Gson;

/**
 * @author drakeet
 */
public class GsonJsonConverter implements JsonConverter {

  private final Gson gson = new Gson();

  @Override
  public @Nullable <T> T fromJson(@Nullable String json, @NonNull Class<T> classOfT) {
    return gson.fromJson(json, classOfT);
  }

  @Override
  public @NonNull <T> String toJson(@Nullable T src, @NonNull Class<T> classOfT) {
    return gson.toJson(src, classOfT);
  }
}
