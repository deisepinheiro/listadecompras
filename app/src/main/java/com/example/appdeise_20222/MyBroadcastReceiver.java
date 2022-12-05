package com.example.appdeise_20222;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "987654";
    private final static String default_notification_channel_id = "default" ;

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context);
    }

    private void createNotificationChannel(Context context) {

        Intent intent = new Intent(context, LoginActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);
        PendingIntent pending = PendingIntent.getActivity(
                context,1, intent, PendingIntent.FLAG_IMMUTABLE
        );
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,
                default_notification_channel_id )
                .setSmallIcon(R.drawable. ic_launcher_foreground )
                .setContentTitle( "Olá" )
                .setContentText( "Já fez a sua lista de compras hoje?" )
                .addAction(R.drawable.ic_launcher_foreground, context.getString(R.string.app_name),
                        pending);;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE ) ;
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH ;
            NotificationChannel notificationChannel = new
                    NotificationChannel( CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            notificationChannel.enableLights( true ) ;
            notificationChannel.setLightColor(Color.RED) ;
            notificationChannel.enableVibration( true ) ;
            mBuilder.setChannelId( CHANNEL_ID ) ;
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(notificationChannel) ;
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(( int ) System. currentTimeMillis (), mBuilder.build()) ;
    }

}
