package com.example.user.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.CallbackInterface {

    private final static String Result = "Result";
    private int mTagCount = 0;
    public MainFragment.GameResultType mGameResultType;
    public MainFragment mainFragment;
    public Fragment fragResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragMain);
    }

    @Override
    public void updateGameResult(int iCountSet, int iCountPlayerWin, int iCountComWin, int iCountDraw) {
        if (findViewById(R.id.frameLay).isShown()) {
            switch (mGameResultType) {
                case TYPE_1:
                    ((GameResultFragment) fragResult).updateGameResult(iCountSet, iCountPlayerWin,
                            iCountComWin, iCountDraw);
                    break;
                case TYPE_2:
                    ((GameResult2Fragment) fragResult).updateGameResult(iCountSet, iCountPlayerWin,
                            iCountComWin, iCountDraw);
                    break;
            }
        }

    }

    public void InvokeUpdateResult() {
        mainFragment.UpdateResult();
    }

    @Override
    public void enableGameResult(MainFragment.GameResultType type) {
        FragmentTransaction fragTran;
        String sFragTag;

        switch (type) {
            case TYPE_1:
                GameResultFragment frag = new GameResultFragment();
                fragTran = getSupportFragmentManager().beginTransaction();
                mTagCount++;
                sFragTag = Result + mTagCount;
                fragTran.replace(R.id.frameLay, frag, sFragTag);
                fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();
                break;
            case TYPE_2:
                GameResult2Fragment frag2 = new GameResult2Fragment();
                fragTran = getSupportFragmentManager().beginTransaction();
                mTagCount++;
                sFragTag = Result + mTagCount;
                fragTran.replace(R.id.frameLay, frag2, sFragTag);
                fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragTran.addToBackStack(null);
                fragTran.commit();
                break;
            case HIDE:
                FragmentManager fragMgr = getSupportFragmentManager();
                sFragTag = Result + mTagCount;
                Fragment fragGameResult = fragMgr.findFragmentByTag(sFragTag);
                if (fragGameResult != null) {
                    fragTran = fragMgr.beginTransaction();
                    fragTran.remove(fragGameResult);
                    fragTran.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragTran.addToBackStack(null);
                    fragTran.commit();
                }
                break;
        }

    }
}