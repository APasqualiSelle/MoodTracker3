package com.alineselle.moodtracker3;

import android.app.AlertDialog;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private Button mButtonPlus;
    private Button mButtonMinus;
    private Button mButtonCommentaire;

    private MainActivity mMainActivity;

    private String mTest = "testePlus";
    private String mTest2 = "testMinus";
    private TextView mDisplayText;
    private ImageView mImageMoodView;


    private ConstraintLayout mConstraintLayout;

    public static final String PREF_KEY_COMMENTAIRES = "PREF_KEY_COMMENTAIRE";

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


    final private int NUMBEROFMOODIMAGES = 5;
    private int mMoodIndex = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mMainActivity = this;


        mButtonPlus = findViewById(R.id.plus_button);
        mButtonMinus = findViewById(R.id.minus_button);
        mButtonCommentaire = findViewById(R.id.buttonComment);

        mDisplayText = findViewById(R.id.test);

        mImageMoodView = findViewById(R.id.imageViewMoods);
        mConstraintLayout = findViewById(R.id.mainLayout);
        mImageMoodView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        mButtonPlus.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               mDisplayText.setText(mTest);

                                               //it is necessary to put -1 so that the mMoodIndex does not pass the array length
                                               if (mMoodIndex < NUMBEROFMOODIMAGES - 1) {
                                                   mMoodIndex++;
                                                   mImageMoodView.setImageResource(mImagesId[mMoodIndex]);
                                                   mConstraintLayout.setBackgroundColor(getResources().getColor(mBackgroundColors[mMoodIndex]));
                                               }
                                           }
                                       }
        );
        mButtonMinus.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                mDisplayText.setText(mTest2);
                                                // mImageViewHappy.setVisibility(View.GONE);
                                                if (mMoodIndex > 0) {
                                                    mMoodIndex--;
                                                    mImageMoodView.setImageResource(mImagesId[mMoodIndex]);
                                                    mConstraintLayout.setBackgroundColor(getResources().getColor(mBackgroundColors[mMoodIndex]));

                                                }

                                            }
                                        }
        );

        mButtonCommentaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CustomPopup mCommentPopup = new CustomPopup(mMainActivity);

                mCommentPopup.getAnnulerButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCommentPopup.dismiss();
                    }
                });

                mCommentPopup.getValiderButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCommentPopup.dismiss();
                    }
                });

                mCommentPopup.show();

            }
        });

    }


    //just for testing
    /*public void onClick(View v) {

        int buttonClicked = v.getId();

        switch (buttonClicked) {

            case R.id.plus_button:


                Log.d("Mood Tracker 3", "button plus clicked");


                break;

            case R.id.minus_button:



        }
    }*/
}




