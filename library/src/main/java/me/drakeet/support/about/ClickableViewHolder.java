package me.drakeet.support.about;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.net.Uri.parse;

/**
 * @author drakeet
 */
public class ClickableViewHolder extends RecyclerView.ViewHolder {

    @Nullable private String url;


    public ClickableViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (url != null) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(parse(url));
                    try {
                        v.getContext().startActivity(intent);
                    } catch (ActivityNotFoundException e) {
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
