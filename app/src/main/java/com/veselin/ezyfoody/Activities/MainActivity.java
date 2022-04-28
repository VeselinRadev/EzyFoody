package com.veselin.ezyfoody.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.veselin.ezyfoody.Adapters.AdapterGridShopCategory;
import com.veselin.ezyfoody.Data.DataGenerator;
import com.veselin.ezyfoody.Models.ShopCategory;
import com.veselin.ezyfoody.R;
import com.veselin.ezyfoody.Utils.Tools;
import com.veselin.ezyfoody.Widget.SpacingItemDecoration;


public class MainActivity extends AppCompatActivity {
    private AdapterGridShopCategory mAdapter;
    /* access modifiers changed from: private */
    public View parent_view;
    private RecyclerView recyclerView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        this.parent_view = findViewById(R.id.parent_view);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon((int) R.drawable.ic_menu);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle((CharSequence) "Categories");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.recyclerView);
        this.recyclerView = recyclerView2;
        recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
        this.recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 8), true));
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(false);
        AdapterGridShopCategory adapterGridShopCategory = new AdapterGridShopCategory(this, DataGenerator.getShoppingCategory(this));
        this.mAdapter = adapterGridShopCategory;
        this.recyclerView.setAdapter(adapterGridShopCategory);
        this.mAdapter.setOnItemClickListener(new AdapterGridShopCategory.OnItemClickListener() {
            public void onItemClick(View view, ShopCategory shopCategory, int i) {
                //View access$000 = ShoppingCategoryCard.this.parent_view;
                startActivity(new Intent(MainActivity.this, ShoppingSubCategoryTabs.class));
               // Snackbar.make(access$000, (CharSequence) "Item " + shopCategory.title + " clicked", ).show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart_setting, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        } else {
            startActivity(new Intent(MainActivity.this, ShoppingCartCard.class));
        }
        return super.onOptionsItemSelected(menuItem);
    }
}