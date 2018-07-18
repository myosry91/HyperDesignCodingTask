package com.example.myosr.hyperdesigncodingtask.dataProccess;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hossam on 1/7/17.
 */

public class JsonParser {

    ArrayList<DataEncap> data;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ArrayList<DataEncap> JsonProcess(String jsonFile) {

        data = new ArrayList<>();

        try {


            JSONArray jsonArray = new JSONArray(jsonFile);


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject mainObjectArray = jsonArray.getJSONObject(i);

                DataEncap encap = new DataEncap(

                        mainObjectArray.getInt(KeyTags.idKey),
                        mainObjectArray.getString(KeyTags.descKey),
                        mainObjectArray.getInt(KeyTags.priceKey),
                        String.valueOf(mainObjectArray.getJSONObject(KeyTags.imgKey).getString(KeyTags.urlKey)),
                        Integer.parseInt(String.valueOf(mainObjectArray.getJSONObject(KeyTags.imgKey)
                                .getString(KeyTags.heightKey))));
                data.add(encap);



            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ArrayList<DataEncap> getlist() {
        return data;
    }
}
