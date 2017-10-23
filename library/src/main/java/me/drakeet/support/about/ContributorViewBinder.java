package me.drakeet.support.about;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.ItemViewBinder;

import static android.net.Uri.parse;

/**
 * @author drakeet
 */
public class ContributorViewBinder
    extends ItemViewBinder<Contributor, ContributorViewBinder.ViewHolder> {

    @NonNull @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.about_page_item_contributor, parent, false);
        return new ViewHolder(root);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Contributor contributor) {
        holder.avatar.setImageResource(contributor.avatarResId);
        holder.name.setText(contributor.name);
        holder.desc.setText(contributor.desc);
        holder.data = contributor;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView avatar;
        public TextView name;
        public TextView desc;
        public Contributor data;

        public ViewHolder(final View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            desc = (TextView) itemView.findViewById(R.id.desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if (v.getContext() instanceof AbsAboutActivity) {
                        AbsAboutActivity activity = (AbsAboutActivity) v.getContext();
                        OnContributorClickListener listener = activity.getOnContributorClickListener();
                        if (listener != null && listener.onContributorClick(itemView, data)) {
                            return;
                        }
                    }
                    if (data.url != null) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(parse(data.url));
                        try {
                            v.getContext().startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }


    @Override
    protected long getItemId(@NonNull Contributor item) {
        return item.hashCode();
    }
}