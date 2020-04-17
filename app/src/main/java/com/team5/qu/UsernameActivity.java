package com.team5.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UsernameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
    }

    //TODO: When next button is clicked:
    //  Check if username is already taken and give according prompt
    //  Check if password and password confirm match and give according prompt
}
