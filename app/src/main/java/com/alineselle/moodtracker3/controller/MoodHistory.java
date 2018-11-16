package com.alineselle.moodtracker3.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alineselle.moodtracker3.R;

public class MoodHistory extends AppCompatActivity {

   Button mPieChartBtn;

    // private TextView mDisplayComment;
    private String mStoreCommentAWeekAgo;
    private String mStoreComment6DaysAgo;
    private String mStoreComment5DaysAgo;
    private String mStoreComment4DaysAgo;
    private String mStoreComment3DaysAgo;
    private String mStoreComment2DaysAgo;
    private String mStoreCommentYesterday;


    private ImageView mDisplayColorAWeekAgo;
    private ImageView mDisplayColor6DaysAgo;
    private ImageView mDisplayColor5DaysAgo;
    private ImageView mDisplayColor4DaysAgo;
    private ImageView mDisplayColor3DaysAgo;
    private ImageView mDisplayColor2DaysAgo;
    private ImageView mDisplayColorYesterday;

    private TextView mDisplayTextAWeekAgo;
    private TextView mDisplayText6DaysAgo;
    private TextView mDisplayText5DaysAgo;
    private TextView mDisplayText4DaysAgo;
    private TextView mDisplayText3DaysAgo;
    private TextView mDisplayText2DaysAgo;
    private TextView mDisplayTextYesterday;


    private int mStoreColorAWeekAgo;
    private int mStoreColor6DaysAgo;
    private int mStoreColor5DaysAgo;
    private int mStoreColor4DaysAgo;
    private int mStoreColor3DaysAgo;
    private int mStoreColor2DaysAgo;
    private int mStoreColorYesterday;

    private int mStoreLevelAWeekAgo;
    private int mStoreLeveld6DaysAgo;
    private int mStoreLevel5DaysAgo;
    private int mStoreLevel4DaysAgo;
    private int mStoreLevel3DaysAgo;
    private int mStoreLevel2DaysAgo;
    private int mStoreLevelYesterday;
    
    
    private Button mCommentBtnAWeekAgo;
    private Button mCommentBtn6DaysAgo;
    private Button mCommentBtn5DaysAgo;
    private Button mCommentBtn4DaysAgo;
    private Button mCommentBtn3DaysAgo;
    private Button mCommentBtn2DaysAgo;
    private Button mCommentBtnYesterday;

    private Button mClearButton;




