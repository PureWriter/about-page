package me.drakeet.support.about;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
@SuppressWarnings("WeakerAccess")
public class CategoryViewBinder extends ItemViewBinder<Category, CategoryViewBinder.ViewHolder> {

  @NonNull @Override
  public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new ViewHolder(inflater.inflate(R.layout.about_page_item_category, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Category category) {
    holder.category.setText(category.title);
    holder.actionIcon.setImageDrawable(category.actionIcon);
    holder.actionIcon.setContentDescription(category.actionIconContentDescription);
    if (category.actionIcon != null) {
      holder.actionIcon.setVisibility(View.VISIBLE);
    } else {
      holder.actionIcon.setVisibility(View.GONE);
    }
    holder.actionIcon.setOnClickListener(category.getOnActionClickListener());
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView category;
    public ImageButton actionIcon;

    public ViewHolder(View itemView) {
      super(itemView);
      category = itemView.findViewById(R.id.category);
      actionIcon = itemView.findViewById(R.id.actionIcon);
    }
  }

  @Override
  public long getItemId(@NonNull Category item) {
    return item.hashCode();
  }
}
