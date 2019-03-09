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
public class CardViewBinder extends ItemViewBinder<Card, CardViewBinder.ViewHolder> {

  @NonNull @Override
  public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.about_page_item_card, parent, false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Card card) {
    holder.content.setText(card.content);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView content;

    public ViewHolder(View itemView) {
      super(itemView);
      content = itemView.findViewById(R.id.content);
    }
  }

  @Override
  public long getItemId(@NonNull Card item) {
    return item.hashCode();
  }
}