package com.drakeet.about;

import android.content.Intent;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import static android.net.Uri.parse;

/**
 * @author drakeet
 */
public class ClickableViewHolder extends RecyclerView.ViewHolder {

  private @Nullable String url;

  public ClickableViewHolder(View itemView) {
    super(itemView);
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (url != null) {
          Intent intent = new Intent(Intent.ACTION_VIEW);
          intent.setData(parse(url));
          try {
            v.getContext().startActivity(intent);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  public void setURL(@Nullable String url) {
    this.url = url;
  }
}
