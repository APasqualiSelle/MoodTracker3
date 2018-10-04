package Controller;

import android.content.Intent;
import android.content.SharedPreferences;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.Toast;

import com.alineselle.moodtracker3.R;

import Model.Mood;


public class MainActivity extends AppCompatActivity {

    private Mood mCurrentMood;

    private Button mButtonPlus;
    private Button mButtonMinus;
    private Button mButtonComment;
    private Button mButtonHistoryMood;

    private ImageView mImageMoodView;

    private MainActivity mMainActivity;
    private ConstraintLayout mConstraintLayout;
    private SharedPreferences mPreferences;

    final private int NUMBEROFMOODIMAGES = 5;
    private int mMoodIndex = 3;
    public static final String PREF_KEY_HISTORY_MOOD = "PREF_KEY_HISTORY_MOOD";
    public static final String PREF_KEY_COMMENT = "PREF_KEY_COMMENT";
    public static final String PREF_KEY_LAST_COLOR = "PREF_KEY_LAST_COLOR";

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //the idea is to creat an object that contains a color and a String if the user sets a comment for that mood
        mCurrentMood = new Mood(3,"");
       /* mPreferences.edit().putInt(PREF_KEY_LAST_COLOR,mBackgroundColors[mCurrentMood.getMoodLevel()]).apply();
        mPreferences.edit().putString(PREF_KEY_COMMENT, mCurrentMood.getComment()).apply();*/


        mButtonPlus = findViewById(R.id.plus_button);
        mButtonMinus = findViewById(R.id.minus_button);
        mButtonComment = findViewById(R.id.buttonComment);
        mButtonHistoryMood = findViewById(R.id.buttonHistory);

        mImageMoodView = findViewById(R.id.imageViewMoods);

        this.mMainActivity = this;
        mConstraintLayout = findViewById(R.id.mainLayout);
        mPreferences = getSharedPreferences(PREF_KEY_HISTORY_MOOD, MODE_PRIVATE);

        mImageMoodView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);


        mButtonPlus.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {


                                               //it is necessary to put -1 so that the mMoodIndex does not pass the array length that starts by the element 0
                                               if (mMoodIndex < NUMBEROFMOODIMAGES - 1) {
                                                   mMoodIndex++;
                                                   mCurrentMood = new Mood(mMoodIndex,"");
                                                   mCurrentMood.setMoodLevel(mMoodIndex);
                                                   mImageMoodView.setImageResource(mImagesId[mCurrentMood.getMoodLevel()]);
                                                   mConstraintLayout.setBackgroundColor(getResources().getColor(mBackgroundColors[mCurrentMood.getMoodLevel()]));
                                                   mPreferences.edit().putInt(PREF_KEY_LAST_COLOR,mBackgroundColors[mCurrentMood.getMoodLevel()]).apply();
                                                   mPreferences.edit().putString(PREF_KEY_COMMENT, mCurrentMood.getComment()).apply();
                                               }


        }
                                       }


        );


        mButtonMinus.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {


                                                if (mMoodIndex > 0) {
                                                    mMoodIndex--;
                                                    mCurrentMood = new Mood(mMoodIndex,"");
                                                    mCurrentMood.setMoodLevel(mMoodIndex);
                                                    mImageMoodView.setImageResource(mImagesId[mCurrentMood.getMoodLevel()]);
                                                    mConstraintLayout.setBackgroundColor(getResources().getColor(mBackgroundColors[mCurrentMood.getMoodLevel()]));
                                                    mPreferences.edit().putInt(PREF_KEY_LAST_COLOR,mBackgroundColors[mCurrentMood.getMoodLevel()]).apply();
                                                    mPreferences.edit().putString(PREF_KEY_COMMENT, mCurrentMood.getComment()).apply();

                                                    }
                                                }
                                            }



        );

        mButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CustomPopup mCommentPopup = new CustomPopup(mMainActivity);

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
                        mPreferences.edit().putString(PREF_KEY_COMMENT, mCurrentMood.getComment()).apply();
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

    }

}




