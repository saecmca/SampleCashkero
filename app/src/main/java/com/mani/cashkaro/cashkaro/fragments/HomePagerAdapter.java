package com.mani.cashkaro.cashkaro.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mani.cashkaro.cashkaro.OfferDtls;
import com.mani.cashkaro.cashkaro.R;

import java.util.ArrayList;

/**
 * Created by SelvaGanesh on 10-04-2017.
 */

public class HomePagerAdapter extends RecyclerView.Adapter<HomePagerAdapter.MyviewHolder> {

    ArrayList<Homemodel> arrayList;
    Context mcontext;


    public HomePagerAdapter(Context activity, ArrayList<Homemodel> arrayList) {

        this.mcontext = activity;
        this.arrayList = arrayList;

    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.commingup_fragment, parent, false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, final int position) {
        try {
            final Homemodel histPos = arrayList.get(position);
            holder.tvMovName.setText(histPos.getDesc());
            holder.ivMovie.setImageResource(histPos.getImage());
            holder.tvCinema.setText("View all offers ("+histPos.getViewmore()+")");
            holder.tvGenre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent main=new Intent(mcontext, OfferDtls.class);
                    main.putExtra("prName",histPos.getName());
                    mcontext.startActivity(main);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {


        private ImageView ivMovie;
        private TextView tvMovName, tvGenre, tvCinema, tvType, tvTicketNo, tvLabTotal, tvTotal, tvLabPay;


        public MyviewHolder(View itemView) {
            super(itemView);
            ivMovie=(ImageView)itemView.findViewById(R.id.image);
            tvMovName = (TextView) itemView.findViewById(R.id.tvPro);
            tvGenre = (TextView) itemView.findViewById(R.id.tvact);
            tvCinema = (TextView) itemView.findViewById(R.id.tvmore);

        }
    }


}
