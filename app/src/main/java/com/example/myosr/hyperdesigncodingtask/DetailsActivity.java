package com.example.myosr.hyperdesigncodingtask;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myosr.hyperdesigncodingtask.dataProccess.Connector;
import com.example.myosr.hyperdesigncodingtask.dataProccess.DataEncap;
import com.example.myosr.hyperdesigncodingtask.dataProccess.JsonParser;
import com.example.myosr.hyperdesigncodingtask.dataProccess.KeyTags;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {

    private ImageView clicked , next , previous;
    final static String api = "http://grapesnberries.getsandbox.com/products?count=10&from=1";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        clicked = (ImageView) findViewById(R.id.click_image);
        next = (ImageView) findViewById(R.id.next_image);
        previous = (ImageView) findViewById(R.id.pre_image);

        Intent intent = getIntent();

        // Display clicked item image
        Picasso.with(getApplicationContext()).load(intent.getStringExtra("image")).into(clicked);

        // Display next item image
        int index = intent.getIntExtra("id",0);
        Connector connector = new Connector();

        try {

            JSONArray jsonArray = new JSONArray(connector.execute(api).get());
            JSONObject mainObjectArray = jsonArray.getJSONObject
                    (index);
            String imgUrl = String.valueOf
                    (mainObjectArray.getJSONObject(KeyTags.imgKey).getString(KeyTags.urlKey));
            Picasso.with(getApplicationContext()).load(imgUrl).into(next);

            // display previous image
            JSONObject mainObjectArrayPre = jsonArray.getJSONObject
                    (index-2);
            String imgUrlPre = String.valueOf
                    (mainObjectArrayPre.getJSONObject(KeyTags.imgKey).getString(KeyTags.urlKey));
            Picasso.with(getApplicationContext()).load(imgUrlPre).into(previous);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
