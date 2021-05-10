package com.example.warcards;

import android.app.Application;
import android.content.Context;
import android.content.res.TypedArray;
import android.location.Location;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.warcards.objects.SharedPrefs;

public class App extends Application {

    private static Context context;

    private static TypedArray profilePics;

    private static String date;

    private static Location myLocation;

    // ================================================================

    @Override
    public void onCreate() {
        super.onCreate();

        refreshDate();

        context = getApplicationContext();
        profilePics = getApplicationContext().getResources().obtainTypedArray(R.array.playerImages);

        myLocation = new Location("G");

        SharedPrefs.initPrefs(this);
    }

    // ================================================================

    void refreshDate(){ date = DateFormat.format(" dd.MM.yy - HH:mm ", System.currentTimeMillis()).toString(); }

    public static void toast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    public static void updateLocation(Location location) {
        if (location != null) {
            myLocation.setLatitude(location.getLatitude());
            myLocation.setLongitude(location.getLongitude());
        }
    }

    // ================================================================

    public static Context getAppContext() { return context; }

    public static Location getLocation() { return myLocation; }

    public static TypedArray getProfilePics() {
        return profilePics;
    }

    public static String getDate() { return date; }

}
