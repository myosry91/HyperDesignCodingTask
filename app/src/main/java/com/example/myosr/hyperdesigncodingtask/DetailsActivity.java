package com.example.myosr.hyperdesigncodingtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.myosr.hyperdesigncodingtask.dataProccess.KeyTags;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {

    private ImageView clicked , next , previous;
    final static String api = "http://grapesnberries.getsandbox.com/products?count=10&from=1";

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

        try {
            JSONArray jsonArray = new JSONArray(api);
            JSONObject mainObjectArray = jsonArray.getJSONObject
                    (index);
            String imgUrl = String.valueOf
                    (mainObjectArray.getJSONObject(KeyTags.imgKey).getString(KeyTags.urlKey));
            Picasso.with(getApplicationContext()).load(imgUrl).into(next);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Display previous item image
        try {
            JSONArray jsonArray = new JSONArray(api);
            JSONObject mainObjectArray = jsonArray.getJSONObject
                    (index-2);
            String imgUrl = String.valueOf
                    (mainObjectArray.getJSONObject(KeyTags.imgKey).getString(KeyTags.urlKey));
            Picasso.with(getApplicationContext()).load(imgUrl).into(previous);

        } catch (JSONException e) {
            e.printStackTrace();
        }




        }
}
