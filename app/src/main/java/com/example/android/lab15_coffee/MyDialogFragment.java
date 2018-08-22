package com.example.android.lab15_coffee;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by android on 2017/8/25.
 */

public class MyDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private okcancel okCancelHandler;
    private View mDialogView;
    private AlertDialog mDialog;
    private Spinner mSpinner;
    private SpinnerAdd spinnerAdd;

    private int quantuty = 1;
    private int combo = 0;
    private TextView price_tv;
    private int total_price;
    String burger_tea = "單點";

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        resetTotalPrice();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public interface okcancel {
        void ok(Burger burger);
        void cancel();
    }

    public  MyDialogFragment(){

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
         super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        mDialogView = inflater.inflate(R.layout.fragment_my_dialog,null);

        iniOkCancelHandler();
        initSpinner();
        initDialog();
        initButton();

        RadioGroup radio_combo = mDialogView.findViewById(R.id.radio_combo);
        radio_combo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.notea:
                        combo = 0;
                        burger_tea = "";
                        burger_tea += "單點";
                        resetTotalPrice();
                        break;
                    case R.id.redtea:
                        combo = 10;
                        burger_tea = "";
                        burger_tea += "+ 紅茶";
                        resetTotalPrice();
                        break;
                    case R.id.greentea:
                        combo = 10;
                        burger_tea = "";
                        burger_tea += "+ 綠茶";
                        resetTotalPrice();
                        break;
                    case R.id.milktea:
                        combo = 20;
                        burger_tea = "";
                        burger_tea += "+ 奶茶";
                        resetTotalPrice();
                        break;
                }
            }
        });

        return mDialog;
    }

    private void initButton() {
        Button inc = mDialogView.findViewById(R.id.btn_inc);
        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++quantuty;
                displayQuantity();
                resetTotalPrice();
            }
        });
        Button dec = mDialogView.findViewById(R.id.btn_dec);
        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantuty > 1){
                    --quantuty;
                    displayQuantity();
                }
                resetTotalPrice();
            }
        });
    }

    private void displayQuantity(){
        TextView tvQuantity = mDialogView.findViewById(R.id.order_view);
        tvQuantity.setText(String.valueOf(quantuty));
    }

    private void resetTotalPrice() {
        int combo_price = combo * quantuty;
        total_price = quantuty * getBurger().getPrice() + combo_price;
        getBurger().setTotal(total_price);
        getBurger().setTea(burger_tea);
        getBurger().setQuantity(quantuty);
        TextView priceTextView = mDialogView.findViewById(R.id.price_text_view);
        priceTextView.setText("NT$"+total_price);
    }


    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("新增餐點")
                .setIcon(android.R.drawable.ic_input_add)
                .setView(mDialogView)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            Burger burger = getBurger();
                            okCancelHandler.ok(burger);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        okCancelHandler.cancel();
                    }
                });
        mDialog =  builder.create();
    }

    private void iniOkCancelHandler() {
        try{
            okCancelHandler = (okcancel)getActivity();
        }catch (ClassCastException cause){
            String message = "Activity無法處理確定取消";
            throw new MyDialogFragmentException(message,cause);
        }
    }

    private void initSpinner() {
        try{
            mSpinner = (Spinner)mDialogView.findViewById(R.id.burger_spinner);
            Activity activity = getActivity();

            spinnerAdd = new SpinnerAdd(activity);
            spinnerAdd.updataProducts(Constant.getProductList());
            mSpinner.setAdapter(spinnerAdd);
            mSpinner.setOnItemSelectedListener(this);
            int position = 0;
            mSpinner.setSelection(position);
        }catch (ClassCastException cause){
            String message = "Activity無法處理確定取消";
            throw new MyDialogFragmentException(message, cause);
        }
    }


    private Burger getBurger() {
        int position = mSpinner.getSelectedItemPosition();
        Burger burger = (Burger) spinnerAdd.getItem(position);
        return burger;
    }
}
