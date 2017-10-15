package me.drakeet.support.about;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author drakeet
 */
public abstract class AbsAboutActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private LinearLayout headerContentLayout;

    private Items items;
    private MultiTypeAdapter adapter;
    private TextView slogan, version;
    private RecyclerView recyclerView;
    private @Nullable ImageLoader imageLoader;
    private boolean initialized;


    protected abstract void onCreateHeader(@NonNull ImageView icon, @NonNull TextView slogan, @NonNull TextView version);
    protected abstract void onItemsCreated(@NonNull Items items);


    protected void onTitleViewCreated(@NonNull CollapsingToolbarLayout collapsingToolbar) {}


    public void setImageLoader(@NonNull ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
        if (initialized) {
            adapter.register(Recommended.class, new RecommendedViewBinder(imageLoader));
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public final void setContentView(View view) {
        super.setContentView(view);
    }


    @Override
    public final void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }


    @Override
    public final void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page_main_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView icon = (ImageView) findViewById(R.id.icon);
        slogan = (TextView) findViewById(R.id.slogan);
        version = (TextView) findViewById(R.id.version);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        headerContentLayout = (LinearLayout) findViewById(R.id.header_content_layout);
        onTitleViewCreated(collapsingToolbar);
        onCreateHeader(icon, slogan, version);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        onApplyPresetAttrs();
        recyclerView = (RecyclerView) findViewById(R.id.list);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        adapter = new MultiTypeAdapter();
        adapter.register(Category.class, new CategoryViewBinder());
        adapter.register(Card.class, new CardViewBinder());
        adapter.register(Line.class, new LineViewBinder());
        adapter.register(Contributor.class, new ContributorViewBinder());
        adapter.register(License.class, new LicenseViewBinder());
        adapter.register(Recommended.class, new RecommendedViewBinder(imageLoader));
        items = new Items();
        onItemsCreated(items);
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
        initialized = true;
    }


    private void onApplyPresetAttrs() {
        final TypedArray a = obtainStyledAttributes(R.styleable.AbsAboutActivity);
        Drawable headerBackground = a.getDrawable(R.styleable.AbsAboutActivity_aboutPageHeaderBackground);
        if (headerBackground != null) {
            setHeaderBackground(headerBackground);
        }
        Drawable headerContentScrim = a.getDrawable(R.styleable.AbsAboutActivity_aboutPageHeaderContentScrim);
        if (headerContentScrim != null) {
            setHeaderContentScrim(headerContentScrim);
        }
        @ColorInt
        int headerTextColor = a.getColor(R.styleable.AbsAboutActivity_aboutPageHeaderTextColor, -1);
        if (headerTextColor != -1) {
            setHeaderTextColor(headerTextColor);
        }
        Drawable navigationIcon = a.getDrawable(R.styleable.AbsAboutActivity_aboutPageNavigationIcon);
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
        a.recycle();
    }


    /**
     * Use {@link #setHeaderBackground(int)} instead.
     *
     * @param resId The resource id of header background
     */
    @Deprecated
    public void setHeaderBackgroundResource(@DrawableRes int resId) {
        setHeaderBackground(resId);
    }


    private void setHeaderBackground(@DrawableRes int resId) {
        setHeaderBackground(ContextCompat.getDrawable(this, resId));
    }


    private void setHeaderBackground(@NonNull Drawable drawable) {
        ViewCompat.setBackground(headerContentLayout, drawable);
    }


    /**
     * Set the drawable to use for the content scrim from resources. Providing null will disable
     * the scrim functionality.
     *
     * @param drawable the drawable to display
     */
    public void setHeaderContentScrim(@NonNull Drawable drawable) {
        collapsingToolbar.setContentScrim(drawable);
    }


    public void setHeaderContentScrim(@DrawableRes int resId) {
        setHeaderContentScrim(ContextCompat.getDrawable(this, resId));
    }


    public void setHeaderTextColor(@ColorInt int color) {
        collapsingToolbar.setCollapsedTitleTextColor(color);
        slogan.setTextColor(color);
        version.setTextColor(color);
    }


    /**
     * Set the icon to use for the toolbar's navigation button.
     *
     * @param resId Resource ID of a drawable to set
     */
    public void setNavigationIcon(@DrawableRes int resId) {
        toolbar.setNavigationIcon(resId);
    }


    public void setNavigationIcon(@NonNull Drawable drawable) {
        toolbar.setNavigationIcon(drawable);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    public void setTitle(@NonNull CharSequence title) {
        collapsingToolbar.setTitle(title);
    }


    public Toolbar toolbar() {
        return toolbar;
    }


    public CollapsingToolbarLayout collapsingToolbar() {
        return collapsingToolbar;
    }


    public Items items() {
        return items;
    }


    public MultiTypeAdapter adapter() {
        return adapter;
    }


    public TextView sloganTextView() {
        return slogan;
    }


    public TextView versionTextView() {
        return version;
    }
}
