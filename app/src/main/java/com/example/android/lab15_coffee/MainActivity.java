package com.example.android.lab15_coffee;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MyDialogFragment.okcancel,
        AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private static final String TAG = "MainActivity";

    private ListView mListView;
    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new MyDialogFragment();
                dialog.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        });
        initListView();
    }

    private void initListView() {
        mAdapter = new MyListAdapter(new ArrayList<Burger>());
        mListView = (ListView)findViewById(R.id.listview);
        mListView.setEmptyView(findViewById(R.id.empty));
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
    }


    @Override
    public void ok(Burger burger) {
        mAdapter.addBurger(burger);
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        Snackbar.make(fab,"點選了第"+position+"項",Snackbar.LENGTH_SHORT).setAction("Action",null).show();

    }

    public void ordering(View view) {
        if (mAdapter.getCount() == 0) {
            Snackbar.make(getWindow().getDecorView(),R.string.warning,Snackbar.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("mailto:koko83447@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"一點心輕食茶飲訂購服務");

        String order_List = "";
        int total_All = 0;
        for (int i = 0; i < mListView.getAdapter().getCount(); i++) {
            Burger burger = (Burger) mListView.getAdapter().getItem(i);
            order_List += burger.getName()+burger.getQuantity()+"份　"+burger.getTea()+"　價格:"+burger.getTotal()+"\n";
            total_All += burger.getTotal();
        }
        String ordering = "您好,\n以下為您所訂購的餐點\n\n"+order_List+"\n您所訂購的餐點總額為"+total_All+"元\n\n如以上餐點與價格沒問題請發送此信件\n我們將為您準備餐點\n感謝您的訂餐.並祝您有美好的一天";
        intent.putExtra(Intent.EXTRA_TEXT,ordering);

        startActivity(intent);

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        final int remove = position;
        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
        adb.setTitle("刪除?")
        .setIcon(android.R.drawable.ic_delete)
        .setMessage("請問要刪除此餐點嗎?")
                .setNegativeButton("取消",null)
                .setPositiveButton("確定",new AlertDialog.OnClickListener(){
                    public void onClick(DialogInterface dialog,int witch){
                        //長按刪除功能
                        mAdapter.remove(position);
                    }
                });
        adb.show();
        return false;
    }
}
