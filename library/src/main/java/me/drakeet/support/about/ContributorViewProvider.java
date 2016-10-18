package me.drakeet.support.about;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.ItemViewProvider;

/**
 * @author drakeet
 */
public class ContributorViewProvider
    extends ItemViewProvider<Contributor, ContributorViewProvider.ViewHolder> {

    @NonNull @Override
    protected ViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.about_page_item_contributor, parent, false);
        return new ViewHolder(root);
    }


    @Override
    protected void onBindViewHolder(
        @NonNull ViewHolder holder, @NonNull Contributor contributor) {
        holder.avatar.setImageResource(contributor.avatarResId);
        holder.name.setText(contributor.name);
        holder.desc.setText(contributor.desc);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView name;
        TextView desc;


        ViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            desc = (TextView) itemView.findViewById(R.id.desc);
        }
    }
}