package com.veselin.ezyfoody.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.veselin.ezyfoody.Adapters.AdapterGridShopProductCard;
import com.veselin.ezyfoody.Adapters.AdapterShopCheckout;
import com.veselin.ezyfoody.Data.DataGenerator;
import com.veselin.ezyfoody.Models.ShopOrder;
import com.veselin.ezyfoody.Models.ShopProduct;
import com.veselin.ezyfoody.R;
import com.veselin.ezyfoody.Utils.Tools;
import com.veselin.ezyfoody.Widget.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCartCard extends AppCompatActivity {
    private List<ShopOrder> orderList;
    private  AdapterShopCheckout adapterShopCheckout;
    private RecyclerView recyclerView;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_shopping_cart_card);
        loadOrders();
        initRecyclerView();
        initCheckoutBtn();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void loadOrders(){
        orderList = new ArrayList<>();
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        if(sharedpreferences.contains("Air Mineral")){
            ShopOrder order = new ShopOrder();
            order.product = DataGenerator.getSingleShoppingProduct(this, "Air Mineral");
            order.quantity = sharedpreferences.getInt("Air Mineral", 0);
            if(order.quantity != 0)
                orderList.add(order);
        }
        if(sharedpreferences.contains("Jus Mangga")){
            ShopOrder order = new ShopOrder();
            order.product = DataGenerator.getSingleShoppingProduct(this, "Jus Mangga");
            order.quantity = sharedpreferences.getInt("Jus Mangga", 0);
            if(order.quantity != 0)
                orderList.add(order);
        }
        if(sharedpreferences.contains("Jus Alpukat")){
            ShopOrder order = new ShopOrder();
            order.product = DataGenerator.getSingleShoppingProduct(this, "Jus Alpukat");
            order.quantity = sharedpreferences.getInt("Jus Alpukat", 0);
            if(order.quantity != 0)
                orderList.add(order);
        }
        if(sharedpreferences.contains("Jus Apel")){
            ShopOrder order = new ShopOrder();
            order.product = DataGenerator.getSingleShoppingProduct(this, "Jus Apel");
            order.quantity = sharedpreferences.getInt("Jus Apel", 0);
            if(order.quantity != 0)
                orderList.add(order);
        }
    }
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapterShopCheckout = new AdapterShopCheckout(this, orderList);
        recyclerView.setAdapter(adapterShopCheckout);
    }
    public void initCheckoutBtn(){
        Button btn  = findViewById(R.id.checkout_btn);
            btn.setOnClickListener(view -> {
                findViewById(R.id.finished_order_msg).setVisibility(View.VISIBLE);
                recyclerView.setOnClickListener(view1 ->{});
                btn.setText("GO BACK");
                adapterShopCheckout.notifyDataSetChanged();
                btn.setOnClickListener(view2 -> {
                    SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                    sharedpreferences.edit().clear().apply();
                    startActivity(new Intent(ShoppingCartCard.this, MainActivity.class));
                    finish();
                });
            });

    }
}
