package me.drakeet.support.about;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @author drakeet
 */
public class ContributorViewBinder
    extends ItemViewBinder<Contributor, ContributorViewBinder.ViewHolder> {

    @NonNull @Override
    protected ViewHolder onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.about_page_item_contributor, parent, false);
        return new ViewHolder(root);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Contributor contributor) {
        holder.avatar.setImageResource(contributor.avatarResId);
        holder.name.setText(contributor.name);
        holder.desc.setText(contributor.desc);
        holder.setURL(contributor.url);
    }


    static class ViewHolder extends ClickableViewHolder {

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