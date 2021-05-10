package com.example.warcards.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;
import androidx.fragment.app.Fragment;
import com.example.warcards.R;
import com.example.warcards.callBacks.IMainActivity;
import com.example.warcards.objects.SharedPrefs;

public class selector_fragment extends Fragment implements View.OnClickListener {

    private View view;

    private Button startGame, topScores;

    private IMainActivity iMainActivity;

    private ToggleButton timerToggle;

    // ================================================================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_selector, container, false);

        findViews();
        setListeners();

        return view;
    }

    void findViews(){
        startGame = view.findViewById(R.id.game_button);
        topScores = view.findViewById(R.id.topScores_button);
        timerToggle = view.findViewById(R.id.selector_timer_toggle);
    }

    void setListeners(){
        startGame.setOnClickListener(this);
        topScores.setOnClickListener(this);
        timerToggle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.game_button:
                iMainActivity.inflateFragment(getString(R.string.GameFragment),true, null);
                break;
            case R.id.topScores_button:
                iMainActivity.inflateFragment(getString(R.string.TopScoresFragment), true, null);
                break;
            case R.id.selector_timer_toggle:
                SharedPrefs.getInstance().invertTimerMode();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) getActivity();
    }
}