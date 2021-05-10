package com.example.warcards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.warcards.R;
import com.example.warcards.objects.SharedPrefs;
import com.example.warcards.objects.WinnersListAdapter;

public class list_fragment extends Fragment {

    private View view;

    private RecyclerView winners_list_layout;

    // ================================================================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_fragment, container, false);

        findViews();

        winners_list_layout.setLayoutManager(new LinearLayoutManager(this.getContext()));

        updateWinnersListView();

        return view;
    }

    // ================================================================

    public void updateWinnersListView(){
        winners_list_layout.setAdapter(new WinnersListAdapter(SharedPrefs.getWinnersList()));
    }

    void findViews(){
        winners_list_layout = view.findViewById(R.id.topScores_recyclerView);
    }

}