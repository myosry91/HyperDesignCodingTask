package com.example.myosr.hyperdesigncodingtask;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.myosr.hyperdesigncodingtask.dataProccess.Connector;
import com.example.myosr.hyperdesigncodingtask.dataProccess.DataEncap;
import com.example.myosr.hyperdesigncodingtask.dataProccess.JsonParser;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    final static String api = "http://grapesnberries.getsandbox.com/products?count=10&from=1";
    JsonParser parser = new JsonParser();
    private RecyclerView recyclerView;
    private AdapterProduct adapter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Connector connector = new Connector();

        try {


            ArrayList<DataEncap> arrayList = parser.JsonProcess(connector.execute(api).get());


            recyclerMain();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void recyclerMain() {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AdapterProduct(parser.getlist(), getApplicationContext(), this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter.notifyDataSetChanged();

    }
}
