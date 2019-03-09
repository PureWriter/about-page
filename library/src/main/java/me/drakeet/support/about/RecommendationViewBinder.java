package me.drakeet.support.about;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
@SuppressWarnings("WeakerAccess")
public class RecommendationViewBinder extends ItemViewBinder<Recommendation, RecommendationViewBinder.ViewHolder> {

  private static final String TAG = "about-page";

  private @NonNull final AbsAboutActivity activity;

  public RecommendationViewBinder(@NonNull AbsAboutActivity activity) {
    this.activity = activity;
  }

  @NonNull @Override
  public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new ViewHolder(inflater.inflate(R.layout.about_page_item_recommendation, parent, false), activity);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Recommendation recommendation) {
    holder.setRecommendation(recommendation, activity.getImageLoader());
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView icon;
    public TextView name;
    public TextView packageName;
    public TextView sizeView;
    public TextView description;
    public Recommendation recommendation;
    private @Nullable BottomSheetDialog bottomSheet;
    protected @NonNull final AbsAboutActivity activity;

    public ViewHolder(View itemView, @NonNull AbsAboutActivity activity) {
      super(itemView);
      this.activity = activity;
      icon = itemView.findViewById(R.id.icon);
      name = itemView.findViewById(R.id.name);
      packageName = itemView.findViewById(R.id.packageName);
      sizeView = itemView.findViewById(R.id.size);
      description = itemView.findViewById(R.id.description);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      if (v.getId() == R.id.google_play && bottomSheet != null) {
        openWithMarket(v.getContext(), recommendation.packageName, recommendation.downloadUrl);
        bottomSheet.dismiss();
      } else if (v.getId() == R.id.web && bottomSheet != null) {
        openWithWeb(v.getContext(), recommendation);
        bottomSheet.dismiss();
      } else if (recommendation != null) {
        OnRecommendationClickedListener listener = activity.getOnRecommendationClickedListener();
        if (listener != null && listener.onRecommendationClicked(v, recommendation)) {
          return;
        }
        if (recommendation.openWithGooglePlay) {
          bottomSheet = new BottomSheetDialog(v.getContext());
          bottomSheet.setContentView(R.layout.about_page_dialog_market_chooser);
          bottomSheet.show();
          // noinspection ConstantConditions
          bottomSheet.findViewById(R.id.web).setOnClickListener(this);
          // noinspection ConstantConditions
          bottomSheet.findViewById(R.id.google_play).setOnClickListener(this);
        } else {
          openWithWeb(v.getContext(), recommendation);
        }
      }
    }

    protected void setRecommendation(@NonNull Recommendation recommendation, @Nullable ImageLoader imageLoader) {
      this.recommendation = recommendation;
      if (imageLoader != null) {
        icon.setVisibility(View.VISIBLE);
        imageLoader.load(icon, recommendation.iconUrl);
      } else {
        icon.setVisibility(View.GONE);
        Log.e(TAG, "You should call AbsAboutActivity.setImageLoader() otherwise the icon will be gone.");
      }
      name.setText(recommendation.appName);
      packageName.setText(recommendation.packageName);
      description.setText(recommendation.description);
      @SuppressLint("SetTextI18n")
      String size = recommendation.downloadSize + "MB";
      sizeView.setText(size);
    }

    protected void openWithWeb(@NonNull Context context, @NonNull Recommendation recommendation) {
      context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(recommendation.downloadUrl)));
    }

    private void openWithMarket(@NonNull Context context, @NonNull String targetPackage, @NonNull String defaultDownloadUrl) {
      try {
        Intent googlePlayIntent = context.getPackageManager().getLaunchIntentForPackage("com.android.vending");
        ComponentName comp = new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity");
        // noinspection ConstantConditions
        googlePlayIntent.setComponent(comp);
        googlePlayIntent.setData(Uri.parse("market://details?id=" + targetPackage));
        context.startActivity(googlePlayIntent);
      } catch (Throwable e) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(defaultDownloadUrl)));
        e.printStackTrace();
      }
    }
  }

  @Override
  public long getItemId(@NonNull Recommendation item) {
    return item.hashCode();
  }
}
