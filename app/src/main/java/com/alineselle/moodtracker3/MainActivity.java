package com.alineselle.moodtracker3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button mButtonPlus;
    private Button mButtonMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonPlus = findViewById(R.id.plus_button);
        mButtonMinus = findViewById(R.id.minus_button);

        int[] imagesIdForPlusButton = {R.drawable.smiley_normal,
                R.drawable.smiley_happy,
                R.drawable.smiley_super_happy};

        int[] imagesIdsForMinusButton = {R.drawable.smiley_disappointed,
                R.drawable.smiley_sad};

        int[] backgroundSadColors = {R.color.warm_grey,
                R.color.faded_red};
        int[]backgroundHappyColors = {R.color.cornflower_blue_65
                ,R.color.light_sage,
                R.color.banana_yellow};
    }
}
