package com.mani.cashkaro.cashkaro.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mani.cashkaro.cashkaro.R;
import com.mani.cashkaro.cashkaro.Splashscreen;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by SelvaGanesh on 14-03-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    NotificationManager notificationManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        Log.e(TAG, "From: " + remoteMessage.getData().get("title"));

        String tittle_get = remoteMessage.getData().get("title");
        String message_get = remoteMessage.getData().get("message");
        String image_get = remoteMessage.getData().get("image");

        shownotification(tittle_get, message_get, image_get);
        Log.v("PushNotification", "tittle_get " + tittle_get + "message_get " + message_get + "image_get " + image_get);
    }

    private void shownotification(String tittle_get, String message_get, String image_get) {

        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;

        Intent intent = new Intent(this, Splashscreen.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long[] pattern = {500, 500, 500, 500, 500};

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        if (image_get != null && !image_get.equalsIgnoreCase("")) {

            Bitmap bitmap = getBitmapFromURL(image_get);

            try {


                NotificationCompat.BigPictureStyle notiStyle = new NotificationCompat.BigPictureStyle();
                // notiStyle.setSummaryText(intent.getExtras().getString("message"));

                notiStyle.bigPicture(bitmap);
                notificationManager = (NotificationManager) this
                        .getSystemService(Context.NOTIFICATION_SERVICE);
                PendingIntent contentIntent = null;

                Intent gotoIntent = new Intent();
                gotoIntent.setClassName(this, "com.cinescape.ui.Homescreen");
                contentIntent = PendingIntent.getActivity(this,
                        (int) (Math.random() * 100), gotoIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                        this);
                Notification notification;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher).setWhen(0)
                            .setAutoCancel(true)
                            .setContentTitle(tittle_get)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(message_get))
                            .setColor(Color.WHITE)
                            .setContentIntent(contentIntent)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

                            .setContentText(message_get)
                            .setStyle(notiStyle).build();
                } else {
                    notification = mBuilder.setSmallIcon(R.mipmap.ic_launcher).setWhen(0)
                            .setAutoCancel(true)
                            .setContentTitle(tittle_get)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(message_get))
                            .setContentIntent(contentIntent)
                            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

                            .setContentText(message_get)
                            .setStyle(notiStyle).build();
                }

                notification.flags = Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(m, notiStyle.build());

            } catch (Throwable e) {
                e.printStackTrace();
            }

        } else {
            NotificationCompat.Builder notificationBuilder2 = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(tittle_get)
                    .setContentText(message_get)
                    .setAutoCancel(true)
                    .setVibrate(pattern)
                    .setLights(Color.BLUE, 1, 1)
                    .setSound(defaultSoundUri)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(message_get))
                    .setContentIntent(pendingIntent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notificationBuilder2.setSmallIcon(R.mipmap.ic_launcher);
                notificationBuilder2.setColor(Color.WHITE);
            } else {
                notificationBuilder2.setSmallIcon(R.mipmap.ic_launcher);
            }


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(m, notificationBuilder2.build());
        }


    }

    public Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}