package com.drakeet.about.extension;

import androidx.annotation.Keep;
import com.drakeet.about.Recommendation;
import java.util.List;

/**
 * @author drakeet
 */
@Keep
public class RecommendationResponse {
  public int code;
  public List<Recommendation> data;
}
