package com.drakeet.about.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import com.drakeet.about.AbsAboutActivity;
import com.drakeet.about.Card;
import com.drakeet.about.Category;
import com.drakeet.about.Contributor;
import com.drakeet.about.License;
import com.drakeet.about.OnContributorClickedListener;
import com.drakeet.about.OnRecommendationClickedListener;
import com.drakeet.about.Recommendation;
import com.drakeet.about.extension.RecommendationLoaderDelegate;
import com.drakeet.about.extension.provided.MoshiJsonConverter;
import com.drakeet.about.provided.PicassoImageLoader;
import java.util.List;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

/**
 * @author drakeet
 */
@SuppressLint("SetTextI18n")
@SuppressWarnings("SpellCheckingInspection")
public class AboutActivity extends AbsAboutActivity
    implements OnRecommendationClickedListener, OnContributorClickedListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setImageLoader(new PicassoImageLoader());
    setOnRecommendationClickedListener(this);
    setOnContributorClickedListener(this);
  }

  @Override
  protected void onCreateHeader(@NonNull ImageView icon, @NonNull TextView slogan, @NonNull TextView version) {
    icon.setImageResource(R.mipmap.ic_launcher);
    slogan.setText("About Page By drakeet");
    version.setText("v" + BuildConfig.VERSION_NAME);
  }

  @Override
  protected void onItemsCreated(@NonNull List<Object> items) {
    items.add(new Category("介绍与帮助"));
    items.add(new Card(getString(R.string.card_content)));

    items.add(new Category("Developers"));
    items.add(new Contributor(R.drawable.avatar_drakeet, "Drakeet", "Developer & designer", "http://weibo.com/drak11t"));
    items.add(new Contributor(R.drawable.avatar_drakeet, "黑猫酱", "Developer", "https://drakeet.me"));
    items.add(new Contributor(R.drawable.avatar_drakeet, "小艾大人", "Developer"));

    items.add(new Category("我独立开发的应用"));
    items.add(new Recommendation(
        0, getString(R.string.pure_writer),
        "https://raw.githubusercontent.com/PureWriter/about-page/master/images/app_writer.png",
        "com.drakeet.purewriter",
        "快速的纯文本编辑器，我们希望写作能够回到原本的样子：纯粹、有安全感、随时、绝对不丢失内容、具备良好的写作体验。",
        "https://play.google.com/store/apps/details?id=com.drakeet.purewriter",
        "2017-10-09 16:46:57",
        "2017-10-09 16:46:57", 2.93, true)
    );
    items.add(new Recommendation(
        1, getString(R.string.pure_mosaic),
        "https://raw.githubusercontent.com/PureWriter/about-page/master/images/app_mosaic.png",
        "me.drakeet.puremosaic",
        "专注打码的轻应用，包含功能：传统马赛克、毛玻璃效果、选区和手指模式打码，更有创新型高亮打码和 LowPoly 风格马赛克。只为满足一个纯纯的打码需求，让打码也能成为一种赏心悦目。",
        "https://play.google.com/store/apps/details?id=me.drakeet.puremosaic",
        "2017-10-09 16:46:57",
        "2017-10-09 16:46:57", 2.64, true)
    );
    // Load more Recommendation items from remote server asynchronously
    RecommendationLoaderDelegate.attach(this, items.size(), new MoshiJsonConverter() /* or new GsonJsonConverter() */);
    // or
    // RecommendationLoader.getInstance().loadInto(this, items.size());

    items.add(new Category("Open Source Licenses"));
    items.add(new License("about-page", "drakeet", License.APACHE_2, "https://github.com/drakeet/about-page"));
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
  public boolean onRecommendationClicked(@NonNull View itemView, @NonNull Recommendation recommendation) {
    Toast.makeText(this, "onRecommendationClicked: " + recommendation.appName, Toast.LENGTH_SHORT).show();
    return false;
  }

  @Override
  public boolean onContributorClicked(@NonNull View itemView, @NonNull Contributor contributor) {
    if (contributor.name.equals("小艾大人")) {
      Toast.makeText(this, "onContributorClicked: " + contributor.name, Toast.LENGTH_SHORT).show();
      return true;
    }
    return false;
  }
}
