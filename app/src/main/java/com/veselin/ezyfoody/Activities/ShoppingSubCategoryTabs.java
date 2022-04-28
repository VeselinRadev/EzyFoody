package com.veselin.ezyfoody.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.veselin.ezyfoody.Adapters.AdapterGridShopProductCard;
import com.veselin.ezyfoody.Data.DataGenerator;
import com.veselin.ezyfoody.Models.ShopProduct;
import com.veselin.ezyfoody.R;
import com.veselin.ezyfoody.Utils.Tools;
import com.veselin.ezyfoody.Widget.SpacingItemDecoration;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingSubCategoryTabs extends AppCompatActivity {
    public View parent_view;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_shopping_sub_category_tabs);
        this.parent_view = findViewById(R.id.parent_view);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 8), true));
        recyclerView.setHasFixedSize(true);
        List<ShopProduct> shoppingProduct = DataGenerator.getShoppingProduct(this);
        Collections.shuffle(shoppingProduct);
        AdapterGridShopProductCard adapterGridShopProductCard = new AdapterGridShopProductCard(this, shoppingProduct);
        recyclerView.setAdapter(adapterGridShopProductCard);
        adapterGridShopProductCard.setOnItemClickListener((view, shopProduct, i) -> {
            startActivity(new Intent(ShoppingSubCategoryTabs.this, ShoppingProductAdvDetails.class).putExtra("PRODUCT", shopProduct.title));
            //Snackbar.make(view2, (CharSequence) "Item " + shopProduct.title + " clicked", -1).show();
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
            startActivity(new Intent(ShoppingSubCategoryTabs.this, ShoppingCartCard.class));
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