    private SharedPreferences mHistoryResultPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);
        mPieChartBtn = findViewById(R.id.pieChartButton);

        mClearButton = findViewById(R.id.buttonClear);

        mCommentBtnAWeekAgo = findViewById(R.id.CommentBtnAWeekAgo);
        mCommentBtn6DaysAgo = findViewById(R.id.Comment_btn6DaysAgo);
        mCommentBtn5DaysAgo = findViewById(R.id.Comment_btn5DaysAgo);
        mCommentBtn4DaysAgo = findViewById(R.id.Comment_btn4DaysAgo);
        mCommentBtn3DaysAgo = findViewById(R.id.Comment_btn3DaysAgo);
        mCommentBtn2DaysAgo = findViewById(R.id.Comment_btn2DaysAgo);
        mCommentBtnYesterday = findViewById(R.id.Comment_btnYesterday);

        mCommentBtnAWeekAgo.setVisibility(View.GONE);
        mCommentBtn6DaysAgo.setVisibility(View.GONE);
        mCommentBtn5DaysAgo.setVisibility(View.GONE);
        mCommentBtn4DaysAgo.setVisibility(View.GONE);
        mCommentBtn3DaysAgo.setVisibility(View.GONE);
        mCommentBtn2DaysAgo.setVisibility(View.GONE);
        mCommentBtnYesterday.setVisibility(View.GONE);


        mDisplayColorAWeekAgo = findViewById(R.id.chosenColorAWeekAgo);
        mDisplayColor6DaysAgo = findViewById(R.id.chosenColor6DaysAgo);
        mDisplayColor5DaysAgo = findViewById(R.id.chosenColor5DaysAgo);
        mDisplayColor4DaysAgo = findViewById(R.id.chosenColor4DaysAgo);
        mDisplayColor3DaysAgo = findViewById(R.id.chosenColor3DaysAgo);
        mDisplayColor2DaysAgo = findViewById(R.id.chosenColor2DaysAgo);
        mDisplayColorYesterday = findViewById(R.id.chosenColorYesterday);

        mDisplayTextYesterday = findViewById(R.id.textViewYesterday);
        mDisplayText2DaysAgo = findViewById(R.id.textView2DaysAgo);
        mDisplayText3DaysAgo = findViewById(R.id.textView3DaysAgo);
        mDisplayText4DaysAgo = findViewById(R.id.textView4DaysAgo);
        mDisplayText5DaysAgo = findViewById(R.id.textView5DaysAgo);
        mDisplayText6DaysAgo = findViewById(R.id.textView6DaysAgo);
        mDisplayTextAWeekAgo = findViewById(R.id.textViewAWeekAgo);

        mDisplayTextAWeekAgo.setVisibility(View.GONE);
        mDisplayText6DaysAgo.setVisibility(View.GONE);
        mDisplayText5DaysAgo.setVisibility(View.GONE);
        mDisplayText4DaysAgo.setVisibility(View.GONE);
        mDisplayText3DaysAgo.setVisibility(View.GONE);
        mDisplayText2DaysAgo.setVisibility(View.GONE);
        mDisplayTextYesterday.setVisibility(View.GONE);


        mHistoryResultPreferences = getSharedPreferences(MainActivity.PREF_KEY_HISTORY_MOOD, MODE_PRIVATE);


        mStoreLevelYesterday = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[0], -1);
        mStoreLevel2DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[1], -1);
        mStoreLevel3DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[2], -1);
        mStoreLevel4DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[3], -1);
        mStoreLevel5DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[4], -1);
        mStoreLeveld6DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[5], -1);
        mStoreLevelAWeekAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[6], -1);

        mStoreColorYesterday = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[0], 0);
        mStoreColor2DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[1], 0);
        mStoreColor3DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[2], 0);
        mStoreColor4DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[3], 0);
        mStoreColor5DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[4], 0);
        mStoreColor6DaysAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[5], 0);
        mStoreColorAWeekAgo = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[6], 0);

        mStoreCommentYesterday = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT[0],"");
        mStoreComment2DaysAgo = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT[1],"");
        mStoreComment3DaysAgo = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT[2],"");
        mStoreComment4DaysAgo = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT[3],"");
        mStoreComment5DaysAgo = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT[4],"");
        mStoreComment6DaysAgo = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT[5],"");
        mStoreCommentAWeekAgo = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT[6],"");


        Display display = getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size. x;
   //     int height = size. y;
    //    Log. e("Width", "" + width);
    //    Log. e("height", "" + height);

        ViewGroup.LayoutParams params;
        if (mStoreLevelAWeekAgo >= 0) {
            mDisplayTextAWeekAgo.setVisibility(View.VISIBLE);
            params = mDisplayColorAWeekAgo.getLayoutParams();
            params.width = width * (mStoreLevelAWeekAgo + 1) / 5;
            mDisplayColorAWeekAgo.setLayoutParams(params);
        }

        if (mStoreLevelYesterday >= 0) {
            mDisplayTextYesterday.setVisibility(View.VISIBLE);
            params = mDisplayColorYesterday.getLayoutParams();
            params.width = width * (mStoreLevelYesterday + 1) / 5;
            mDisplayColorYesterday.setLayoutParams(params);
        }

        if (mStoreLevel2DaysAgo >= 0) {
            mDisplayText2DaysAgo.setVisibility(View.VISIBLE);
            params = mDisplayColor2DaysAgo.getLayoutParams();
            params.width = width * (mStoreLevel2DaysAgo + 1) / 5;
            mDisplayColor2DaysAgo.setLayoutParams(params);
        }

        if (mStoreLevel3DaysAgo >= 0) {
            mDisplayText3DaysAgo.setVisibility(View.VISIBLE);
            params = mDisplayColor3DaysAgo.getLayoutParams();
            params.width = width * (mStoreLevel3DaysAgo + 1) / 5;
            mDisplayColor3DaysAgo.setLayoutParams(params);
        }

        if (mStoreLevel4DaysAgo >= 0) {
            mDisplayText4DaysAgo.setVisibility(View.VISIBLE);
            params = mDisplayColor4DaysAgo.getLayoutParams();
            params.width = width * (mStoreLevel4DaysAgo + 1) / 5;
            mDisplayColor4DaysAgo.setLayoutParams(params);
        }

        if (mStoreLevel5DaysAgo >= 0) {
            mDisplayText5DaysAgo.setVisibility(View.VISIBLE);
            params = mDisplayColor5DaysAgo.getLayoutParams();
            params.width = width * (mStoreLevel5DaysAgo + 1) / 5;
            mDisplayColor5DaysAgo.setLayoutParams(params);
        }

        if (mStoreLeveld6DaysAgo >= 0) {
            mDisplayText6DaysAgo.setVisibility(View.VISIBLE);
            params = mDisplayColor6DaysAgo.getLayoutParams();
            params.width = width * (mStoreLeveld6DaysAgo + 1) / 5;
            mDisplayColor6DaysAgo.setLayoutParams(params);
        }

        mDisplayColorAWeekAgo.setImageResource(mStoreColorAWeekAgo);
        mDisplayColorYesterday.setImageResource(mStoreColorYesterday);
        mDisplayColor2DaysAgo.setImageResource(mStoreColor2DaysAgo);
        mDisplayColor3DaysAgo.setImageResource(mStoreColor3DaysAgo);
        mDisplayColor4DaysAgo.setImageResource(mStoreColor4DaysAgo);
        mDisplayColor5DaysAgo.setImageResource(mStoreColor5DaysAgo);
        mDisplayColor6DaysAgo.setImageResource(mStoreColor6DaysAgo);



       /* mDisplayComment = findViewById(R.id.commentText);
        mDisplayComment.setText(mStoreCommentAWeekAgo);*/



        if (mStoreCommentAWeekAgo != "") {
            mCommentBtnAWeekAgo.setVisibility(View.VISIBLE);
            mCommentBtnAWeekAgo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mStoreCommentAWeekAgo, Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (mStoreCommentYesterday != "") {
            mCommentBtnYesterday.setVisibility(View.VISIBLE);
            mCommentBtnYesterday.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mStoreCommentYesterday, Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (mStoreComment2DaysAgo != "") {
            mCommentBtn2DaysAgo.setVisibility(View.VISIBLE);
            mCommentBtn2DaysAgo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mStoreComment2DaysAgo, Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (mStoreComment3DaysAgo != "") {
            mCommentBtn3DaysAgo.setVisibility(View.VISIBLE);
            mCommentBtn3DaysAgo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mStoreComment3DaysAgo, Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (mStoreComment4DaysAgo != "") {
            mCommentBtn4DaysAgo.setVisibility(View.VISIBLE);
            mCommentBtn4DaysAgo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mStoreComment4DaysAgo, Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (mStoreComment5DaysAgo != "") {
            mCommentBtn5DaysAgo.setVisibility(View.VISIBLE);
            mCommentBtn5DaysAgo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mStoreComment5DaysAgo, Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (mStoreComment6DaysAgo != "") {
            mCommentBtn6DaysAgo.setVisibility(View.VISIBLE);
            mCommentBtn6DaysAgo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), mStoreComment6DaysAgo, Toast.LENGTH_SHORT).show();
                }
            });
        }

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHistoryResultPreferences.edit().clear().apply();
            }
        });

        mPieChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mPieChartIntent = new Intent(MoodHistory.this, PieChartResult.class);
                startActivity(mPieChartIntent);
            }
        });


    }


}
