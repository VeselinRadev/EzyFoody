package com.veselin.ezyfoody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.veselin.ezyfoody.Models.ShopProduct;
import com.veselin.ezyfoody.R;
import com.veselin.ezyfoody.Utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class AdapterGridShopProductCard extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    /* access modifiers changed from: private */
    public List<ShopProduct> items = new ArrayList();
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;
    /* access modifiers changed from: private */
   // public OnMoreButtonClickListener onMoreButtonClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, ShopProduct shopProduct, int i);
    }

//    public interface OnMoreButtonClickListener {
//        void onItemClick(View view, ShopProduct shopProduct, MenuItem menuItem);
//    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

//    public void setOnMoreButtonClickListener(OnMoreButtonClickListener onMoreButtonClickListener2) {
//        this.onMoreButtonClickListener = onMoreButtonClickListener2;
//    }

    public AdapterGridShopProductCard(Context context, List<ShopProduct> list) {
        this.items = list;
        this.ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public View lyt_parent;
        public ImageButton more;
        public TextView price;
        public TextView title;

        public OriginalViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.title = (TextView) view.findViewById(R.id.title);
            this.price = (TextView) view.findViewById(R.id.price);
//            this.more = (ImageButton) view.findViewById(R.id.more);
            this.lyt_parent = view.findViewById(R.id.lyt_parent);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OriginalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shop_product_card, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof OriginalViewHolder) {
            OriginalViewHolder originalViewHolder = (OriginalViewHolder) viewHolder;
            final ShopProduct shopProduct = this.items.get(i);
            originalViewHolder.title.setText(shopProduct.title);
            originalViewHolder.price.setText(shopProduct.price);
            Tools.displayImageOriginal(this.ctx, originalViewHolder.image, shopProduct.image);
            originalViewHolder.lyt_parent.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (AdapterGridShopProductCard.this.mOnItemClickListener != null) {
                        AdapterGridShopProductCard.this.mOnItemClickListener.onItemClick(view, AdapterGridShopProductCard.this.items.get(i), i);
                    }
                }
            });
//            originalViewHolder.more.setOnClickListener(new View.OnClickListener() {
//                public void onClick(View view) {
//                    if (AdapterGridShopProductCard.this.onMoreButtonClickListener != null) {
//                        AdapterGridShopProductCard.this.onMoreButtonClick(view, shopProduct);
//                    }
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

//    /* access modifiers changed from: private */
//    public void onMoreButtonClick(final View view, final ShopProduct shopProduct) {
//        PopupMenu popupMenu = new PopupMenu(this.ctx, view);
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                AdapterGridShopProductCard.this.onMoreButtonClickListener.onItemClick(view, shopProduct, menuItem);
//                return true;
//            }
//        });
//        popupMenu.inflate(R.menu.menu_product_more);
//        popupMenu.show();
//    }
//
//    public int getItemCount() {
//        return this.items.size();
//    }
}
