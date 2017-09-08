# about-page

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/drakeet/about-page/blob/master/LICENSE)
![maven-central](https://img.shields.io/maven-central/v/me.drakeet.support/about.svg)

A nice about page based on [MultiType](https://github.com/drakeet/MultiType), material design and easy to use.

From the [PureWriter](https://play.google.com/store/apps/details?id=com.drakeet.purewriter) App:

<a href='https://play.google.com/store/apps/details?id=com.drakeet.purewriter&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width=200 height=77/></a>

### Getting started

In your `build.gradle`:

```groovy
dependencies {
    compile 'me.drakeet.support:about:1.3.0'
    compile 'me.drakeet.multitype:multitype:3.3.0'
}
```

### Usage

```java
public class AboutActivity extends AbsAboutActivity {

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
        items.add(new Line());

        items.add(new Category("Developers"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "drakeet", "Developer & designer", "http://weibo.com/drak11t"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "黑猫酱", "Developer", "https://drakeet.me"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "小艾大人", "Developer"));
        items.add(new Line());

        items.add(new Category("Open Source Licenses"));
        items.add(new License("MultiType", "drakeet", License.APACHE_2, "https://github.com/drakeet/MultiType"));
        items.add(new License("about-page", "drakeet", License.APACHE_2, "https://github.com/drakeet/about-page"));
    }
}
```

### Custom style/theme

```xml
<style name="AppTheme.About" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@android:color/black</item>
    <item name="android:textColorSecondary">@android:color/black</item>
</style>
```

```java
setHeaderBackgroundResource(@DrawableRes int resId);
setHeaderTextColor(@ColorInt int color);
setNavigationIcon(@DrawableRes int resId);
```

### TODO

- Add a inner activity and builder for convenience

<img src="http://ww3.sinaimg.cn/large/86e2ff85gw1f8wrhh1grlj20k00zk41i.jpg" width=270 height=486/> <img src="http://ww3.sinaimg.cn/large/86e2ff85gw1f8wrhv6958j20k00zkjtb.jpg" width=270 height=486/> <img src="http://ww4.sinaimg.cn/large/86e2ff85gw1f8wri3lpwqj20k00zkmzf.jpg" width=270 height=486/>
