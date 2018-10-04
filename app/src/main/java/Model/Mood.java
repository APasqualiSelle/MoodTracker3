package Model;

import android.content.Context;
import android.content.SharedPreferences;


public class Mood {

    public static final String PREF_KEY_MOOD = "PREF_KEY_MOOD";

    private String Comment;
    private int mMoodLevel;

    public Mood(int moodLevel,String comment) {

        mMoodLevel = moodLevel;
        Comment = comment;
    }





    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {

        Comment = comment;
    }

    public int getMoodLevel() {
        return mMoodLevel;
    }

    public void setMoodLevel(int moodLevel) {
        mMoodLevel = moodLevel;
    }
}
