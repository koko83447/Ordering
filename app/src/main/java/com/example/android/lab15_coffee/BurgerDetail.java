package com.example.android.lab15_coffee;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BurgerDetail extends Activity {
    TextView tvName;
    TextView tvText;
    ImageView ivImage;
    Burger burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger_detail);

        Bundle data = getIntent().getExtras();
        burger = (Burger) data.getSerializable("burger");

        Log.d("aaaaa","hashCode:"+burger.hashCode());

        tvName = findViewById(R.id.burgername);
        tvText = findViewById(R.id.burgerdetail);
        ivImage = findViewById(R.id.burgerimage);

        tvName.setText(burger.getName());
        tvText.setText(burger.getText());
        ivImage.setImageResource(this.getResources().getIdentifier(burger.getImage(),"drawable",this.getPackageName()));
    }

    public void backtomenu(View view) {
        Intent intent = new Intent(BurgerDetail.this,WelcomeActivity.class);
        startActivity(intent);
    }

    public void ordering(View view) {
        Intent intent = new Intent(BurgerDetail.this,MainActivity.class);
        startActivity(intent);
    }
}
