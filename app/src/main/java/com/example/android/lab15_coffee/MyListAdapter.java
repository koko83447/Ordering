package com.example.android.lab15_coffee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by android on 2017/8/29.
 */

public class MyListAdapter extends BaseAdapter {

    private List<Burger> mBurgerList;

    public MyListAdapter(List<Burger> burgerList){
        this.mBurgerList = burgerList;
    }

    @Override
    public int getCount() {
        return mBurgerList.size();
    }

    @Override
    public Object getItem(int i) {
        return mBurgerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View covnertView, ViewGroup parent) {
        Context context = parent.getContext();
        covnertView = LayoutInflater.from(context).inflate(R.layout.listview_layout,null);


        TextView tvItemId = (TextView)covnertView.findViewById(R.id.itemId);
        TextView tvItemTitle = (TextView)covnertView.findViewById(R.id.itemTitle);
        TextView tvItemPrice = (TextView)covnertView.findViewById(R.id.itemPrice);
        TextView tvItemTea = covnertView.findViewById(R.id.itemTea);
        TextView tvItemQuantity = covnertView.findViewById(R.id.itemQuantity);
        ImageView iv_ItemImage = (ImageView)covnertView.findViewById(R.id.itemImage);

        Burger burger = mBurgerList.get(position);

        tvItemTitle.setText(burger.getName());
        tvItemPrice.setText(Integer.toString(burger.getTotal()));
        tvItemTea.setText(burger.getTea());
        tvItemQuantity.setText(Integer.toString(burger.getQuantity())+"份");
        iv_ItemImage.setImageResource(covnertView.getResources().getIdentifier(burger.getPic(),"drawable", context.getPackageName()));
        return covnertView;
    }

    public void addBurger(Burger burger) {
        mBurgerList.add(burger);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mBurgerList.remove(position);
        notifyDataSetChanged();
    }
}
