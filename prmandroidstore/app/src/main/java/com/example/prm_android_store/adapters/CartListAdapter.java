package com.example.prm_android_store.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.activities.ProductDetailActivity;
import com.example.prm_android_store.data.CartItem;
import com.example.prm_android_store.data.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartItemViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<CartItem> cartList;

    //getting the context and product list with constructor
    public CartListAdapter(Context mCtx, List<CartItem> cartList) {
        this.mCtx = mCtx;
        this.cartList = cartList;
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_cart_recycler_view, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CartItemViewHolder holder, int position) {
        // Getting the product of the specified position
        CartItem cartItem = cartList.get(position);

        // Binding the data with the viewholder views
        holder.textViewProductID.setText(String.valueOf(cartItem.getProduct().getId()));
        holder.textViewProductName.setText(cartItem.getProduct().getName());
        holder.textViewProductBrand.setText(cartItem.getProduct().getBrand());
        holder.textViewQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.textViewPrice.setText(String.format("%,.0f", cartItem.getProduct().getList_price()) + "₫");

        Picasso.get().load(cartItem.getProduct().getImage()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }


    class CartItemViewHolder extends RecyclerView.ViewHolder{

        TextView textViewProductID, textViewProductName, textViewProductBrand, textViewPrice, textViewQuantity;
        ImageView imageView;
        TextView removeButton, increaseQuantityButton, decreaseQuantityButton;

        public CartItemViewHolder(View itemView) {
            super(itemView);

            textViewProductID = itemView.findViewById(R.id.tvProductId);
            textViewProductName = itemView.findViewById(R.id.tvProductName);
            textViewProductBrand = itemView.findViewById(R.id.tvProductBrand);
            textViewQuantity = itemView.findViewById(R.id.tvQuantity);
            textViewPrice = itemView.findViewById(R.id.tvProductPrice);
            imageView = itemView.findViewById(R.id.ivProduct);
            removeButton = itemView.findViewById(R.id.tvItemRemoveButton);
            increaseQuantityButton = itemView.findViewById(R.id.tvAddQuantity);
            decreaseQuantityButton = itemView.findViewById(R.id.tvSubQuantity);
        }
    }
}
