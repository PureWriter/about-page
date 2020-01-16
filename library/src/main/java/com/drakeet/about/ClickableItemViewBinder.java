package com.drakeet.about;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author wilson
 */
@SuppressWarnings("WeakerAccess")
public class ClickableItemViewBinder extends com.drakeet.multitype.ItemViewBinder<ClickableItem, ClickableItemViewBinder.ViewHolder> {

    private @NonNull
    final AbsAboutActivity activity;

    public ClickableItemViewBinder(@NonNull AbsAboutActivity activity) {
        this.activity = activity;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.about_page_item_clickable, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ClickableItem item) {
        holder.title.setText(item.title);
        holder.desc.setText(item.desc);
        holder.data = item;
        if (holder.data.onClickListener != null) {
            holder.itemView.setOnClickListener(holder.data.onClickListener);
        }
    }

    @Override
    public long getItemId(@NonNull ClickableItem item) {
        return item.hashCode();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView desc;
        public ClickableItem data;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.item);
            title = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}