package me.drakeet.support.about;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
@SuppressWarnings("WeakerAccess")
public class CategoryViewBinder extends ItemViewBinder<Category, CategoryViewBinder.ViewHolder> {

    @NonNull @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.about_page_item_category, parent, false);
        return new ViewHolder(root);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Category category) {
        holder.category.setText(category.value);
        if (category.rightIcon != -1) {
            holder.rightIcon.setImageResource(category.rightIcon);
            holder.rightIcon.setVisibility(View.VISIBLE);
        } else {
            holder.rightIcon.setVisibility(View.GONE);
        }
        if (category.rightText != null) {
            holder.rightText.setText(category.rightText);
            holder.rightText.setVisibility(View.VISIBLE);
        } else {
            holder.rightText.setVisibility(View.GONE);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView category;
        public AppCompatImageView rightIcon;
        public TextView rightText;


        public ViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
            rightIcon = itemView.findViewById(R.id.right_icon);
            rightText = itemView.findViewById(R.id.right_text);
            // rightIcon.setOnClickListener();
            // rightText.setOnClickListener();
        }
    }


    @Override
    protected long getItemId(@NonNull Category item) {
        return item.hashCode();
    }
}
