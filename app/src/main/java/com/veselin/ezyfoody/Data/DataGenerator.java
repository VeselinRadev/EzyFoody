package com.veselin.ezyfoody.Data;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.appcompat.content.res.AppCompatResources;

import com.veselin.ezyfoody.Models.ShopCategory;
import com.veselin.ezyfoody.Models.ShopProduct;
import com.veselin.ezyfoody.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static Random r = new Random();

    public static int randInt(int i) {
        return r.nextInt(i + 0 + 1) + 0;
    }


    public static List<ShopCategory> getShoppingCategory(Context context) {
        ArrayList arrayList = new ArrayList();
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.shop_category_icon);
        //TypedArray obtainTypedArray2 = context.getResources().obtainTypedArray(R.array.shop_category_bg);
        String[] stringArray = context.getResources().getStringArray(R.array.shop_category_title);
       // String[] stringArray2 = context.getResources().getStringArray(R.array.shop_category_brief);
        for (int i = 0; i < obtainTypedArray.length(); i++) {
            ShopCategory shopCategory = new ShopCategory();
            shopCategory.image = obtainTypedArray.getResourceId(i, -1);
            //shopCategory.image_bg = obtainTypedArray2.getResourceId(i, -1);
            shopCategory.title = stringArray[i];
            //shopCategory.brief = stringArray2[i];
            shopCategory.imageDrw = AppCompatResources.getDrawable(context, shopCategory.image);
            arrayList.add(shopCategory);
        }
        return arrayList;
    }
    public static List<ShopProduct> getShoppingProduct(Context context) {
        ArrayList arrayList = new ArrayList();
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.shop_product_image);
        String[] stringArray = context.getResources().getStringArray(R.array.shop_product_title);
        String[] stringArray2 = context.getResources().getStringArray(R.array.shop_product_price);
        for (int i = 0; i < obtainTypedArray.length(); i++) {
            ShopProduct shopProduct = new ShopProduct();
            shopProduct.image = obtainTypedArray.getResourceId(i, -1);
            shopProduct.title = stringArray[i];
            shopProduct.price = stringArray2[i];
            shopProduct.imageDrw = context.getResources().getDrawable(shopProduct.image);
            arrayList.add(shopProduct);
        }
        return arrayList;
    }
    public static ShopProduct getSingleShoppingProduct(Context context, String title){
        ShopProduct shopProduct = new ShopProduct();
        List<ShopProduct> list = getShoppingProduct(context);
        for(ShopProduct sp : list){
            if(sp.title.equals(title)){ shopProduct = sp; break;}
        }
        return shopProduct;
    }
    private static int getRandomIndex(int i) {
        return r.nextInt(i - 1);
    }
}
