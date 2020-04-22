package com.team5.qu;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class AccountCreateActivity extends AppCompatActivity {

    private int screenCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
    }

    //TODO: When next button is clicked:
    //  If screenCount == 0
    //      Check if username is already taken and give according prompt if so
    //      Check if password and password confirm match and give according prompt if not
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

    public void onNextClick(View v) {
        Snackbar endOfProgramError = Snackbar.make(v, "Unfortunately, this is all of the program we have so far :(", Snackbar.LENGTH_LONG);
        if(screenCount == 0) {
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            setContentView(R.layout.activity_name);
            screenCount++;
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
        else {
            //If screencount has incremented past 4, the account creation process is over and its time to display the main menu
            //DON'T FORGET TO DO CHECKS FOR THE TIME SCREEN HERE BEFORE FINISHING ACCOUNT CREATION PROCESS
            //Intent mainMenu = new Intent(this, MainMenuActivity.class);
            //Transfer all the account data over to main menu/save it to a file
            //startActivity(mainMenu);
            endOfProgramError.show();
        }
    }
}
