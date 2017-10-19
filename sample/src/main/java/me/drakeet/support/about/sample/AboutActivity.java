package me.drakeet.support.about.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import me.drakeet.multitype.Items;
import me.drakeet.support.about.AbsAboutActivity;
import me.drakeet.support.about.Card;
import me.drakeet.support.about.Category;
import me.drakeet.support.about.Contributor;
import me.drakeet.support.about.License;
import me.drakeet.support.about.Line;
import me.drakeet.support.about.OnRecommendedClickedListener;
import me.drakeet.support.about.Recommended;
import me.drakeet.support.about.provided.PicassoImageLoader;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO;
import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;

/**
 * @author drakeet
 */
@SuppressLint("SetTextI18n")
@SuppressWarnings("SpellCheckingInspection")
public class AboutActivity extends AbsAboutActivity implements OnRecommendedClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImageLoader(new PicassoImageLoader());
        setOnRecommendedClickedListener(this);
    }


    @Override
    protected void onCreateHeader(@NonNull ImageView icon, @NonNull TextView slogan, @NonNull TextView version) {
        icon.setImageResource(R.mipmap.ic_launcher);
        slogan.setText("About Page By drakeet");
        version.setText("v" + BuildConfig.VERSION_NAME);
    }


    @Override
    protected void onItemsCreated(@NonNull Items items) {
        items.add(new Category("介绍与帮助"));
        items.add(new Card(getString(R.string.card_content)));

        items.add(new Category("Developers"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "drakeet", "Developer & designer", "http://weibo.com/drak11t"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "黑猫酱", "Developer", "https://drakeet.me"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "小艾大人", "Developer"));

        items.add(new Category("应用推荐"));
        items.add(new Recommended(
            0, getString(R.string.pure_writer),
            "https://storage.recommend.wetolink.com/storage/app_recommend/images/YBMHN6SRpZeF0VHbPZWZGWJ2GyB6uaPx.png",
            "com.drakeet.purewriter",
            "快速的纯文本编辑器，我们希望写作能够回到原本的样子：纯粹、有安全感、随时、绝对不丢失内容、具备良好的写作体验。",
            "https://www.coolapk.com/apk/com.drakeet.purewriter",
            "2017-10-09 16:46:57",
            "2017-10-09 16:46:57", 2.93, true)
        );
        items.add(new Line());
        items.add(new Recommended(
            1, getString(R.string.pure_mosaic),
            "http://image.coolapk.com/apk_logo/2016/0831/ic_pure_mosaic-2-for-16599-o_1argff2ddgvt1lfv1b3mk2vd6pq-uid-435200.png",
            "me.drakeet.puremosaic",
            "专注打码的轻应用，包含功能：传统马赛克、毛玻璃效果、选区和手指模式打码，更有创新型高亮打码和 LowPoly 风格马赛克。只为满足一个纯纯的打码需求，让打码也能成为一种赏心悦目。",
            "https://www.coolapk.com/apk/me.drakeet.puremosaic",
            "2017-10-09 16:46:57",
            "2017-10-09 16:46:57", 2.64, true)
        );

        items.add(new Category("Open Source Licenses"));
        items.add(new License("about-page", "drakeet", License.APACHE_2, "https://github.com/drakeet/about-page"));
        items.add(new Line());
        items.add(new License("MultiType", "drakeet", License.APACHE_2, "https://github.com/drakeet/MultiType"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        MenuItem dayNight = menu.findItem(R.id.menu_night_mode);
        dayNight.setChecked(AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_night_mode) {
            menuItem.setChecked(!menuItem.isChecked());
            if (menuItem.isChecked()) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
            }
            getDelegate().applyDayNight();
        }
        return true;
    }


    @Override
    public boolean onRecommendedClicked(@NonNull View itemView, @NonNull Recommended recommended) {
        Toast.makeText(this, "onRecommendedClicked: " + recommended.appName, Toast.LENGTH_SHORT).show();
        return false;
    }
}
