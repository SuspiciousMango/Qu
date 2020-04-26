package com.team5.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //private ArrayList<Account> allAccounts;
    private byte loginCount = 0;

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
        Snackbar usernameError = Snackbar.make(v, "ERROR: Username cannot be left blank", Snackbar.LENGTH_LONG);
        Snackbar passwordError = Snackbar.make(v, "ERROR: Password cannot be left blank", Snackbar.LENGTH_LONG);
        Snackbar loginPWError = Snackbar.make(v, "ERROR: Incorrect password", Snackbar.LENGTH_LONG);
        Snackbar loginError = Snackbar.make(v, "ERROR: No account found with given credentials", Snackbar.LENGTH_LONG);
        Snackbar accountLock = Snackbar.make(v, "ERROR: 5 password attempts exceeded, account now locked", Snackbar.LENGTH_LONG);
        Snackbar accountLockError = Snackbar.make(v, "ERROR: This account is locked", Snackbar.LENGTH_LONG);
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
                if(temp.getUserName().equals(uName){
                    //if loginCount > 5, do not allow any more attempts with the given username
                    if(loginCount > 5){
                        accountLock.show();
                        temp.setLocked();
                    }
                    //if account is locked, do not allow login
                    if(temp.isLocked()){
                        accountLockError.show();
                    }
                    else{
                        if(temp.getPassword().equals(pWord)){
                            Intent mainMenu = new Intent(this, MainMenuActivity.class);
                            //Pass all account data to mainMenu intent
                            startActivity(mainMenu);
                        }
                       else{
                            loginPWError.show();
                            loginCount++;
                        }
                    }
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
