package com.alineselle.moodtracker3.controller;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alineselle.moodtracker3.R;
import com.alineselle.moodtracker3.model.Mood;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;


public class PieChartResult extends AppCompatActivity {

    private static String TAG = "PieChartResult";
    PieChart mPieChart;

    Mood mMood;

    private SharedPreferences mHistoryMoodPreferences;
    private int mStoreColor7;
    private int mStoreColor1;
    private int mStoreColor2;
    private int mStoreColor3;
    private int mStoreColor4;
    private int mStoreColor5;
    private int mStoreColor6;

    private int mStoreMoodLevel1;
    private int mStoreMoodLevel2;
    private int mStoreMoodLevel3;
    private int mStoreMoodLevel4;
    private int mStoreMoodLevel5;
    private int mStoreMoodLevel6;
    private int mStoreMoodLevel7;


    int[] yMoodLevels = new int[7];
    int[] yMoodColors = new int [7];
    String[] xDays = {"Yesterday", "2 days ago", "3 days ago", "4 days ago", "five days ago", "six days ago", "A week ago"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart2);
        mMood = new Mood(3, "");
        //Get the Chart
        mPieChart = findViewById(R.id.PieChart);
        mHistoryMoodPreferences = getSharedPreferences(MainActivity.PREF_KEY_HISTORY_MOOD, MODE_PRIVATE);

       /* for (int i = 0; i < yMoodLevels.length; i++) {

            yMoodLevels[i] = mHistoryMoodPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[i], 0);
        }*/
       /* yMoodLevels[6] = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL_A_WEEK_AGO", 0);*/
/*
       mStoreMoodLevel1 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL1", 0);
        mStoreMoodLevel2 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL2", 0);
        mStoreMoodLevel3 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL3", 0);
        mStoreMoodLevel4 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL4",0);
        mStoreMoodLevel5 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL5",0);
        mStoreMoodLevel6 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL6", 0);
        mStoreMoodLevel7 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL_A_WEEK_AGO", 0);*/

for (int i =0; i< yMoodColors.length; i++){

    yMoodColors[i] = mHistoryMoodPreferences.getInt(MainActivity.PREF_KEY_MOOD_COLOR[i],0);
}
       /* mStoreColor1 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_COLOR1", 0);
        mStoreColor2 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_COLOR2", 0);
        mStoreColor3 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_COLOR3", 0);
        mStoreColor4 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_COLOR4", 0);
        mStoreColor5 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_COLOR5", 0);
        mStoreColor6 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_COLOR6", 0);
        mStoreColor7 = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_COLOR_A_WEEK_AGO", 0);*/
        Log.d(TAG, "onCreate:starting to create Chart");


        mPieChart.getDescription().setText("Weekly Mood");
        mPieChart.setRotationEnabled(true);
        mPieChart.setHoleRadius(25f);
        mPieChart.setTransparentCircleAlpha(0);
        mPieChart.setCenterText("Weekly Mood Pie Charter");
        mPieChart.setCenterTextSize(10);
        //    mPieChart.setUsePercentValues(true);
//mPieChart.setDrawEntryLabels(true);

        addDataSet();
        mPieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart");
            }

            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void addDataSet() {

        for (int i = 0; i < yMoodLevels.length; i++) {
            yMoodLevels[i] = mHistoryMoodPreferences.getInt(MainActivity.PREF_KEY_MOOD_LEVEL[i], -1);
        }
       /* yMoodLevels[6] = mHistoryMoodPreferences.getInt("PREF_KEY_MOOD_LEVEL_A_WEEK_AGO", -1);
        Log.d(TAG, "addDataSet:started");*/


        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        for (int i = 0; i < yMoodLevels.length; i++) {
            if (yMoodLevels[i] >= 0) {
                yEntrys.add(new PieEntry(1, 1));
            }
        }


        //Create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Mood");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(0);


        //add colors todataSet


        /*if ((mStoreColor1 == 0) || (mStoreColor2 == 0) || (mStoreColor3 == 0) || (mStoreColor4 == 0) || (mStoreColor5 == 0) || (mStoreColor6 == 0) || (mStoreColor7 == 0)) {
            colors.add(R.color.faded_red);
        } else if ((mStoreColor1 == 1) || (mStoreColor2 == 1) || (mStoreColor3 == 1) || (mStoreColor4 == 1) || (mStoreColor5 == 1) || (mStoreColor6 == 1) || (mStoreColor7 == 1)) {
            colors.add(R.color.warm_grey);
        } else if (mStoreColor1 == 2 || (mStoreColor2 == 2) || (mStoreColor3 == 2) || (mStoreColor4 == 2) || (mStoreColor5 == 2) || (mStoreColor6 == 2) || (mStoreColor7 == 2)) {
            colors.add(R.color.cornflower_blue_65);
        } else if (mStoreColor1 == 3 || (mStoreColor2 == 3) || (mStoreColor3 == 3) || (mStoreColor4 == 3) || (mStoreColor5 == 3) || (mStoreColor6 == 3) || (mStoreColor7 == 3)) {
            colors.add(R.color.light_sage);
        } else if (mStoreColor1 == 4 || (mStoreColor2 == 4) || (mStoreColor3 == 4) || (mStoreColor4 == 4) || (mStoreColor5 == 4) || (mStoreColor6 == 4) || (mStoreColor7 == 4)) {
            colors.add(R.color.banana_yellow);
        } else {
            colors.add(R.color.transparent);
        }*/
        ArrayList<Integer> colors = new ArrayList<>();

       for(int i =0; i <yMoodColors.length; i++){
           if (yMoodColors[i] != 0)
               colors.add(getResources().getColor(yMoodColors[i]));

       }
       /* if (yMoodColors[0] != 0)
            colors.add(getResources().getColor(yMoodColors[0]));
        if (yMoodColors[1]!= 0)
            colors.add(getResources().getColor(yMoodColors[1]));
        if (yMoodColors[2] != 0)
            colors.add(getResources().getColor(yMoodColors[2]));
        if (yMoodColors[3] != 0)
            colors.add(getResources().getColor(yMoodColors[3]));
        if (yMoodColors[4] != 0)
            colors.add(getResources().getColor(yMoodColors[4]));
        if (yMoodColors[5] != 0)
            colors.add(getResources().getColor(yMoodColors[5]));
        if (yMoodColors[6] != 0)
            colors.add(getResources().getColor(yMoodColors[6]));*/
        Collections.sort(colors);


        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = mPieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //Create pie data object
        PieData pieData = new PieData(pieDataSet);
        mPieChart.setData(pieData);
        mPieChart.invalidate();

    }


}
