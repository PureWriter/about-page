package me.drakeet.support.about;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author drakeet
 */
public abstract class AbsAboutActivity extends AppCompatActivity implements View.OnClickListener {

    protected Toolbar toolbar;
    protected CollapsingToolbarLayout collapsingToolbar;

    protected Items items;
    protected MultiTypeAdapter adapter;
    protected TextView slogan, version;


    protected abstract void onCreateHeader(ImageView icon, TextView slogan, TextView version);
    protected abstract void onItemsCreated(@NonNull Items items);


    @Nullable
    protected CharSequence onCreateTitle() {
        return null;
    }


    protected void onActionClick(View action) {}


    @SuppressWarnings("ConstantConditions") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page_main_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView icon = (ImageView) findViewById(R.id.icon);
        slogan = (TextView) findViewById(R.id.slogan);
        version = (TextView) findViewById(R.id.version);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        onCreateHeader(icon, slogan, version);

        final CharSequence title = onCreateTitle();
        if (title != null) {
            collapsingToolbar.setTitle(title);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        onSetupRecyclerView(recyclerView);
    }


    private void onSetupRecyclerView(RecyclerView recyclerView) {
        adapter = new MultiTypeAdapter();
        adapter.register(Category.class, new CategoryViewBinder());
        adapter.register(Card.class, new CardViewBinder(this));
        adapter.register(Line.class, new LineViewBinder());
        adapter.register(Contributor.class, new ContributorViewBinder());
        adapter.register(License.class, new LicenseViewBinder());
        items = new Items();
        onItemsCreated(items);
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
    }


    /**
     * Set the header view background to a given resource and replace the default value
     * ?attr/colorPrimary.
     * The resource should refer to a Drawable object or 0 to remove the background.
     *
     * @param resId The identifier of the resource.
     */
    public void setHeaderBackgroundResource(@DrawableRes int resId) {
        if (collapsingToolbar == null) {
            collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        }
        collapsingToolbar.setContentScrimResource(resId);
        collapsingToolbar.setBackgroundResource(resId);
    }


    public void setHeaderContentColor(@ColorInt int color) {
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


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    public void setTitle(CharSequence title) {
        collapsingToolbar.setTitle(title);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.action) {
            onActionClick(v);
        }
    }
}
