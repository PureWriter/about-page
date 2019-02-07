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
public class RecommendedViewBinder extends ItemViewBinder<Recommended, RecommendedViewBinder.ViewHolder> {

  private static final String TAG = "about-page";

  private @NonNull final AbsAboutActivity activity;

  public RecommendedViewBinder(@NonNull AbsAboutActivity activity) {
    this.activity = activity;
  }

  @NonNull @Override
  public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new ViewHolder(inflater.inflate(R.layout.about_page_item_recommended, parent, false), activity);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Recommended recommended) {
    holder.setRecommended(recommended, activity.getImageLoader());
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView icon;
    public TextView name;
    public TextView packageName;
    public TextView sizeView;
    public TextView description;
    public Recommended recommended;
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
        openWithMarket(v.getContext(), recommended.packageName, recommended.downloadUrl);
        bottomSheet.dismiss();
      } else if (v.getId() == R.id.web && bottomSheet != null) {
        openWithWeb(v.getContext(), recommended);
        bottomSheet.dismiss();
      } else if (recommended != null) {
        OnRecommendedClickedListener listener = activity.getOnRecommendedClickedListener();
        if (listener != null && listener.onRecommendedClicked(v, recommended)) {
          return;
        }
        if (recommended.openWithGooglePlay) {
          bottomSheet = new BottomSheetDialog(v.getContext());
          bottomSheet.setContentView(R.layout.about_page_dialog_market_chooser);
          bottomSheet.show();
          // noinspection ConstantConditions
          bottomSheet.findViewById(R.id.web).setOnClickListener(this);
          // noinspection ConstantConditions
          bottomSheet.findViewById(R.id.google_play).setOnClickListener(this);
        } else {
          openWithWeb(v.getContext(), recommended);
        }
      }
    }

    protected void setRecommended(@NonNull Recommended recommended, @Nullable ImageLoader imageLoader) {
      this.recommended = recommended;
      if (imageLoader != null) {
        icon.setVisibility(View.VISIBLE);
        imageLoader.load(icon, recommended.iconUrl);
      } else {
        icon.setVisibility(View.GONE);
        Log.e(TAG, "You should call AbsAboutActivity.setImageLoader() otherwise the icon will be gone.");
      }
      name.setText(recommended.appName);
      packageName.setText(recommended.packageName);
      description.setText(recommended.description);
      @SuppressLint("SetTextI18n")
      String size = recommended.downloadSize + "MB";
      sizeView.setText(size);
    }

    protected void openWithWeb(@NonNull Context context, @NonNull Recommended recommended) {
      context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(recommended.downloadUrl)));
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
  public long getItemId(@NonNull Recommended item) {
    return item.hashCode();
  }
}
