package com.example.android.lab15_coffee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.Serializable;

public class WelcomeActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mListView = findViewById(R.id.list_item);
        BurgerAdd burgerAdd = new BurgerAdd(this);
        burgerAdd.updataProducts(Constant.getProductList());

        mListView.setAdapter(burgerAdd);

        LayoutInflater inflater = getLayoutInflater();
        mListView.addFooterView(inflater.inflate(R.layout.footer,mListView,false));

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Burger product = Constant.getProductList().get(position);
                Intent intent = new Intent(WelcomeActivity.this,BurgerDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("burger",product);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void ordering(View view) {
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
    }



}
