package com.drakeet.about;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
public class LicenseViewBinder extends ItemViewBinder<License, LicenseViewBinder.ViewHolder> {

  @Override @NonNull
  public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    return new ViewHolder(inflater.inflate(R.layout.about_page_item_license, parent, false));
  }

  @Override @SuppressLint("SetTextI18n")
  public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull License data) {
    holder.content.setText(data.name + " - " + data.author);
    holder.hint.setText(data.url + "\n" + data.type);
    holder.setURL(data.url);
  }

  @Override
  public long getItemId(@NonNull License item) {
    return item.hashCode();
  }

  public static class ViewHolder extends ClickableViewHolder {

    public TextView content;
    public TextView hint;

    public ViewHolder(View itemView) {
      super(itemView);
      content = itemView.findViewById(R.id.content);
      hint = itemView.findViewById(R.id.hint);
    }
  }
}