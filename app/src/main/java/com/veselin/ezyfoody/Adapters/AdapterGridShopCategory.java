package com.veselin.ezyfoody.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.veselin.ezyfoody.R;
import com.veselin.ezyfoody.Models.ShopCategory;

import java.util.ArrayList;
import java.util.List;

public class AdapterGridShopCategory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context ctx;
    /* access modifiers changed from: private */
    public List<ShopCategory> items = new ArrayList();
    /* access modifiers changed from: private */
    public OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, ShopCategory shopCategory, int i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public AdapterGridShopCategory(Context context, List<ShopCategory> list) {
        this.items = list;
        this.ctx = context;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public View lyt_parent;
        public TextView title;

        public OriginalViewHolder(View view) {
            super(view);
            this.image = (ImageView) view.findViewById(R.id.image);
            this.title = (TextView) view.findViewById(R.id.title);
            this.lyt_parent = view.findViewById(R.id.lyt_parent);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OriginalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shop_category_card, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof OriginalViewHolder) {
            OriginalViewHolder originalViewHolder = (OriginalViewHolder) viewHolder;
            ShopCategory shopCategory = this.items.get(i);
            originalViewHolder.title.setText(shopCategory.title);
            originalViewHolder.image.setImageDrawable(shopCategory.imageDrw);
            originalViewHolder.lyt_parent.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (AdapterGridShopCategory.this.mOnItemClickListener != null) {
                        AdapterGridShopCategory.this.mOnItemClickListener.onItemClick(view, (ShopCategory) AdapterGridShopCategory.this.items.get(i), i);
                    }
                }
            });
        }
    }

    public int getItemCount() {
        return this.items.size();
    }
}
