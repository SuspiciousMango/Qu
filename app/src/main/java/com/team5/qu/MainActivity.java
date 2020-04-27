package com.team5.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    //the location that all system files are stored in
    //private File dataLoc = getFilesDir();
    //private ArrayList<Account> allAccounts;
    private byte loginCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //QuSystem.setParentDir(dataLoc);
        FileInputStream inputData;
        try {
            inputData = openFileInput("accounts.txt");
            QuSystem.readAccountsFromFile(inputData);
        }
        catch(FileNotFoundException e){
        }
    }

    /**
     * Sign In method created by Jayson and edited by
     * Called when the user clicks the sign-in button
     */
    public void login(View v) {
        /*Debug code to clear text files in case of emergency hooked up to the login button for easy access
        try{
            FileOutputStream debugStream = openFileOutput("accounts.txt", MODE_PRIVATE);
            OutputStreamWriter accountWriter = new OutputStreamWriter(debugStream);
            accountWriter.write("TestName//TestAccount//test//test@example.com//1234567890//Computer Science (BS)//[4.0'gender'a', 2.0'available times'2:01234:2:2:2:01234:2', 1.0'location to meet'jfmedo', 3.0'study techniques'fplo']//[CS 112, CS 211, CS 222, CS 262, CS 306, CS 310, CS 499]//2//[]//[]//[]//[]\n");
            accountWriter.write("TestName2//TestAccount2//test//test2@example.com//0987654321//Computer Science (BS)//[4.0'gender'a', 2.0'available times'2:01234:2:2:2:01234:2', 1.0'location to meet'jfmedo', 3.0'study techniques'fplo']//[CS 112, CS 211, CS 222, CS 262, CS 306, CS 310, CS 499]//2//[]//[]//[]//[]\n");
            accountWriter.write("TestName3//TestAccount3//test//test3@example.com//0000000000//Computer Science (BS)//[4.0'gender'a', 2.0'available times'2:01234:2:2:2:01234:2', 1.0'location to meet'jfmedo', 3.0'study techniques'fplo']//[CS 112, CS 211, CS 222, CS 262, CS 306, CS 310, CS 499]//2//[]//[]//[]//[]\n");
            accountWriter.write("TestName4//TestAccount4//test//test4@example.com//9999999999//Computer Science (BS)//[4.0'gender'a', 2.0'available times'2:01234:2:2:2:01234:2', 1.0'location to meet'jfmedo', 3.0'study techniques'fplo']//[CS 112, CS 211, CS 222, CS 262, CS 306, CS 310, CS 499]//2//[]//[]//[]//[]\n");
            accountWriter.close();
        }
        catch (IOException e) {
        }
         //*/
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
            Account loginAttempt = QuSystem.getAccountFromUsername(username.getText().toString().trim());
            if(loginAttempt == null){
                loginError.show();
                return;
            }
            //if loginCount > 5, do not allow any more attempts with the given username
            if(loginCount > 5){
                accountLock.show();
                loginAttempt.setLocked();
            }
            //if account is locked, do not allow login
            if(loginAttempt.isLocked()){
                accountLockError.show();
            }
            else{
                if(loginAttempt.getPassword().equals(pWord)){
                    Intent mainMenu = new Intent(this, MainMenuActivity.class);
                    mainMenu.putExtra("CURRENT_ACCOUNT", loginAttempt.getUsername());
                    startActivity(mainMenu);
                }
                else{
                    loginPWError.show();
                    loginCount++;
                }
            }
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
