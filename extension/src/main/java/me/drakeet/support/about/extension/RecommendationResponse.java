package me.drakeet.support.about.extension;

import androidx.annotation.Keep;
import java.util.List;
import me.drakeet.support.about.Recommendation;

/**
 * @author drakeet
 */
@Keep
public class RecommendationResponse {
  public int code;
  public List<Recommendation> data;
}
