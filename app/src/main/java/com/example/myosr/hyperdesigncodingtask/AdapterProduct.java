package com.example.myosr.hyperdesigncodingtask;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myosr.hyperdesigncodingtask.dataProccess.DataEncap;
import com.squareup.picasso.Picasso;


import org.json.JSONException;

import java.util.ArrayList;


public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> implements View.OnClickListener {

    ArrayList<DataEncap> arrayList;
    Context context;
    MainActivity mainActivity;

    DataEncap encap = new DataEncap();

    public AdapterProduct(ArrayList<DataEncap> arrayList, Context context, MainActivity mainActivity) {
        this.arrayList = arrayList;
        this.context = context;
        this.mainActivity = mainActivity;
    }

    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);

        ViewHolder viewHolder = new ViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterProduct.ViewHolder holder, int position) {
        encap = arrayList.get(position);

        holder.cardView.setTag(position);

        holder.textTitle.setText(encap.getPrice()+"$");
        holder.textDesc.setText(encap.getDesc());

        Picasso.with(context).load(encap.getImg())
                .resize(150, encap.getHeight()).into(holder.imageNews);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onClick(View v) {

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView cardView;
        ImageView imageNews;
        TextView textTitle;
        TextView textDesc;

        public ViewHolder(View layout) {
            super(layout);

            cardView = (CardView) layout.findViewById(R.id.list_row_container);
            imageNews = (ImageView) layout.findViewById(R.id.item_image);
            textTitle = (TextView) layout.findViewById(R.id.title_price);
            textDesc = (TextView) layout.findViewById(R.id.desc);
            cardView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int position = (int) view.getTag();

            encap = arrayList.get(position);

            Intent intent = new Intent(mainActivity, DetailsActivity.class);

            intent.putExtra("image", encap.getImg());
            intent.putExtra("id", encap.getId());


            mainActivity.startActivity(intent);

        }

    }

}
