package com.alineselle.moodtracker3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MoodHistory extends AppCompatActivity {

    private TextView mDisplayComment;
    private String mStoreComment;
    private ImageView mDisplayColorMoodChosen;

  private int mStoreLastColorChosen;



    private SharedPreferences mHistoryResultPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);


        mHistoryResultPreferences = getSharedPreferences(MainActivity.PREF_KEY_HISTORY_MOOD, MODE_PRIVATE);
        mDisplayColorMoodChosen = findViewById(R.id.chosenMoodColor1);

        mStoreLastColorChosen = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_LAST_COLOR, 0);


        mStoreComment = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT, null);
        mDisplayComment = findViewById(R.id.commentText);
        mDisplayComment.setText(mStoreComment);



        mDisplayColorMoodChosen.setImageResource(mStoreLastColorChosen);

    }
}
