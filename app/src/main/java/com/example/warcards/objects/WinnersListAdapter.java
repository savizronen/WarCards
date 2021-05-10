package com.example.warcards.objects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.warcards.App;
import com.example.warcards.R;
import com.example.warcards.fragments.map_fragment;
import java.util.LinkedList;

public class WinnersListAdapter extends RecyclerView.Adapter<WinnersListAdapter.WinnersListViewHolder> {

    View view;

    private LinkedList<Winner> winnersList;

    // ================================================================

    public WinnersListAdapter(LinkedList<Winner> winnersList){
        this.winnersList = winnersList;
    }

    @NonNull
    @Override
    public WinnersListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.winners_list_item, parent, false);

        return new WinnersListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WinnersListViewHolder holder, int position) {
        Winner winner = winnersList.get(position);
        holder.winner_position.setText("" + (position+1));
        holder.winner_img.setImageResource(App.getProfilePics().getResourceId(winner.getImgIndex(),-1));
        holder.winner_name.setText("Name - " + winner.getName());
        holder.winner_score.setText("Score - " + winner.getScore());
        holder.winner_date.setText(winner.getDate());
        holder.itemView.setOnClickListener(v -> {
            map_fragment.getMapCallBack().clearMap();
            map_fragment.getMapCallBack().displayLocationOnMap(winner);
        });
    }

    @Override
    public int getItemCount() {
        return winnersList.size();
    }

    // ================================================================
    // viewHolder for each item in list

    public class WinnersListViewHolder extends RecyclerView.ViewHolder {
        TextView winner_position;
        ImageView winner_img;
        TextView winner_name;
        TextView winner_score;
        TextView winner_date;

        public WinnersListViewHolder(View itemView){
            super(itemView);
            winner_position = itemView.findViewById(R.id.winnerList_item_position);
            winner_img = itemView.findViewById(R.id.winnerList_item_img);
            winner_name = itemView.findViewById(R.id.winnerList_item_name);
            winner_score = itemView.findViewById(R.id.winnerList_item_score);
            winner_date = itemView.findViewById(R.id.winnerList_item_date);
        }
    }

    // ================================================================
}
