package com.alineselle.moodtracker3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

public class MoodHistory extends AppCompatActivity {

    private TextView mDisplayComment;
    private String mStoreComment;
    private ImageView mDisplayColorMoodChosen;

  private int mStoreLastColorChose;
    private int mStoreColorMoodIndex0;
    private int mStoreColorMoodIndex1;
    private int mStoreColorMoodIndex2;
    private int mStoreColorMoodIndex3;
    private int mStoreColorMoodIndex4;


    private SharedPreferences mHistoryResultPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);


        mHistoryResultPreferences = getSharedPreferences(MainActivity.PREF_KEY_HISTORY_MOOD, MODE_PRIVATE);
        mDisplayColorMoodChosen = findViewById(R.id.chosenMoodColor1);

        mStoreLastColorChose = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_LAST_COLOR, 0);
        /*mStoreColorMoodIndex0 = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_FADED_RED, 0);
        mStoreColorMoodIndex0 = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_WARM_GREY, 0);
        mStoreColorMoodIndex0 = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_CORNFLOWER_BLUE, 0);
        mStoreColorMoodIndex0 = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_LIGHT_SAGE, 0);
        mStoreColorMoodIndex0 = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_BANANA_YELLOW, 0);

        *//*mDisplayColorMoodChosen.setImageResource(mStoreColorMoodIndex1);
        mDisplayColorMoodChosen.setImageResource(mStoreColorMoodIndex2);
        mDisplayColorMoodChosen.setImageResource(mStoreColorMoodIndex4);
        mDisplayColorMoodChosen.setImageResource(mStoreColorMoodIndex3);*/

        mStoreComment = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT, null);
        mDisplayComment = findViewById(R.id.commentText);
        mDisplayComment.setText(mStoreComment);

       // mDisplayColorMoodChosen.setImageResource(mStoreColorMoodIndex0);

        mDisplayColorMoodChosen.setImageResource(mStoreLastColorChose);

    }
}
