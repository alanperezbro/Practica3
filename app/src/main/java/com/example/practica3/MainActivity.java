package com.example.practica3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
NotificationManager notificationManager;
static final String CANAL_ID="segundo canal";
static final int NOTIFICATION_ID =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                    CANAL_ID, "Mis Notificaciones",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Descripcion del canal");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0,100,300,100});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);

        }
        Button boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {

            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:6141903907"));
            Intent intent3= new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0?q=universidad+tecnologica+de+chihuahua"));
            Intent intent4= new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.nfl.com"));
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    MainActivity.this,0,intent, PendingIntent.FLAG_IMMUTABLE);
            PendingIntent pendingIntent2 = PendingIntent.getActivity(
                    MainActivity.this,0,intent2, PendingIntent.FLAG_IMMUTABLE);
            PendingIntent pendingIntent3 = PendingIntent.getActivity(
                    MainActivity.this,0,intent3, PendingIntent.FLAG_IMMUTABLE);
            PendingIntent pendingIntent4 = PendingIntent.getActivity(
                    MainActivity.this,0,intent4, PendingIntent.FLAG_IMMUTABLE);

            @Override
            public void onClick(View view) {
                NotificationCompat.Builder noti =
                        new NotificationCompat.Builder(MainActivity.this,CANAL_ID)
                .setContentTitle("Notificación auuuu")
                                .setContentText("pura buena vibra pa")
                                .addAction(R.drawable.ic_launcher_background,"Teléfono", pendingIntent2)
                                .addAction(R.drawable.ic_launcher_background,"Mapa", pendingIntent3)
                                .addAction(R.drawable.ic_launcher_background,"Página", pendingIntent4)
                                .setSmallIcon(R.drawable.notification);


                notificationManager.notify(NOTIFICATION_ID,noti.build());
            }
        });
    }
}