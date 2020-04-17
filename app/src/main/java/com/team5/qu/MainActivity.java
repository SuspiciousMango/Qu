package com.team5.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Sign In method created by Jayson and edited by
     * Called when the user clicks the sign-in button
     */
    public void login(View v) {
        EditText username = (EditText)(findViewById(R.id.username));
        EditText password = (EditText)(findViewById(R.id.password));
        Snackbar usernameError = Snackbar.make(v, "Username cannot be left blank", Snackbar.LENGTH_LONG);
        Snackbar passwordError = Snackbar.make(v, "Password cannot be left blank", Snackbar.LENGTH_LONG);
        Snackbar loginError = Snackbar.make(v, "Incorrect username or password", Snackbar.LENGTH_LONG);
        //check if username and password fields are empty
        if(username.getText().length() == 0) {
            usernameError.show();
        }
        else if(password.getText().length() == 0) {
            passwordError.show();
        }
        //check if username and password match an existing account
        //log user in if so, display error otherwise
        else {
            loginError.show();
        }
    }

    /**
     * Create account method created by Jayson and edited by
     * Called when the user clicks the create account button
     */
    public void createAccount(View v) {
        Intent createAcc = new Intent(this, UsernameActivity.class);
        startActivity(createAcc);
    }
}
