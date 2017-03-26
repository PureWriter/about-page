package me.drakeet.support.about;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
public class LineViewBinder extends ItemViewBinder<Line, LineViewBinder.ViewHolder> {

    @NonNull @Override
    protected ViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.about_page_item_line, parent, false);
        return new ViewHolder(root);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Line data) {}


    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }
    }
}