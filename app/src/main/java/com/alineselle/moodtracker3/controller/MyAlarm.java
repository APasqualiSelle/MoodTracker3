package com.alineselle.moodtracker3.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.alineselle.moodtracker3.R;

import java.util.Calendar;

public class MyAlarm extends BroadcastReceiver {
    private SharedPreferences mPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
       /* MediaPlayer mediaPlayer = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();*/
        Calendar calendar = Calendar.getInstance();
        Log.e("moodtrackerALARM","TICK : "+calendar.getTime());


        mPreferences = context.getSharedPreferences(MainActivity.PREF_KEY_HISTORY_MOOD, MainActivity.MODE_PRIVATE);

        int previousMoodLevel = mPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL_NOW ,-1);
        int lastMoodLevel;

        int previousMoodColor = mPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR_NOW, 0);
        int lastMoodColor;

        String lastComment;
        String previousComment = mPreferences.getString(MainActivity.PREF_KEY_COMMENT_NOW, "");


        for (int i = 0; i <= 6; i++) {
            lastMoodLevel = mPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[i], -1);
            mPreferences.edit().putInt(MainActivity.PREF_KEY_MOOD_LEVEL[i], previousMoodLevel).apply();
            previousMoodLevel = lastMoodLevel;

            lastMoodColor = mPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[i], 0);
            mPreferences.edit().putInt(MainActivity.PREF_KEY_MOOD_COLOR[i] , previousMoodColor).apply();
            previousMoodColor = lastMoodColor;

            lastComment = mPreferences.getString(MainActivity.PREF_KEY_COMMENT[i], "");
            mPreferences.edit().putString(MainActivity.PREF_KEY_COMMENT[i], previousComment).apply();
            previousComment = lastComment;
        }


        mPreferences.edit().putInt(MainActivity.PREF_KEY_MOOD_LEVEL_NOW, 3).apply();
        mPreferences.edit().putInt(MainActivity.PREF_KEY_MOOD_COLOR_NOW, R.color.light_sage).apply();
        mPreferences.edit().putString(MainActivity.PREF_KEY_COMMENT_NOW, "").apply();
    }
}