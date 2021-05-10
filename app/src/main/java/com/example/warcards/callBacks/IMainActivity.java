package com.example.warcards.callBacks;

import android.os.Bundle;

public interface IMainActivity {

    void hideSystemUI();

    void playSound(int rawSound);

    void inflateFragment(String fragmentTag, boolean addToBackStack, Bundle bundle);

}
