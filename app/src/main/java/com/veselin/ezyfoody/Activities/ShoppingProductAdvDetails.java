package com.veselin.ezyfoody.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.veselin.ezyfoody.Data.DataGenerator;
import com.veselin.ezyfoody.Models.ShopOrder;
import com.veselin.ezyfoody.Models.ShopProduct;
import com.veselin.ezyfoody.R;
import com.veselin.ezyfoody.Utils.Tools;

public class ShoppingProductAdvDetails extends AppCompatActivity {

    /* access modifiers changed from: private */
    public View parent_view;
    /* access modifiers changed from: private */
    public TextView tv_qty;
    public ShopProduct product;
    public ShopOrder order;
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_shopping_product_adv_details);
        this.parent_view = findViewById(R.id.parent_view);
        product = DataGenerator.getSingleShoppingProduct(this, getIntent().getStringExtra("PRODUCT"));
        order = new ShopOrder();
        order.product = product;
        initComponent();
    }


    private void initComponent() {
        Tools.displayImageOriginal(this, findViewById(R.id.image), product.image);
        Tools.displayImageOriginal(this, findViewById(R.id.image_1), product.image);
        Tools.displayImageOriginal(this, findViewById(R.id.image_2), product.image);
        Tools.displayImageOriginal(this, findViewById(R.id.image_3), product.image);
        Tools.displayImageOriginal(this, findViewById(R.id.image_4), product.image);
        Tools.displayImageOriginal(this, findViewById(R.id.image_5), product.image);

        initQuantityMenu();
        ((AppCompatButton) findViewById(R.id.bt_add_to_cart)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt(order.product.title, order.quantity);
                editor.commit();
                Toast.makeText(ShoppingProductAdvDetails.this, "Added to the cart!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
        ((TextView)findViewById(R.id.name)).setText(getIntent().getStringExtra("PRODUCT"));

    }

    private void initQuantityMenu(){
        TextView qtxt = findViewById(R.id.quantity);
        ImageButton plus = findViewById(R.id.plus);
        ImageButton minus = findViewById(R.id.minus);

        order.quantity = Integer.parseInt(qtxt.getText().toString());
        plus.setOnClickListener(view -> {
            order.quantity++;
            qtxt.setText(String.valueOf(order.quantity));
        });
        minus.setOnClickListener(view -> {
            if(order.quantity > 1) {
                order.quantity--;
                qtxt.setText(String.valueOf(order.quantity));
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
            startActivity(new Intent(ShoppingProductAdvDetails.this, ShoppingCartCard.class));
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
