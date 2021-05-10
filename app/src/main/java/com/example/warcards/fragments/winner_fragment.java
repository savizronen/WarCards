package com.example.warcards.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.warcards.App;
import com.example.warcards.R;
import com.example.warcards.objects.SharedPrefs;
import com.example.warcards.objects.Winner;
import com.example.warcards.services.LocationMonitoringService;

public class winner_fragment extends Fragment{

    private View view;

    private String winnerMsg;

    private TextView winner_score;
    private ImageView winner_img;

    private TextView winner_LBL_msg;

    // ================================================================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_winner, container, false);

        winnerMsg = getArguments().getString(SharedPrefs.KEYS.WINNER);

        getActivity().stopService(new Intent(getContext(), LocationMonitoringService.class));
        findViews();
        addWinnerToList();

        return view;
    }

    // ================================================================

    void findViews() {
        winner_img = view.findViewById(R.id.winner_img);
        winner_score = view.findViewById(R.id.winner_score);
        winner_LBL_msg = view.findViewById(R.id.winner_LBL_msg);
    }

    // ================================================================

    void addWinnerToList() {
        if (winnerMsg != "It's A Tie!") { // if winner exists
            Winner winner = getWinnerFromBundle();
            setWinnerToView(winner);
            SharedPrefs.getInstance().addWinner(winner);
        } else
            winner_LBL_msg.setText(winnerMsg);
    }

    private Winner getWinnerFromBundle() {
        String name = getArguments().getString(SharedPrefs.KEYS.PLAYER_NAME);
        int score = getArguments().getInt(SharedPrefs.KEYS.PLAYER_SCORE);
        int imgIndex = getArguments().getInt(SharedPrefs.KEYS.PLAYER_IMG_INDEX);

        return new Winner(name, score, imgIndex, App.getLocation(), App.getDate());
    }

    private void setWinnerToView(Winner winner) {
        winner_img.setImageResource(App.getProfilePics().getResourceId(winner.getImgIndex(), -1));
        winner_score.setText("" + winner.getScore());
        winner_LBL_msg.setText(winner.getName() + " Won!");
    }

// =============================================================

}