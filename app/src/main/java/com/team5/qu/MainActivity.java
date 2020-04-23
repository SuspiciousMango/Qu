package com.team5.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //private ArrayList<Account> allAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //allAccounts = new ArrayList<Account>();
        //input account file data here
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
        String uName = username.getText().toString().trim();
        String pWord = password.getText().toString().trim();
        //check if username and password fields are empty
        if(uName.length() == 0) {
            usernameError.show();
        }
        else if(pWord.length() == 0) {
            passwordError.show();
        }
        //check if username and password match an existing account
        //log user in if so, display error otherwise
        else {
            /*for(int i = 0; i < allAccounts.size(); i++){
                Account temp = allAccounts.get(i);
                if(temp.getUserName().equals(uName) && temp.getPassword().equals(pWord)){
                    Intent mainMenu = new Intent(this, MainMenuActivity.class);
                    //Pass all account data to mainMenu intent
                    startActivity(mainMenu);
                }
            */
            loginError.show();
        }
    }

    /**
     * Create account method created by Jayson and edited by
     * Called when the user clicks the create account button
     */
    public void createAccount(View v) {
        Intent createAcc = new Intent(this, AccountCreateActivity.class);
        //allAccounts will probably have to get passed to the createAcc activity to check usernames
        startActivity(createAcc);
    }
}
