package tomerbu.itworks.edu.notifictiondemos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Set;

public class CallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Set<String> keys = bundle.keySet();
        for (String key : keys) {
            System.out.println(key + ": " + bundle.get(key));
        }


        System.out.println("Call changed");
     }
}
