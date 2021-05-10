package com.example.warcards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.warcards.R;
import com.example.warcards.objects.SharedPrefs;

public class topScores_fragment extends Fragment {

    private View view;

    private map_fragment map = new map_fragment();
    private list_fragment list = new list_fragment();

    private Button delete_BTN;
    private EditText nameEditText;

    // ================================================================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top_scores, container, false);

        findViews();

        delete_BTN.setOnClickListener(v -> {
            if(SharedPrefs.getInstance().removeWinner(nameEditText.getText().toString())){
                list.updateWinnersListView(); // if winner removed from list
            } else {
                nameEditText.setError("Name Not Found");
                nameEditText.requestFocus();
            }
        });

        putFragmentInView(R.id.map_right, map);
        putFragmentInView(R.id.list_left, list);

        return view;
    }

    // ================================================================

    void findViews() {
        nameEditText = view.findViewById(R.id.topScores_Edit_name);
        delete_BTN = view.findViewById(R.id.topScores_BTN_delete);
    }

    public void putFragmentInView(int layout_id, Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(layout_id, fragment).commit();
    }

}