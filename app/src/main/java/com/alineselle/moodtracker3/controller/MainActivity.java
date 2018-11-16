package com.alineselle.moodtracker3.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;

import com.alineselle.moodtracker3.R;

import com.alineselle.moodtracker3.model.Mood;

import java.util.Calendar;

import androidx.work.WorkManager;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SwipeGestureDetector mSwipeGestureDetector;

    public static final String PREF_KEY_HISTORY_MOOD = "PREF_KEY_HISTORY_MOOD";

    public static final String[] PREF_KEY_COMMENT = {"PREF_KEY_COMMENT_YESTERDAY",
                                                     "PREF_KEY_COMMENT_2_DAYS_AGO",
                                                     "PREF_KEY_COMMENT_3_DAYS_AGO",
                                                     "PREF_KEY_COMMENT_4_DAYS_AGO",
                                                     "PREF_KEY_COMMENT_5_DAYS_AGO",
                                                     "PREF_KEY_COMMENT_6_DAYS_AGO",
                                                     "PREF_KEY_COMMENT_A_WEEK_AGO"};

    public static final String[] PREF_KEY_MOOD_COLOR = {"PREF_KEY_MOOD_COLOR_YESTERDAY",
            "PREF_KEY_MOOD_COLOR_2_DAYS_AGO",
            "PREF_KEY_MOOD_COLOR_3_DAYS_AGO",
            "PREF_KEY_MOOD_COLOR_4_DAYS_AGO",
            "PREF_KEY_MOOD_COLOR_5_DAYS_AGO",
            "PREF_KEY_MOOD_COLOR_6_DAYS_AGO",
            "PREF_KEY_MOOD_COLOR_A_WEEK_AGO"};

    public static final String[] PREF_KEY_MOOD_LEVEL = {"PREF_KEY_MOOD_LEVEL_YESTERDAY",
            "PREF_KEY_MOOD_LEVEL_2_DAYS_AGO",
            "PREF_KEY_MOOD_LEVEL_3_DAYS_AGO",
            "PREF_KEY_MOOD_LEVEL_4_DAYS_AGO",
            "PREF_KEY_MOOD_LEVEL_5_DAYS_AGO",
            "PREF_KEY_MOOD_LEVEL_6_DAYS_AGO",
            "PREF_KEY_MOOD_LEVEL_A_WEEK_AGO"};
    
    public static final String PREF_KEY_COMMENT_NOW = "PREF_KEY_COMMENT_NOW";
    public static final String PREF_KEY_MOOD_COLOR_NOW = "PREF_KEY_MOOD_COLOR_NOW";
    public static final String PREF_KEY_MOOD_LEVEL_NOW = "PREF_KEY_MOOD_LEVEL_NOW";

    final private int NUMBEROFMOODIMAGES = 5;
    final private int HISTORYSIZE = 7;
    int[] mImagesId = {R.drawable.smiley_sad,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_normal,
            R.drawable.smiley_happy,
            R.drawable.smiley_super_happy};
    int[] mBackgroundColors = {R.color.faded_red
            , R.color.warm_grey,
            R.color.cornflower_blue_65
            , R.color.light_sage,
            R.color.banana_yellow};
    public Mood mCurrentMood;
    /* private Button mButtonPlus;
     private Button mButtonMinus;*/
    private Button mButtonComment;
    private Button mButtonHistoryMood;
    private ImageView mImageMoodView;
    private MainActivity mMainActivity = this;
    private ConstraintLayout mConstraintLayout;
    private SharedPreferences mPreferences;
    private int mMoodIndex = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //  mOneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyMidnightManager.class).build();


       /* Log.e("mDefaultMoodRequest", "Work ID:"+ mOneTimeWorkRequest.getId());*/
         WorkManager.getInstance().cancelAllWork();
  //      WorkManager.getInstance().enqueue(mOneTimeWorkRequest);



        mSwipeGestureDetector = new SwipeGestureDetector(this);

        mPreferences = getSharedPreferences(PREF_KEY_HISTORY_MOOD, MODE_PRIVATE);
        //the idea is to create an object that contains a color and a String if the user sets a comment for that com.alineselle.moodtracker3.model.Mood
        mCurrentMood = new Mood(3, "");

        saveMoodPreferences();

        mButtonComment = findViewById(R.id.buttonComment);
        mButtonHistoryMood = findViewById(R.id.buttonHistory);

        mImageMoodView = findViewById(R.id.imageViewMoods);

        mConstraintLayout = findViewById(R.id.mainLayout);


        mImageMoodView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        mButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CustomPopup mCommentPopup;
                mCommentPopup = new CustomPopup(mMainActivity);

                mCommentPopup.getCancelButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mCommentPopup.dismiss();
                    }
                });

                mCommentPopup.getValidButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String mComment1 = mCommentPopup.getEditText().getText().toString();
                        mCurrentMood.setComment(mComment1);
                        saveMoodPreferences();
                        mCommentPopup.dismiss();
                    }
                });

                mCommentPopup.show();
            }
        });

        mButtonHistoryMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent historicalMoodIntent = new Intent(MainActivity.this, MoodHistory.class);
                startActivity(historicalMoodIntent);

            }
        });

        setAlarmToSaveDefaultMood();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return mSwipeGestureDetector.onTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }


    public void onSwipe(SwipeGestureDetector.SwipeDirection direction) {


        switch (direction) {

            case TOP_TO_BOTTOM:
                if (mMoodIndex > 0) {

                    mMoodIndex--;
                    mCurrentMood = new Mood(mMoodIndex, "");
                    mCurrentMood.setMoodLevel(mMoodIndex);
                    mCurrentMood.getComment();
                    mImageMoodView.setImageResource(mImagesId[mCurrentMood.getMoodLevel()]);
                    mConstraintLayout.setBackgroundColor(getResources().getColor(mBackgroundColors[(mCurrentMood.getMoodLevel())]));
                    saveMoodPreferences();


                }
                break;

            case BOTTOM_TO_TOP:
                if (mMoodIndex < NUMBEROFMOODIMAGES - 1) {

                    mMoodIndex++;
                    mCurrentMood = new Mood(mMoodIndex, "");
                    mCurrentMood.setMoodLevel(mMoodIndex);
                    mCurrentMood.getComment();
                    mImageMoodView.setImageResource(mImagesId[mCurrentMood.getMoodLevel()]);
                    mConstraintLayout.setBackgroundColor(getResources().getColor(mBackgroundColors[mCurrentMood.getMoodLevel()]));
                    saveMoodPreferences();
                }

                break;
        }

    }

    public void saveMoodPreferences() {

        /*Calendar rightNow = Calendar.getInstance();
        Calendar lastTime = Calendar.getInstance();
        int dayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
        int month = rightNow.get(Calendar.MONTH) + 1;
        int year = rightNow.get(Calendar.YEAR);
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minute = rightNow.get(Calendar.MINUTE);
        long milliseconds = rightNow.getTimeInMillis();

        mCurrentMood.setComment(mCurrentMood.getComment() + " " + dayOfMonth + "/" + month + "/" + year + " " + hour + " : " + minute + "--" + milliseconds);*/

        /*long lastTimeInMillis = mPreferences.getLong(PREF_KEY_LAST_TIME, 0);
        int countMinutes = 0;
        if (lastTimeInMillis > 0) {
            lastTime.setTimeInMillis(lastTimeInMillis);
            while(((rightNow.get(Calendar.YEAR) != lastTime.get(Calendar.YEAR))
                    || (rightNow.get(Calendar.MONTH) != lastTime.get(Calendar.MONTH))
                    || (rightNow.get(Calendar.DAY_OF_MONTH) != lastTime.get(Calendar.DAY_OF_MONTH))
                    || (rightNow.get(Calendar.HOUR_OF_DAY) != lastTime.get(Calendar.HOUR_OF_DAY))
                    || (rightNow.get(Calendar.MINUTE) != lastTime.get(Calendar.MINUTE))
                    )
                &&
                    (countMinutes <= 7))   // adding this condition to break the loop after  7 times.*/
/*
        {
                lastTime.add(Calendar.MINUTE, 1);
                countMinutes++; // counting the minutes to limit to 7 max*/

        {/*

            int previousMoodLevel = mPreferences.getInt(PREF_KEY_MOOD_LEVEL_A_WEEK_AGO, -1);
            int lastMoodLevel;
            int previousMoodColor = mPreferences.getInt(PREF_KEY_MOOD_COLOR_A_WEEK_AGO, 0);
            int lastMoodColor;
            String lastComment;
            String previousComment = mPreferences.getString(PREF_KEY_COMMENT_A_WEEK_AGO, "");


            for (int i = 1; i <= 6; i++) {
                lastMoodLevel = mPreferences.getInt("PREF_KEY_MOOD_LEVEL" + i, -1);
                mPreferences.edit().putInt("PREF_KEY_MOOD_LEVEL" + i, previousMoodLevel).apply();
                previousMoodLevel = lastMoodLevel;

                lastMoodColor = mPreferences.getInt("PREF_KEY_MOOD_COLOR" + i, 0);
                mPreferences.edit().putInt("PREF_KEY_MOOD_COLOR" + i, previousMoodColor).apply();
                previousMoodColor = lastMoodColor;

                lastComment = mPreferences.getString("PREF_KEY_COMMENT" + i, "");
                mPreferences.edit().putString("PREF_KEY_COMMENT" + i, previousComment).apply();
                previousComment = lastComment;
            }*/
            /*mPreferences.edit().putInt(PREF_KEY_MOOD_LEVEL_A_WEEK_AGO, 3 ).apply();
            mPreferences.edit().putInt(PREF_KEY_MOOD_COLOR_A_WEEK_AGO, mBackgroundColors[3]).apply();
            mPreferences.edit().putString(PREF_KEY_COMMENT_A_WEEK_AGO, "").apply();*/
        }
    //  mPreferences.edit().putLong(PREF_KEY_LAST_TIME, rightNow.getTimeInMillis()).apply();
        mPreferences.edit().putInt(PREF_KEY_MOOD_LEVEL_NOW, mCurrentMood.getMoodLevel() ).apply();
        mPreferences.edit().putInt(PREF_KEY_MOOD_COLOR_NOW, mBackgroundColors[mCurrentMood.getMoodLevel()]).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT_NOW, mCurrentMood.getComment()).apply();

    Log.d("moodTracker"," save mood __ level : "+mCurrentMood.getMoodLevel() +" color :" +mBackgroundColors[mCurrentMood.getMoodLevel()] + " comment: \""+mCurrentMood.getComment()+"\"");
    }

    void setAlarmToSaveDefaultMood() {


        Calendar calendar = Calendar.getInstance(); // by default , all calendar 's values are correônting to NOW ,so the exact time of this execution

        Log.e("moodtrackerALARM","now is "+calendar.getTime());

        long interval;

        boolean debug  = true;

        if (debug == false)
        {
            //set at 23:59 (to start  just before the next day)
            calendar.set(Calendar.HOUR,23);
            calendar.set(Calendar.MINUTE,59);
            interval = AlarmManager.INTERVAL_DAY; // (to trigger every 24hours )
        }
       else
        {
            //set at NOW + 2 minutes (used to DEBUG, to start soon)
            calendar.add(Calendar.MINUTE,2);
            interval = 2*60*1000;// (to trigger every 2 minutes )
        }


        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyAlarm.class);

        Log.e("moodtrackerALARM","set to "+calendar.getTime());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);

    }


}







