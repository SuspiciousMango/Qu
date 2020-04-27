package com.team5.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MainMenuActivity extends AppCompatActivity {

    private Account currentAccount;
    private PriorityQueue<Account> matchList;
    private Account currentMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Intent prevIntent = getIntent();
        String tempUsername = prevIntent.getStringExtra("CURRENT_ACCOUNT");
        currentAccount = QuSystem.getAccountFromUsername(tempUsername);
        TextView name = findViewById(R.id.textName);
        TextView username = findViewById(R.id.textUserName);
        TextView major = findViewById(R.id.textMajor);
        name.setText(currentAccount.getName());
        username.setText("(" + currentAccount.getUsername() + ")");
        major.setText(currentAccount.getMajor());
        matchList = QuSystem.matchUsers(currentAccount);
        getNextMatch();
    }

    private void getNextMatch(){
        TextView matchTitle = findViewById(R.id.matchTitle);
        TextView matchName = findViewById(R.id.match_name);
        TextView matchMajor = findViewById(R.id.match_major);
        TextView matchClass1 = findViewById(R.id.text_class_1);
        TextView matchClass2 = findViewById(R.id.text_class_2);
        TextView matchClass3 = findViewById(R.id.text_class_3);
        TextView matchClass4 = findViewById(R.id.text_class_4);
        TextView matchClass5 = findViewById(R.id.text_class_5);
        TextView matchClass6 = findViewById(R.id.text_class_6);
        TextView matchClass7 = findViewById(R.id.text_class_7);
        if(matchList.size() < 1){
            currentMatch = null;
            matchTitle.setVisibility(View.INVISIBLE);
            matchName.setText("Queue Empty");
            matchMajor.setText("");
            matchClass1.setText("");
            matchClass2.setText("");
            matchClass3.setText("");
            matchClass4.setText("");
            matchClass5.setText("");
            matchClass6.setText("");
            matchClass7.setText("");
        }
        else {
            matchTitle.setVisibility(View.VISIBLE);
            currentMatch = matchList.remove();
            matchName.setText(currentMatch.getName());
            matchMajor.setText(currentMatch.getMajor());
            ArrayList<String> courses = currentMatch.getCourses();
            if(courses.size() > 0){
                matchClass1.setText(courses.get(0));
            }
            if(courses.size() > 1){
                matchClass2.setText(courses.get(1));
            }
            if(courses.size() > 2){
                matchClass3.setText(courses.get(2));
            }
            if(courses.size() > 3){
                matchClass4.setText(courses.get(3));
            }
            if(courses.size() > 4){
                matchClass5.setText(courses.get(4));
            }
            if(courses.size() > 5){
                matchClass6.setText(courses.get(5));
            }
            if(courses.size() > 6){
                matchClass7.setText(courses.get(6));
            }
            currentAccount.addMatched(currentMatch.getUsername());
            currentMatch.addMatched(currentAccount.getUsername());
        }
    }

    public void likeUser(View v){
        if(currentMatch != null) {
            currentAccount.acceptAccount(currentMatch.getUsername());
            if (currentAccount.checkPending(currentMatch.getUsername()) && currentMatch.checkPending(currentAccount.getUsername())) {
                currentAccount.confirmMatch(currentMatch.getUsername());
                currentMatch.confirmMatch(currentAccount.getUsername());
            }
            getNextMatch();
        }
    }

    public void dislikeUser(View v){
        if(currentMatch != null) {
            currentAccount.rejectAccount(currentMatch.getUsername());
            getNextMatch();
        }
    }

    public void showMatchesScreen(View v){
        TextView name1 = findViewById(R.id.matched_first_name);
        TextView name2 = findViewById(R.id.matched_second_name);
        TextView name3 = findViewById(R.id.matched_third_name);
        TextView name4 = findViewById(R.id.matched_fourth_name);
        TextView phone1 = findViewById(R.id.matched_first_phone);
        TextView phone2 = findViewById(R.id.matched_second_phone);
        TextView phone3 = findViewById(R.id.matched_third_phone);
        TextView phone4 = findViewById(R.id.matched_fourth_phone);
        TextView email1 = findViewById(R.id.matched_first_email);
        TextView email2 = findViewById(R.id.matched_second_email);
        TextView email3 = findViewById(R.id.matched_third_email);
        TextView email4 = findViewById(R.id.matched_fourth_email);
        ArrayList<String> matchNames = currentAccount.getConfirmed();
        ArrayList<Account> matchAccounts = new ArrayList<Account>();
        for(int i = 0; i < matchNames.size(); i++){
            matchAccounts.add(QuSystem.getAccountFromUsername(matchNames.get(i)));
        }
        if(matchAccounts.size() > 0){
            Account temp = matchAccounts.get(0);
            name1.setText(temp.getName());
            phone1.setText(temp.getPhoneNumber());
            email1.setText(temp.getEmail());
        }
        if(matchAccounts.size() > 1){
            Account temp = matchAccounts.get(1);
            name2.setText(temp.getName());
            phone2.setText(temp.getPhoneNumber());
            email2.setText(temp.getEmail());
        }
        if(matchAccounts.size() > 2){
            Account temp = matchAccounts.get(2);
            name3.setText(temp.getName());
            phone3.setText(temp.getPhoneNumber());
            email3.setText(temp.getEmail());
        }
        if(matchAccounts.size() > 3){
            Account temp = matchAccounts.get(3);
            name4.setText(temp.getName());
            phone4.setText(temp.getPhoneNumber());
            email4.setText(temp.getEmail());
        }
        setContentView(R.layout.activity_matchedlist);
    }

    public void backToMenu(View v){
        setContentView(R.layout.activity_homepage);
    }

    public void logout(View v) {
        try {
            QuSystem.writeAccountsToFile(openFileOutput("accounts.txt", MODE_PRIVATE));
        }
        catch(FileNotFoundException e){
        }
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
}
