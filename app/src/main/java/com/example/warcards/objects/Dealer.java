package com.example.warcards.objects;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.example.warcards.R;
import com.example.warcards.callBacks.IMainActivity;
import com.example.warcards.fragments.player_fragment;
import java.util.ArrayList;

public class Dealer {

    private View view;

    IMainActivity iMainActivity;

    private ArrayList<Card> cardStack = new ArrayList<>();

    private ImageView left_cardImg;
    private ImageView right_cardImg;

    private ImageView playButton;

    private ProgressBar progressBar;

    public enum Side { LEFT , RIGHT }

    // ================================================================

    public Dealer(View view, int leftCard_view_id, int rightCard_view_id, int playBtn_view_id, int progBar_view_id) {
        this.view = view;

        iMainActivity = (IMainActivity) view.getContext();

        initCardStack(cardStack);

        this.left_cardImg = view.findViewById(leftCard_view_id);
        this.right_cardImg = view.findViewById(rightCard_view_id);

        this.playButton = view.findViewById(playBtn_view_id);
        this.progressBar = view.findViewById(progBar_view_id);
    }

    // ================================================================

    void initCardStack(ArrayList<Card> cardStack) {  // initializes the deck
        for (int i =  1 ; i <= 4 ; i++) { // gets type name from arrays.xml
            int typeId = view.getResources().obtainTypedArray(R.array.names).getResourceId(i, 0);
            String type = view.getResources().getResourceEntryName(typeId);
            for (int j = 2 ; j <= 14 ; j++) {
                cardStack.add(new Card(type, j));
            }
        }
    }

    // ================================================================

    public void dealCardsToPlayers(player_fragment leftPlayer, player_fragment rightPlayer){
        if(!cardStack.isEmpty()) {
            iMainActivity.playSound(R.raw.card_dealing);
            determineRoundWinner(leftPlayer,rightPlayer);
        } else {
            Bundle matchBundle = createWinnerBundle(leftPlayer,rightPlayer);
            iMainActivity.inflateFragment("winner_fragment", true, matchBundle);
            progressBar.setProgress(0);
        }
    }

    // determines which player gets a point
    void determineRoundWinner(player_fragment leftPlayer, player_fragment rightPlayer) {
        int leftCardVal = leftPlayer.getCard_fromDealer(this);
        int rightCardVal = rightPlayer.getCard_fromDealer(this);

        if (leftCardVal > rightCardVal)
            leftPlayer.incrementScore();
        else if (leftCardVal < rightCardVal)
            rightPlayer.incrementScore();

        progressBar.incrementProgressBy(1);
    }

    // ================================================================

    public void resetGameProgress(player_fragment leftPlayer, player_fragment rightPlayer) {
        initCardStack(cardStack);
        leftPlayer.resetGameScore();
        rightPlayer.resetGameScore();
        progressBar.setProgress(0);
    }

    // ================================================================

    // creates data for WinnerFragment
    Bundle createWinnerBundle(player_fragment leftPlayer, player_fragment rightPlayer) {
        Bundle bundle = new Bundle();

        if(leftPlayer.getGameScore() > rightPlayer.getGameScore())
            addDataToBundle(leftPlayer, bundle);
        else if(leftPlayer.getGameScore() < rightPlayer.getGameScore())
            addDataToBundle(rightPlayer, bundle);
        else
            bundle.putString(SharedPrefs.KEYS.WINNER, "It's A Tie!");

        return bundle;
    }

    void addDataToBundle(player_fragment player, Bundle bundle){
        bundle.putString(SharedPrefs.KEYS.PLAYER_NAME,player.getPlayerName());
        bundle.putInt(SharedPrefs.KEYS.PLAYER_SCORE,player.getGameScore());
        bundle.putInt(SharedPrefs.KEYS.PLAYER_IMG_INDEX, player.getImgIndex());
    }

    // ================================================================

    public ImageView getCardView_bySide(Side side) {
        if(side.equals(Side.LEFT))
            return left_cardImg;
        if(side.equals(Side.RIGHT))
            return right_cardImg;
        return null;
    }

    public ArrayList<Card> getCardStack() { return cardStack; }

    public ImageView getPlayButton() { return playButton; }

}
