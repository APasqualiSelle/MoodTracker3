package Controller;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alineselle.moodtracker3.R;

import java.util.List;

import Model.Mood;

public class MoodHistory extends AppCompatActivity {

    private TextView mDisplayComment;
    private String mStoreComment;
    private ImageView mDisplayColorMoodChosen;
    private ImageView mDisplayCommentImage;

    private int mStoreLastColorChosen;


    private SharedPreferences mHistoryResultPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_history);

        mDisplayCommentImage = findViewById(R.id.Comment_imageView);

        mHistoryResultPreferences = getSharedPreferences(MainActivity.PREF_KEY_HISTORY_MOOD, MODE_PRIVATE);
        mDisplayColorMoodChosen = findViewById(R.id.chosenMoodColor1);

        mStoreLastColorChosen = mHistoryResultPreferences.getInt(MainActivity.PREF_KEY_LAST_COLOR, 0);
        mDisplayColorMoodChosen.setImageResource(mStoreLastColorChosen);


        mStoreComment = mHistoryResultPreferences.getString(MainActivity.PREF_KEY_COMMENT, "");
        mDisplayComment = findViewById(R.id.commentText);
        mDisplayComment.setText(mStoreComment);



    }



}
