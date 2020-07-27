package com.way2news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;

public class MainActivity extends AppCompatActivity {

    private String DeviceName = "";
    private static final int IGNORE_BATTERY_OPTIMIZATION_REQUEST = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeviceName = getDeviceName();


        if(DeviceName.equalsIgnoreCase("Xiaomi") || DeviceName.equalsIgnoreCase("OPPO") || DeviceName.equalsIgnoreCase("Vivo") || DeviceName.equalsIgnoreCase("Oneplus") || DeviceName.equalsIgnoreCase("Honour") || DeviceName.equalsIgnoreCase("Letv"))
        {
            turnOnOffDozeMode(MainActivity.this);
        }


    }

    public static String  getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        return manufacturer;
    }


    public void turnOnOffDozeMode(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent();
            String packageName = context.getPackageName();
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (pm.isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
            }
            else {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
            }

            context.startActivity(intent);
        }
    }

}
