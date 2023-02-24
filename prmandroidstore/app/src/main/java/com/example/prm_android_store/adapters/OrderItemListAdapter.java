package com.example.prm_android_store.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.data.CartItem;
import com.example.prm_android_store.data.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderItemListAdapter extends RecyclerView.Adapter<OrderItemListAdapter.OrderItemViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<OrderItem> orderItemList;

    //getting the context and product list with constructor
    public OrderItemListAdapter(Context mCtx, List<OrderItem> orderItemList) {
        this.mCtx = mCtx;
        this.orderItemList = orderItemList;
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_order_item_recycler_view, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderItemViewHolder holder, int position) {
        // Getting the product of the specified position
        OrderItem orderItem = orderItemList.get(position);

        // Binding the data with the viewholder views
        holder.textViewProductName.setText(orderItem.getProduct().getName());
        holder.textViewProductBrand.setText(orderItem.getProduct().getBrand().getName());
        holder.textViewQuantity.setText(String.valueOf(orderItem.getQuantity()));
        holder.textViewPrice.setText(String.format("%,.0f", orderItem.getProduct().getList_price()) + "₫");

        Picasso.get().load(orderItem.getProduct().getImage()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return orderItemList.size();
    }


    class OrderItemViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProductName, textViewProductBrand, textViewPrice, textViewQuantity;
        ImageView imageView;

        public OrderItemViewHolder(View itemView) {
            super(itemView);

            textViewProductName = itemView.findViewById(R.id.tvProductName);
            textViewProductBrand = itemView.findViewById(R.id.tvProductBrand);
            textViewQuantity = itemView.findViewById(R.id.tvProductQuantity);
            textViewPrice = itemView.findViewById(R.id.tvProductPrice);
            imageView = itemView.findViewById(R.id.ivProduct);
        }
    }
}
