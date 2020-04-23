package com.team5.qu;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class AccountCreateActivity extends AppCompatActivity {

    private int screenCount = 0;
    private String newUsername;
    private String newPassword;
    //private Account newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
    }

    //TODO: When next button is clicked:
    //  If screenCount == 1
    //      Check if name/email/phone are empty and give according prompt if so
    //      Check if a radio button has been selected and give according prompt if not
    //  If screencount == 2
    //      Check if a major has been chosen (selection is not "none") and give according prompt if not
    //      Check if a year has been chosen and give according prompt if not
    //  If screencount == 3
    //      Check that at least one class has been selected (selection is not "none") and give according prompt if not
    //  If screencount == 4
    //      Check if at least one option from each category has been selected and give according prompt if not
    //  If screencount == 5
    //      Check if at least one time slot is checked and give according prompt if not
    //          (additionally, a helpful message could be displayed if the user has selected few (possibly between 1 and 3 or so)
    //          timeslots that the more timeslots they select the easier finding a buddy will be)
    //  If screencount == 6
    //      Check that each preference is ordered and give according prompt if not (one of the boxes left blank or one
    //      of the preferences selected twice

    public void onNextClick(View v) {
        //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
        //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
        Snackbar endOfProgramError = Snackbar.make(v, "Unfortunately, this is all of the program we have so far :(", Snackbar.LENGTH_LONG);
        Snackbar usernameInUseError = Snackbar.make(v, "ERROR: Username already in use", Snackbar.LENGTH_LONG);
        Snackbar passwordMatchError = Snackbar.make(v, "ERROR: Password does not match password confirm", Snackbar.LENGTH_LONG);
        if(screenCount == 0) {
            EditText username = (EditText)(findViewById(R.id.new_username));
            EditText password = (EditText)(findViewById(R.id.new_password));
            EditText pWordConfirm = (EditText)(findViewById(R.id.confirm_password));
            String tempUName = username.getText().toString().trim();
            String tempPWord = password.getText().toString().trim();
            String pWordCnf = pWordConfirm.getText().toString().trim();
            boolean userFlag = false;
            boolean pWordFlag = false;
            if(usernameAvailable(tempUName)) {
                newUsername = tempUName;
                userFlag = true;
            }
            else{
                usernameInUseError.show();
            }
            if(tempPWord.equals(pWordCnf)) {
                newPassword = tempPWord;
                pWordFlag = true;
            }
            else {
                passwordMatchError.show();
            }
            if(userFlag && pWordFlag) {
                setContentView(R.layout.activity_name);
                screenCount++;
            }
        }
        else if(screenCount == 1){
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            setContentView(R.layout.activity_major);
            screenCount++;
        }
        else if(screenCount == 2){
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            setContentView(R.layout.activity_classes);
            screenCount++;
        }
        else if(screenCount == 3) {
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            setContentView(R.layout.activity_preferences);
            screenCount++;
        }
        else if(screenCount == 4) {
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            setContentView(R.layout.activity_time);
            screenCount++;
        }
        else if(screenCount == 5) {
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            setContentView(R.layout.activity_time);
            screenCount++;
        }
        else {
            //If screencount has incremented past 5 (made it to 6), the account creation process is over and its time to display the main menu
            //DON'T FORGET TO DO CHECKS FOR THE PREFERENCE ORDERING SCREEN HERE BEFORE FINISHING ACCOUNT CREATION PROCESS
            //newAccount = new Account(put all data here);
            //Intent mainMenu = new Intent(this, MainMenuActivity.class);
            //Transfer all the account data over to main menu/save it to a file
            //startActivity(mainMenu);
            endOfProgramError.show();
        }
    }

    private boolean usernameAvailable(String uName){
        /*for(int i = 0; i < allAccounts.size(); i++){
            Account temp = allAccounts.get(i);
            if(temp.getUsername().equals(uName)){
                return false;
            }
        }
        */
        return true;
    }
}
