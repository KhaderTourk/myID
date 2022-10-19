package com.example.myshop.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myshop.R;
import com.example.myshop.data.Product;

import java.util.ArrayList;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
        private ArrayList<Product> products;

        public ProductAdapter(ArrayList<Product> products) {
            this.products = products;
        }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.product_item, parent, false);
        return new ViewHolder(listItem);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Product myListData = products.get(position);
        holder.productName.setText(products.get(position).getName());
        holder.productPrice.setText(products.get(position).getPrice());
        holder.productType.setText(products.get(position).getType());
        holder.productImage.setImageURI(products.get(position).getImage());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName;
        private final TextView productPrice;
        private final TextView productType;
        private final ImageView productImage;
        public CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            this.productName = (TextView) itemView.findViewById(R.id.tv_product_name_card);
            this.productPrice = (TextView) itemView.findViewById(R.id.tv_product_price_card);
            this.productType = (TextView) itemView.findViewById(R.id.tv_product_type);
            this.productImage = (ImageView) itemView.findViewById(R.id.iv_product_card);
            this.card = (CardView)itemView.findViewById(R.id.item_card);
        }
    }
}
