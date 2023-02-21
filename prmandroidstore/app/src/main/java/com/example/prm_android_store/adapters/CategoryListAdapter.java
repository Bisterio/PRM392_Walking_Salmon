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
import com.example.prm_android_store.activities.ProductsActivity;
import com.example.prm_android_store.data.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Category> categoryList;

    //getting the context and product list with constructor
    public CategoryListAdapter(Context mCtx, List<Category> categoryList) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_category_recycler_view, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        //getting the product of the specified position
        Category category = categoryList.get(position);

        //binding the data with the viewholder views
        holder.textViewCategoryID.setText(String.valueOf(category.getId()));
        holder.textViewCategoryName.setText(category.getName());

        Picasso.get().load(category.getImage()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewCategoryID, textViewCategoryName;
        ImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            textViewCategoryID = itemView.findViewById(R.id.tvCategoryId);
            textViewCategoryName = itemView.findViewById(R.id.tvCategoryName);
            imageView = itemView.findViewById(R.id.ivCategory);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent categorized_products = new Intent(mCtx, ProductsActivity.class);
            categorized_products.putExtra("categoryId", categoryList.get(position).getId());
            categorized_products.putExtra("categoryName", categoryList.get(position).getName());
            mCtx.startActivity(categorized_products);
        }
    }
}
