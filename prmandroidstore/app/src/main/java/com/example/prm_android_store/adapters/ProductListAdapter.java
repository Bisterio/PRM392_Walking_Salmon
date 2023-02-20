package com.example.prm_android_store.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.data.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Product> productList;

    //getting the context and product list with constructor
    public ProductListAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Product product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewProductID.setText(String.valueOf(product.getId()));
        holder.textViewProductName.setText(product.getName());
        holder.textViewProductBrand.setText(product.getBrand().getName());
        holder.textViewModelYear.setText(String.valueOf(product.getModel_year()));
        holder.textViewPrice.setText(String.format("%,.0f", product.getList_price()) + "₫");

        Picasso.get().load(product.getImage()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewProductID, textViewProductName, textViewProductBrand, textViewModelYear, textViewPrice;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewProductID = itemView.findViewById(R.id.tvProductId);
            textViewProductName = itemView.findViewById(R.id.tvProductName);
            textViewProductBrand = itemView.findViewById(R.id.tvProductBrand);
            textViewModelYear = itemView.findViewById(R.id.tvProductModelYear);
            textViewPrice = itemView.findViewById(R.id.tvProductPrice);
            imageView = itemView.findViewById(R.id.ivProduct);
        }
    }
}
