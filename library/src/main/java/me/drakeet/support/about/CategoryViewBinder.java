package me.drakeet.support.about;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    View root = inflater.inflate(R.layout.about_page_item_category, parent, false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Category category) {
    holder.category.setText(category.value);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView category;

    public ViewHolder(View itemView) {
      super(itemView);
      category = (TextView) itemView.findViewById(R.id.category);
    }
  }

  @Override
  public long getItemId(@NonNull Category item) {
    return item.hashCode();
  }
}
