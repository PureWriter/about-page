package me.drakeet.support.about;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import me.drakeet.multitype.Item;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author drakeet
 */
public abstract class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    protected Items items;
    private MultiTypeAdapter adapter;


    protected abstract void onCreateHeader(ImageView icon, TextView slogan, TextView version);
    protected abstract void onItemsCreated(@NonNull Items items);


    @Nullable protected CharSequence onCreateTitle() {
        return null;
    }


    protected void onActionClick(View action) {}


    @SuppressWarnings("ConstantConditions") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_page_main_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageView icon = (ImageView) findViewById(R.id.icon);
        TextView slogan = (TextView) findViewById(R.id.slogan);
        TextView version = (TextView) findViewById(R.id.version);
        onCreateHeader(icon, slogan, version);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        final CharSequence title = onCreateTitle();
        if (title != null) {
            collapsingToolbar.setTitle(title);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        onSetupRecyclerView(recyclerView);
    }


    private void onSetupRecyclerView(RecyclerView recyclerView) {
        items = new Items();
        onItemsCreated(items);
        adapter = new MultiTypeAdapter(items);
        adapter.register(Category.class, new CategoryViewProvider());
        adapter.register(Card.class, new CardViewProvider(this));
        adapter.register(Line.class, new LineViewProvider());
        adapter.register(Contributor.class, new ContributorViewProvider());
        adapter.register(License.class, new LicenseViewProvider());
        recyclerView.setAdapter(adapter);
    }


    @Override public void setTitle(CharSequence title) {
        collapsingToolbar.setTitle(title);
    }


    @Override public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.action) {
            onActionClick(v);
        }
    }
}
