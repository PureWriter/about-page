package me.drakeet.support.about.sample;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.Items;
import me.drakeet.support.about.AbsAboutActivity;
import me.drakeet.support.about.Card;
import me.drakeet.support.about.Category;
import me.drakeet.support.about.Contributor;
import me.drakeet.support.about.License;
import me.drakeet.support.about.Line;

/**
 * @author drakeet
 */
public class MainActivity extends AbsAboutActivity {

    @Override @SuppressLint("SetTextI18n")
    protected void onCreateHeader(ImageView icon, TextView slogan, TextView version) {
        icon.setImageResource(R.mipmap.ic_launcher);
        slogan.setText("About Page By drakeet");
        version.setText("v" + BuildConfig.VERSION_NAME);
    }


    @Override @SuppressWarnings("SpellCheckingInspection")
    protected void onItemsCreated(@NonNull Items items) {
        /* @formatter:off */
        items.add(new Category("介绍与帮助"));
        items.add(new Card(getString(R.string.card_content), "分享"));
        items.add(new Line());

        items.add(new Category("Developers"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "drakeet", "Developer & designer", "http://weibo.com/drak11t"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "黑猫酱", "Developer", "https://drakeet.me"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "小艾大人", "Developer"));
        items.add(new Line());

        items.add(new Category("Open Source Licenses"));
        items.add(new License("MultiType", "drakeet", License.APACHE_2, "https://github.com/drakeet/MultiType"));
        items.add(new License("about-page", "drakeet", License.APACHE_2, "https://github.com/drakeet/about-page"));
        /* @formatter:on */
    }


    @Override
    protected void onActionClick(View action) {
        onClickShare();
    }


    public void onClickShare() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_content));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }
}
