# about-page

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/drakeet/about-page/blob/master/LICENSE)
![maven-central](https://img.shields.io/maven-central/v/com.drakeet.about/about.svg)

A nice about page based on [MultiType](https://github.com/drakeet/MultiType), material design and very easy to use.

From the [PureWriter](https://play.google.com/store/apps/details?id=com.drakeet.purewriter) App:

<a href='https://play.google.com/store/apps/details?id=com.drakeet.purewriter&utm_source=global_co&utm_medium=prtnr&utm_content=Mar2515&utm_campaign=PartBadge&pcampaignid=MKT-Other-global-all-co-prtnr-py-PartBadge-Mar2515-1'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width=200 height=77/></a>

### Getting started

In your `build.gradle`:

_This library has been rebuilt based on AndroidX. If you are still using the android support library, please use version `about:2.1.1` and `multitype:3.4.4`._

_In addition, since 2.3.0 we have migrated to build with MultiType 4.0.0. If you don't want to use MultiType 4.0.0, you can use the last stable version `multitype:3.5.0`(`me.drakeet.multitype:multitype:3.5.0`) & `about:2.2.0`(`me.drakeet.support:about:2.2.0`)._

```groovy
dependencies {
    implementation 'com.drakeet.about:about:2.5.2'
    implementation 'com.drakeet.multitype:multitype:4.3.0'
    
    // optional: for using the PicassoImageLoader
    // implementation 'com.squareup.picasso:picasso:2.71828'
    // optional: for using the GlideImageLoader
    // implementation 'com.github.bumptech.glide:glide:4.9.0'
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
    protected void onItemsCreated(@NonNull List<Object> items) {
        items.add(new Category("介绍与帮助"));
        items.add(new Card(getString(R.string.card_content)));

        items.add(new Category("Developers"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "drakeet", "Developer & designer", "http://weibo.com/drak11t"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "黑猫酱", "Developer", "https://drakeet.me"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "小艾大人", "Developer"));

        items.add(new Category("Open Source Licenses"));
        items.add(new License("MultiType", "drakeet", License.APACHE_2, "https://github.com/drakeet/MultiType"));
        items.add(new License("about-page", "drakeet", License.APACHE_2, "https://github.com/drakeet/about-page"));
    }
}
```

### Custom style/theme

```xml
<style name="AppTheme.About" parent="Theme.AppCompat.DayNight.NoActionBar">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@android:color/black</item>
    
    <!--optional-->
    <item name="aboutPageHeaderBackground">reference|color</item>
    <item name="aboutPageHeaderContentScrim">reference|color</item>
    <item name="aboutPageHeaderTextColor">color</item>
    <item name="aboutPageNavigationIcon">reference</item>
</style>
```

### Screenshots

<img src="https://i.loli.net/2017/10/20/59e95e4c78f5b.png" width=270/> <img src="https://i.loli.net/2017/10/20/59e95e4c8243c.png" width=270/>

License
-------

    Copyright (c) 2016-present. Drakeet Xu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
