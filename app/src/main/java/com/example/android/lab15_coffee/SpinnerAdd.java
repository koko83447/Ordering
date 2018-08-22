package com.example.android.lab15_coffee;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdd extends BaseAdapter{

    private List<Burger> products = new ArrayList<Burger>();
    private final Context context;

    private String burgerName;
    private String burgerImage;

    public SpinnerAdd(Context context) {
        this.context = context;

    }

    public void updataProducts(List<Burger> products){
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tvName;
        ImageView ivImage;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_layout,parent,false);

            tvName = convertView.findViewById(R.id.burger_name);
            ivImage = convertView.findViewById(R.id.burger_image);
            convertView.setTag(new ViewHolder(tvName,ivImage));
        }else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvProductName;
            ivImage = viewHolder.ivProductImage;
        }
        final Burger product = (Burger) getItem(position);
        burgerName = product.getName();
        tvName.setText(burgerName);
        burgerImage = product.getPic();
        ivImage.setImageResource(context.getResources().getIdentifier(burgerImage,"drawable",context.getPackageName()));

        return convertView;
    }

    private static class ViewHolder{
        public final TextView tvProductName;
        public final ImageView ivProductImage;

        public ViewHolder(TextView tvProductName,ImageView ivProductImage){
            this.tvProductName = tvProductName;
            this.ivProductImage = ivProductImage;
        }
    }
}
