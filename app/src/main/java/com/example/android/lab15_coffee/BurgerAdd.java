package com.example.android.lab15_coffee;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BurgerAdd extends BaseAdapter{

    private List<Burger> products = new ArrayList<Burger>();
    private final Context context;

    public BurgerAdd(Context context) {
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
        //TextView tvName,tvText;
        ImageView ivImage;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.content_welcome,parent,false);

            //tvName = convertView.findViewById(R.id.burgername);
            //tvText = convertView.findViewById(R.id.burgerdetail);
            ivImage = convertView.findViewById(R.id.menuimage);
            //convertView.setTag(new ViewHolder(tvName, tvText, ivImage));
            convertView.setTag(new ViewHolder(ivImage));
        }else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            //tvName = viewHolder.tvProductName;
            //tvText = viewHolder.tvProductText;
            ivImage = viewHolder.ivProductImage;
        }
        final Burger product = (Burger) getItem(position);
        //tvName.setText(product.getName());
        //tvText.setText(product.getText());
        ivImage.setImageResource(context.getResources().getIdentifier(product.getMenu(),"drawable",context.getPackageName()));

        return convertView;
    }
    private static class ViewHolder{
        //public final TextView tvProductName;
        //public final TextView tvProductText;
        public final ImageView ivProductImage;

        public ViewHolder(ImageView ivProductImage){
            //this.tvProductName = tvProductName;
            //this.tvProductText = tvProductText;
            this.ivProductImage = ivProductImage;
        }
    }
}
