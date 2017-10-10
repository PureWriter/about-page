package me.drakeet.support.about;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

    private @NonNull final ImageLoader imageLoader;


    public RecommendedViewBinder(@NonNull ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }


    @NonNull @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.about_page_item_recommended, parent, false));
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Recommended recommended) {
        // noinspection ConstantConditions
        if (imageLoader == null) {
            throw new NullPointerException("You should call setImageLoader() first");
        }
        imageLoader.load(holder.icon, recommended.iconUrl);
        holder.name.setText(recommended.appName);
        holder.packageName.setText(recommended.packageName);
        holder.description.setText(recommended.description);
        @SuppressLint("SetTextI18n")
        String size = recommended.downloadSize + "MB";
        holder.size.setText(size);
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView icon;
        private TextView name;
        private TextView packageName;
        private TextView size;
        private TextView description;


        ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            name = (TextView) itemView.findViewById(R.id.name);
            packageName = (TextView) itemView.findViewById(R.id.packageName);
            size = (TextView) itemView.findViewById(R.id.size);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
