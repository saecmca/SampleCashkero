package com.mani.cashkaro.cashkaro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mani.cashkaro.cashkaro.fragments.DetailAdapter;
import com.mani.cashkaro.cashkaro.fragments.Homemodel;

import java.util.ArrayList;

public class OfferDtls extends AppCompatActivity  {
     RecyclerView rclv;
    Homemodel homemodel;
    ArrayList<Homemodel>arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_dtls);
        rclv=(RecyclerView)findViewById(R.id.rcl);
        rclv.setLayoutManager(new LinearLayoutManager(this));
        Intent intent=getIntent();
        String strTit=intent.getStringExtra("prName");
        ((TextView)findViewById(R.id.tvTit)).setText(strTit);


        homemodel=new Homemodel();
        homemodel.setDesc("Smart Recharge Deals");
        homemodel.setName(strTit);
        arrayList.add(homemodel);
        homemodel=new Homemodel();
        homemodel.setDesc("Men fashion Deals");
        homemodel.setName(strTit);
        arrayList.add(homemodel);
        homemodel=new Homemodel();
        homemodel.setDesc("Women fashion Deals");
        homemodel.setName(strTit);
        arrayList.add(homemodel);
        homemodel=new Homemodel();
        homemodel.setDesc("Hot mobile Deals");
        homemodel.setName(strTit);
        arrayList.add(homemodel);
        homemodel=new Homemodel();
        homemodel.setDesc("Book Deals");
        homemodel.setName(strTit);
        arrayList.add(homemodel);

        DetailAdapter adapter = new DetailAdapter(OfferDtls.this,arrayList);
        rclv.setAdapter(adapter);

        ((ImageView)findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
