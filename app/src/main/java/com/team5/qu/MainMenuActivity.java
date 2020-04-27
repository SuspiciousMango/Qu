package com.team5.qu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class MainMenuActivity extends AppCompatActivity {

    private Account currentAccount;

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
