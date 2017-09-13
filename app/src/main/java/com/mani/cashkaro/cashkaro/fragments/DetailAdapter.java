package com.mani.cashkaro.cashkaro.fragments;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mani.cashkaro.cashkaro.R;
import com.mani.cashkaro.cashkaro.Splashscreen;

import java.util.ArrayList;

/**
 * Created by SelvaGanesh on 10-04-2017.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyviewHolder> {

    ArrayList<Homemodel> arrayList;
    Context mcontext;


    public DetailAdapter(Context activity, ArrayList<Homemodel> arrayList) {

        this.mcontext = activity;
        this.arrayList = arrayList;

    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adap, parent, false);

        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyviewHolder holder, final int position) {
        try {
            final Homemodel histPos = arrayList.get(position);
            holder.tvMovName.setText(histPos.getDesc().toUpperCase());
            holder.tvGenre.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {

                    int mNotificationId = 001;

                    // Build Notification , setOngoing keeps the notification always in status bar
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(mcontext)
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentTitle("CashKaro")
                                    .setContentText("Congratulations you have clicked on "+histPos.getDesc())
                                    .setOngoing(true);

                    // Create pending intent, mention the Activity which needs to be
                    //triggered when user clicks on notification(StopScript.class in this case)

                    PendingIntent contentIntent = PendingIntent.getActivity(mcontext, 0,
                            new Intent(mcontext, Splashscreen.class), PendingIntent.FLAG_UPDATE_CURRENT);

                   mBuilder.setAutoCancel(true);
                    mBuilder.setContentIntent(contentIntent);


                    // Gets an instance of the NotificationManager service
                    NotificationManager mNotifyMgr =
                            (NotificationManager)mcontext. getSystemService(Context.NOTIFICATION_SERVICE);

                    // Builds the notification and issues it.
                    mNotifyMgr.notify(mNotificationId, mBuilder.build());

                    Intent main=new Intent(mcontext, WebviewActivity.class);
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

            tvMovName = (TextView) itemView.findViewById(R.id.tvPro);
            tvGenre = (TextView) itemView.findViewById(R.id.tvact);


        }
    }


}
