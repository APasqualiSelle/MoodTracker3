package com.alineselle.moodtracker3;

import android.app.Activity;
import android.app.Dialog;
import android.widget.Button;
import android.widget.EditText;

public class CustomPopup extends Dialog {


    private EditText mEditText;
    private Button mValidButton, mCancelButton;



    public CustomPopup(Activity activity){

        super(activity,R.style.Theme_AppCompat_DayNight_Dialog);
        setContentView(R.layout.my_popup_template);

        this.mCancelButton = findViewById(R.id.annulerButton);
        this.mValidButton = findViewById(R.id.validerButton);
        this.mEditText = findViewById(R.id.editTextPopup);
    }

    public Button getValidButton(){

        return mValidButton;
    }

    public Button getCancelButton(){

        return mCancelButton;
    }

    public EditText getEditText() {
        return mEditText;
    }

    public void build(){

        show();

}
}
