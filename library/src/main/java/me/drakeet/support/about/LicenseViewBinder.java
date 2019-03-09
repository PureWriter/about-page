package me.drakeet.support.about;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
public class LicenseViewBinder extends ItemViewBinder<License, LicenseViewBinder.ViewHolder> {

  @NonNull @Override
  public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.about_page_item_license, parent, false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, @NonNull License data) {
    holder.content.setText(data.name + " - " + data.author);
    holder.hint.setText(data.url + "\n" + data.type);
    holder.setURL(data.url);
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

  @Override
  public long getItemId(@NonNull License item) {
    return item.hashCode();
  }
}