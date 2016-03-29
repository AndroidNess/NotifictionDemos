package tomerbu.itworks.edu.notifictiondemos;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


public class MyNotificationIntentService extends IntentService {


    public MyNotificationIntentService() {
        super("MyNotificationIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Hello from AlarmClock")
                .setContentText("This is the content")
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManagerCompat.from(this).notify(1, builder.build());
    }

}
