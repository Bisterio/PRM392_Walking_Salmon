package com.example.prm_android_store.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.activities.OrderDetailActivity;
import com.example.prm_android_store.activities.ProductsActivity;
import com.example.prm_android_store.data.CartItem;
import com.example.prm_android_store.data.Order;
import com.example.prm_android_store.data.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Order> orderList;

    //getting the context and product list with constructor
    public OrderListAdapter(Context mCtx, List<Order> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_order_recycler_view, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        // Getting the product of the specified position
        Order order = orderList.get(position);

        //binding the data with the viewholder views
        holder.textViewOrderID.setText("Order ID: #" + String.valueOf(order.getId()));
        holder.textViewPrice.setText(String.format("%,.0f", order.getTotalPrice()) + "₫");
        holder.textViewQuantity.setText(String.valueOf(order.getTotalQuantity()) + " products");
        holder.textViewOrderDate.setText("Order Date: " + order.getOrder_date());
        if(order.getShipped_date() != null){
            holder.textViewShippedDate.setText("Shipped Date: " + order.getShipped_date());
        } else {
            holder.textViewShippedDate.setText("This order is on its way");
        }

        switch (order.getStatus()){
            case 1:
                holder.textViewStatus.setText("Pending");
                holder.textViewStatus.setTextColor(Color.rgb(233,178,8));
                break;
            case 2:
                holder.textViewStatus.setText("Processing");
                holder.textViewStatus.setTextColor(Color.rgb(124,124,124));
                break;
            case 3:
                holder.textViewStatus.setText("Rejected");
                holder.textViewStatus.setTextColor(Color.rgb(210,0,0));
                break;
            case 4:
                holder.textViewStatus.setText("Completed");
                holder.textViewStatus.setTextColor(Color.rgb(60,130,36));
                break;
        }
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }


    class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewOrderID, textViewPrice, textViewQuantity, textViewStatus, textViewOrderDate, textViewShippedDate;
        TextView detailButton;
        public OrderViewHolder(View itemView) {
            super(itemView);

            textViewOrderID = itemView.findViewById(R.id.tvOrderId);
            textViewPrice = itemView.findViewById(R.id.tvOrderPrice);
            textViewQuantity = itemView.findViewById(R.id.tvOrderQuantity);
            textViewStatus = itemView.findViewById(R.id.tvOrderStatus);
            textViewOrderDate = itemView.findViewById(R.id.tvOrderDate);
            textViewShippedDate = itemView.findViewById(R.id.tvShippedDate);
            detailButton = itemView.findViewById(R.id.tvDetailButton);

            detailButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent order_detail = new Intent(mCtx, OrderDetailActivity.class);
            order_detail.putExtra("orderId", orderList.get(position).getId());
            mCtx.startActivity(order_detail);
        }
    }
}
