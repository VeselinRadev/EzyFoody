package com.veselin.ezyfoody.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.veselin.ezyfoody.Models.ShopOrder;
import com.veselin.ezyfoody.R;
import com.veselin.ezyfoody.Utils.Tools;

import java.util.List;

public class AdapterShopCheckout extends RecyclerView.Adapter<AdapterShopCheckout.RecyclerViewHolder> {
    private Context context;
    private List<ShopOrder> orders;
    private static int total = 0;
    public AdapterShopCheckout(Context context, List<ShopOrder> orders){
        this.context = context;
        this.orders = orders;
    }
    @NonNull
    @Override
    public AdapterShopCheckout.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_checkout, parent, false);
        return new AdapterShopCheckout.RecyclerViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterShopCheckout.RecyclerViewHolder holder, int position) {
        final ShopOrder order = orders.get(position);
        holder.getName().setText(order.product.title);
        holder.getPrice().setText("Rp " + String.valueOf(order.quantity * 123));
        total += order.quantity * 123;
        Tools.displayImageOriginal(context, holder.getImage(), order.product.image);
        holder.getQtxt().setText(String.valueOf(order.quantity));
        if( ((TextView)((Activity)context).findViewById(R.id.total)).getText() == "CHECKOUT") {
            holder.getPlus().setOnClickListener(view -> {
                order.quantity++;
                total += 123;
                ((TextView) ((Activity) context).findViewById(R.id.total)).setText("Rp " + String.valueOf(total));
                holder.getQtxt().setText(String.valueOf(order.quantity));
                holder.getPrice().setText("Rp " + String.valueOf(order.quantity * 123));
                SharedPreferences sharedpreferences = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt(order.product.title, order.quantity);
                editor.commit();
            });
            holder.getMinus().setOnClickListener(view -> {
                if (order.quantity > 0) {
                    order.quantity--;
                    total -= 123;
                    ((TextView) ((Activity) context).findViewById(R.id.total)).setText("Rp " + String.valueOf(total));
                    holder.getQtxt().setText(String.valueOf(order.quantity));
                    holder.getPrice().setText("Rp " + String.valueOf(order.quantity * 123));
                    SharedPreferences sharedpreferences = context.getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putInt(order.product.title, order.quantity);
                    editor.commit();
                }
            });
        }else{
            holder.getPlus().setOnClickListener(view -> {});
            holder.getMinus().setOnClickListener(view -> {});
        }
        ((TextView) ((Activity) context).findViewById(R.id.total)).setText("Rp " + String.valueOf(total));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView price;
        private ImageView image;
        private TextView qtxt;
        private ImageButton plus;
        private ImageButton minus;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.name);
            this.price = itemView.findViewById(R.id.price);
            this.image = itemView.findViewById(R.id.image);
            this.qtxt = itemView.findViewById(R.id.quantity);
            this.plus = itemView.findViewById(R.id.plus);
            this.minus = itemView.findViewById(R.id.minus);
        }

        public TextView getName(){
            return title;
        }
        public TextView getPrice() {
            return price;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getQtxt() {
            return qtxt;
        }

        public ImageButton getPlus() {
            return plus;
        }

        public ImageButton getMinus() {
            return minus;
        }

    }
}
