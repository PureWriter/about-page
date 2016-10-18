# about-page

#### Usage

```java
public class MainActivity extends AboutActivity {

    @SuppressLint("SetTextI18n") @Override
    protected void onCreateHeader(ImageView icon, TextView slogan, TextView version) {
        icon.setImageResource(R.mipmap.ic_launcher);
        slogan.setText("About Page By drakeet");
        version.setText("v" + BuildConfig.VERSION_NAME);
    }

    @Override protected void onItemsCreated(@NonNull Items items) {
        items.add(new Category("介绍与帮助"));
        items.add(new Card(getString(R.string.card_content), "分享"));

        items.add(new Line());

        items.add(new Category("Developers"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "drakeet", "Developer & designer"));
        items.add(new Contributor(R.drawable.avatar_drakeet, "小艾大人", "Developer"));

        items.add(new Line());

        items.add(new Category("Open Source Licenses"));
        items.add(new License("MultiType", "drakeet", License.APACHE_2,
            "https://github.com/drakeet/MultiType"));
        items.add(new License("about-page", "drakeet", License.APACHE_2,
            "https://github.com/drakeet/about-page"));
    }

    @Override protected void onActionClick(View action) {
        ...
    }
}
```

#### TODO

- Upload to maven
- Added a inner activity and builder for convenience

<img src="http://ww3.sinaimg.cn/large/86e2ff85gw1f8wrhh1grlj20k00zk41i.jpg" width=270 height=486/> <img src="http://ww3.sinaimg.cn/large/86e2ff85gw1f8wrhv6958j20k00zkjtb.jpg" width=270 height=486/> <img src="http://ww4.sinaimg.cn/large/86e2ff85gw1f8wri3lpwqj20k00zkmzf.jpg" width=270 height=486/>
