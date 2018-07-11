package com.example.abrarfahim.alumniassociation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class EditStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
    }

    public void onClickCancelButton(View v) {
        finish();
    }
}
