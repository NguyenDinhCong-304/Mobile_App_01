package com.example.nguyendinhcong_2122110566;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList){
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        Product product = productList.get(position);

        TextView nameView = convertView.findViewById(R.id.textName);
        TextView priceView = convertView.findViewById(R.id.textPrice);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        nameView.setText(product.getName());
        priceView.setText(String.format("$%.2f", product.getPrice()));

        Picasso.get().load(product.getImageUrl()).into(imageView);

        return convertView;
    }
}
