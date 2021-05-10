package com.example.warcards.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.example.warcards.App;
import com.example.warcards.R;
import com.example.warcards.objects.Card;
import com.example.warcards.objects.Dealer;

import java.util.ArrayList;
import java.util.Random;

public class player_fragment extends Fragment {

    private View view;

    private Dealer.Side side;

    private ImageView img;
    private TextView score;
    private EditText name;

    private Random rand;
    private Card currCard;
    private int gameScore;

    private int playerImgArrIndex;
    private boolean gameRunning;

    //====================================================

    public player_fragment(View view, Dealer.Side side) {
        this.view = view;
        this.side = side;
        this.rand = new Random();
        this.currCard = new Card();
        this.gameScore = 0;
        this.gameRunning = false;
    }

    //=============================================================================================================

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_player, container, false);

        findViews();

        this.playerImgArrIndex = rand.nextInt(App.getProfilePics().length() - 1);

        setImgChangeListener();
        changePlayerImg();

        return view;
    }

    //=============================================================================================================

    void findViews(){
        img = view.findViewById(R.id.fragment_player_img);
        score = view.findViewById(R.id.fragment_player_score);
        name = view.findViewById(R.id.fragment_player_editText);
    }

    //=============================================================================================================

    // change player img listener
    private void setImgChangeListener( ){
        img.setOnClickListener(v -> {
            if (!gameRunning)
                changePlayerImg();
        });
    }

    void changePlayerImg(){
        img.setImageResource(App.getProfilePics().getResourceId(playerImgArrIndex,-1));

        if(playerImgArrIndex < App.getProfilePics().length()-1)
            playerImgArrIndex++;
        else
            playerImgArrIndex = 0;
    }

    //=============================================================================================================

    public int getCard_fromDealer(Dealer dealer) {
        ArrayList<Card> cardStack = dealer.getCardStack();
        Card randCard = cardStack.get(rand.nextInt(cardStack.size()));
        cardStack.remove(randCard);

        // sets new card in imageView
        int img_id = view.getResources().getIdentifier("card_"+ randCard.getImageName(), "drawable", view.getContext().getPackageName());
        Glide.with(this).load(img_id).transition(GenericTransitionOptions.with(R.anim.dealer_deal)).into(dealer.getCardView_bySide(side));
        currCard = randCard;

        return currCard.getValue();
    }

    //======================================================

    // locks changes to img and name
    private void lockEditText() {
        boolean state = !gameRunning;
        name.setFocusable(state);
        name.setEnabled(state);
        name.setCursorVisible(state);
        name.setFocusableInTouchMode(state);
    }

    // locks changes to img and name
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
        lockEditText();
    }

    //======================================================

    public void resetGameScore() { this.gameScore = 0; }

    public void incrementScore() { this.score.setText("" + ++gameScore); }

    //======================================================

    public String getPlayerName() { return name.getText().toString(); }

    public int getGameScore() { return gameScore; }

    public boolean isGameRunning() { return gameRunning; }

    public int getImgIndex() { return playerImgArrIndex-1; }
}
