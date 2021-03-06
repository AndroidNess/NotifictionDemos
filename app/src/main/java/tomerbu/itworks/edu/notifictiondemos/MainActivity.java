package tomerbu.itworks.edu.notifictiondemos;


import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int EMAIL_NOTIFICATION = 1;
    private static final int REQUEST_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPermissions();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =
                        new Intent(getApplicationContext(), MyNotificationIntentService.class );


                PendingIntent pIntent = PendingIntent.getService(getApplicationContext(),
                        1,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, 60*1000, 60*1000, pIntent);



            }
        });
    }

    private void displayBigPictureNotification() {
        Intent mainActivityIntent =
                new Intent(getApplicationContext(), MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(
                getApplicationContext(),
                1, mainActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        //init the builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                MainActivity.this);

        Bitmap bitmap = BitmapFactory.decodeResource(
                getResources(), R.drawable.obama
        );

        //compose the notification
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("i Love Israel!")
                .setContentText("Yay...")
                .setAutoCancel(true)
                .setStyle(
                        new NotificationCompat.BigPictureStyle().bigPicture(bitmap)
                )
                .addAction(R.mipmap.ic_launcher, "true", pIntent)
                .addAction(R.mipmap.ic_launcher, "false", pIntent)
                .setContentIntent(pIntent);

        //Notify using the NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(EMAIL_NOTIFICATION, builder.build());
    }

    void dispNotificationWithActions(){
        Intent mainActivityIntent =
                new Intent(getApplicationContext(), MainActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(
                getApplicationContext(),
                1, mainActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        //init the builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                MainActivity.this);

        //compose the notification
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("This is the title")
                .setContentText("This is the message")
                .setAutoCancel(true)
                .addAction(R.mipmap.ic_launcher, "OK", pIntent)
                .addAction(R.mipmap.ic_launcher, "NoNo", pIntent)
                .setContentIntent(pIntent);

        //Notify using the NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(EMAIL_NOTIFICATION, builder.build());
    }

    void dispNotification(){
        //init the builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);

        //compose the notification
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("This is the title")
                .setContentText("This is the message");

        //Notify using the NotificationManager
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
        notificationManager.notify(EMAIL_NOTIFICATION, builder.build());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getPermissions() {
        String[] permissions = {
                Manifest.permission.PROCESS_OUTGOING_CALLS,
                Manifest.permission.READ_PHONE_STATE
        };
        int result = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.PROCESS_OUTGOING_CALLS);


        if (result != PackageManager.PERMISSION_GRANTED){
            //requestPermissions(permissions, REQUEST_PHONE);
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PHONE);
        }
    }
}
