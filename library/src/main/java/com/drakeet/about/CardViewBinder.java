package com.drakeet.about;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
@SuppressWarnings("WeakerAccess")
public class CardViewBinder extends ItemViewBinder<Card, CardViewBinder.ViewHolder> {

  @Override @NonNull
  public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new ViewHolder(inflater.inflate(R.layout.about_page_item_card, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Card card) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      holder.content.setLineSpacing(card.lineSpacingExtra, holder.content.getLineSpacingMultiplier());
    }
    holder.content.setText(card.content);
  }

  @Override
  public long getItemId(@NonNull Card item) {
    return item.hashCode();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {

    public TextView content;

    public ViewHolder(View itemView) {
      super(itemView);
      content = itemView.findViewById(R.id.content);
    }
  }
}
