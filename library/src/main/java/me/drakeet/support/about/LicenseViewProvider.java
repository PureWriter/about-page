package me.drakeet.support.about;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import me.drakeet.multitype.ItemViewProvider;

import static android.net.Uri.parse;

/**
 * @author drakeet
 */
public class LicenseViewProvider
    extends ItemViewProvider<License, LicenseViewProvider.ViewHolder> {

    @NonNull @Override
    protected ViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.about_page_item_license, parent, false);
        return new ViewHolder(root);
    }


    @Override
    protected void onBindViewHolder(
        @NonNull ViewHolder holder, @NonNull License data) {
        holder.content.setText(data.name + " - " + data.author);
        holder.hint.setText(data.url + "\n" + data.type);
        holder.url = data.url;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView content;
        TextView hint;
        String url;


        ViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
            hint = (TextView) itemView.findViewById(R.id.hint);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(parse(url));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}