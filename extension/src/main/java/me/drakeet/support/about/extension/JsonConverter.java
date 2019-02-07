package me.drakeet.support.about.extension;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author drakeet
 */
public interface JsonConverter {

  @Nullable <T> T fromJson(@NonNull String json, @NonNull Class<T> classOfT) throws Exception;

  @NonNull <T> String toJson(@Nullable T src, @NonNull Class<T> classOfT);
}
