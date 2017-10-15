package me.drakeet.support.about;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
public class RecommendedViewBinder extends ItemViewBinder<Recommended, RecommendedViewBinder.ViewHolder> {

    private static final String TAG = "about-page";

    private @Nullable final ImageLoader imageLoader;


    public RecommendedViewBinder(@Nullable ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }


    @NonNull @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.about_page_item_recommended, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Recommended recommended) {
        holder.setRecommended(recommended, imageLoader);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView icon;
        public TextView name;
        public TextView packageName;
        public TextView sizeView;
        public TextView description;
        public Recommended recommended;


        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            name = (TextView) itemView.findViewById(R.id.name);
            packageName = (TextView) itemView.findViewById(R.id.packageName);
            sizeView = (TextView) itemView.findViewById(R.id.size);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (recommended != null) {
                if (recommended.openWithGooglePlay) {
                    openMarket(v.getContext(), recommended.packageName, recommended.downloadUrl);
                } else {
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(recommended.downloadUrl)));
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


        private void openMarket(@NonNull Context context, @NonNull String targetPackage, @NonNull String defaultDownloadUrl) {
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
}
