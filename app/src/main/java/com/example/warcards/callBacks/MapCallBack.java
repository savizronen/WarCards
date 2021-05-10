package com.example.warcards.callBacks;

import com.example.warcards.objects.Winner;
import com.google.android.gms.maps.model.LatLng;

public interface MapCallBack {

    void displayLocationOnMap(Winner winner);

    void moveCamera(LatLng latLng);

    LatLng latLngCreator(Winner winner);

    void clearMap();
}
