package com.team5.qu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class AccountCreateActivity extends AppCompatActivity {

    private byte screenCount = 0;
    private String newUsername;
    private String newPassword;
    private String newName;
    private String newGender;
    private String newEmail;
    private String newPhoneNum;
    private String newMajor;
    private int newYear;
    private ArrayList<String> courses;
    private String newGenderPreference;
    private String newLocations;
    private String newTechniques;
    private String newTimes;
    private ArrayList<String> newPreferenceOrder;
    private ArrayList<Preference> newPreferences;
    private Account newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
    }

    /**
     * Method called on the click of the next button during account creation
     * Created by Jayson and edited by
     * WARNING: This method is disgustingly long. If I had time to methodize I would have
     */
    public void onNextClick(View v) {
        //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
        //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
        Snackbar usernameInUseError = Snackbar.make(v, "ERROR: Username already in use", Snackbar.LENGTH_LONG);
        Snackbar usernameBlankError = Snackbar.make(v, "ERROR: Username field left blank", Snackbar.LENGTH_LONG);
        Snackbar passwordMatchError = Snackbar.make(v, "ERROR: Password does not match password confirm", Snackbar.LENGTH_LONG);
        Snackbar passwordBlankError = Snackbar.make(v, "ERROR: Password field left blank", Snackbar.LENGTH_LONG);
        Snackbar nameBlankError = Snackbar.make(v, "ERROR: Name field left blank", Snackbar.LENGTH_LONG);
        Snackbar noGenderSelectedError = Snackbar.make(v, "ERROR: No gender selected", Snackbar.LENGTH_LONG);
        Snackbar emailBlankError = Snackbar.make(v, "ERROR: Email field left blank", Snackbar.LENGTH_LONG);
        Snackbar phoneBlankError = Snackbar.make(v, "ERROR: Phone number field left blank", Snackbar.LENGTH_LONG);
        Snackbar noMajorSelectedError = Snackbar.make(v, "ERROR: No major selected", Snackbar.LENGTH_LONG);
        Snackbar noYearSelectedError = Snackbar.make(v, "ERROR: No year selected", Snackbar.LENGTH_LONG);
        Snackbar noClassSelectedError = Snackbar.make(v, "ERROR: At least 1 class must be selected", Snackbar.LENGTH_LONG);
        Snackbar noGenderPreferenceError = Snackbar.make(v, "ERROR: No gender preference selected", Snackbar.LENGTH_LONG);
        Snackbar noLocationSelectedError = Snackbar.make(v, "ERROR: At least 1 location must be selected", Snackbar.LENGTH_LONG);
        Snackbar noTechniqueSelectedError = Snackbar.make(v, "ERROR: At least 1 study technique must be selected", Snackbar.LENGTH_LONG);
        Snackbar noTimeSelectedError = Snackbar.make(v, "ERROR: At least 1 timeslot must be selected (more is better though)", Snackbar.LENGTH_LONG);
        Snackbar noPreferenceSelectedError = Snackbar.make(v, "ERROR: Please order all 4 preferences", Snackbar.LENGTH_LONG);
        if(screenCount == 0) {
            EditText username = findViewById(R.id.new_username);
            EditText password = findViewById(R.id.new_password);
            EditText pWordConfirm = findViewById(R.id.confirm_password);
            String tempUName = username.getText().toString().trim();
            String tempPWord = password.getText().toString().trim();
            String pWordCnf = pWordConfirm.getText().toString().trim();
            boolean userFlag = false;
            boolean pWordFlag = false;
            if(tempUName.length() == 0){
                usernameBlankError.show();
            }
            else if(QuSystem.checkAvailableAccount(tempUName)) {
                newUsername = tempUName;
                userFlag = true;
            }
            else{
                usernameInUseError.show();
            }
            if(tempPWord.length() == 0){
                passwordBlankError.show();
            }
            else if(tempPWord.equals(pWordCnf)) {
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
            EditText name = findViewById(R.id.name);
            RadioGroup gender = findViewById(R.id.genderRadioGroup);
            final int MALE = R.id.male;
            final int FEMALE = R.id.female;
            final int OTHER = R.id.pns;
            EditText email = findViewById(R.id.email);
            EditText phone = findViewById(R.id.phone);
            String tempName = name.getText().toString().trim();
            int tempGender = gender.getCheckedRadioButtonId();
            String tempEmail = email.getText().toString().trim();
            String tempPhone = phone.getText().toString().trim();
            boolean nameFlag = false;
            boolean genderFlag = false;
            boolean emailFlag = false;
            boolean phoneFlag = false;
            if(tempName.length() == 0){
                nameBlankError.show();
            }
            else{
                newName = tempName;
                nameFlag = true;
            }
            if(tempGender == MALE) {
                newGender = "m";
                genderFlag = true;
            }
            else if(tempGender == FEMALE) {
                newGender = "f";
                genderFlag = true;
            }
            else if(tempGender == OTHER) {
                newGender = "o";
                genderFlag = true;
            }
            else{
                noGenderSelectedError.show();
            }
            if(tempEmail.length() == 0){
                emailBlankError.show();
            }
            else{
                newEmail = tempEmail;
                emailFlag = true;
            }
            if(tempPhone.length() == 0){
                phoneBlankError.show();
            }
            else{
                newPhoneNum = tempPhone;
                phoneFlag = true;
            }
            if(nameFlag && genderFlag && emailFlag && phoneFlag) {
                setContentView(R.layout.activity_major);
                screenCount++;
            }
        }
        else if(screenCount == 2){
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            Spinner major = findViewById(R.id.spinner_major);
            RadioGroup year = findViewById(R.id.yearRadioGroup);
            final int FRESH = R.id.freshman;
            final int SOPH = R.id.sophomore;
            final int JUN = R.id.junior;
            final int SEN = R.id.senior;
            String tempMajor = ((String)(major.getSelectedItem())).trim();
            int tempYear = year.getCheckedRadioButtonId();
            boolean majorFlag = false;
            boolean yearFlag = false;
            if(tempMajor.equals("none")) {
                noMajorSelectedError.show();
            }
            else{
                newMajor = tempMajor;
                majorFlag = true;
            }
            if(tempYear == FRESH) {
                newYear = 1;
                yearFlag = true;
            }
            else if(tempYear == SOPH) {
                newYear = 2;
                yearFlag = true;
            }
            else if(tempYear == JUN) {
                newYear = 3;
                yearFlag = true;
            }
            else if(tempYear == SEN) {
                newYear = 4;
                yearFlag = true;
            }
            else{
                noYearSelectedError.show();
            }
            if(majorFlag && yearFlag) {
                setContentView(R.layout.activity_classes);
                screenCount++;
            }
        }
        else if(screenCount == 3) {
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            Spinner class1 = findViewById(R.id.class_1);
            Spinner class2 = findViewById(R.id.class_2);
            Spinner class3 = findViewById(R.id.class_3);
            Spinner class4 = findViewById(R.id.class_4);
            Spinner class5 = findViewById(R.id.class_5);
            Spinner class6 = findViewById(R.id.class_6);
            Spinner class7 = findViewById(R.id.class_7);
            String tempClass1 = ((String)(class1.getSelectedItem())).trim();
            String tempClass2 = ((String)(class2.getSelectedItem())).trim();
            String tempClass3 = ((String)(class3.getSelectedItem())).trim();
            String tempClass4 = ((String)(class4.getSelectedItem())).trim();
            String tempClass5 = ((String)(class5.getSelectedItem())).trim();
            String tempClass6 = ((String)(class6.getSelectedItem())).trim();
            String tempClass7 = ((String)(class7.getSelectedItem())).trim();
            courses = new ArrayList<String>();
            boolean classFlag = false;
            if(!tempClass1.equals("none")) {
                courses.add(tempClass1);
            }
            if(!tempClass2.equals("none")) {
                courses.add(tempClass2);
            }
            if(!tempClass3.equals("none")) {
                courses.add(tempClass3);
            }
            if(!tempClass4.equals("none")) {
                courses.add(tempClass4);
            }
            if(!tempClass5.equals("none")) {
                courses.add(tempClass5);
            }
            if(!tempClass6.equals("none")) {
                courses.add(tempClass6);
            }
            if(!tempClass7.equals("none")) {
                courses.add(tempClass7);
            }
            if(courses.size() == 0){
                noClassSelectedError.show();
            }
            else{
                classFlag = true;
            }
            if(classFlag) {
                setContentView(R.layout.activity_preferences);
                screenCount++;
            }
        }
        else if(screenCount == 4) {
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            RadioGroup genderPreference = findViewById(R.id.studyGenderRadioGroup);
            final int MALES = R.id.males;
            final int FEMALES = R.id.females;
            final int ANYONE = R.id.anyone;
            CheckBox locJC = findViewById(R.id.check_jc);
            CheckBox locFen = findViewById(R.id.check_fenwick);
            CheckBox locMIX = findViewById(R.id.check_mix);
            CheckBox locEngr = findViewById(R.id.check_engr);
            CheckBox locDorm = findViewById(R.id.check_dorm);
            CheckBox locOnline = findViewById(R.id.check_online);
            CheckBox prefFlash = findViewById(R.id.check_flashcards);
            CheckBox prefPract = findViewById(R.id.check_practiceproblems);
            CheckBox prefLect = findViewById(R.id.check_lecture);
            CheckBox prefTA = findViewById(R.id.check_officehours);
            int tempGenderPreference = genderPreference.getCheckedRadioButtonId();
            boolean tempLocJC = locJC.isChecked();
            boolean tempLocFen = locFen.isChecked();
            boolean tempLocMIX = locMIX.isChecked();
            boolean tempLocEngr = locEngr.isChecked();
            boolean tempLocDorm = locDorm.isChecked();
            boolean tempLocOnline = locOnline.isChecked();
            boolean tempPrefFlash = prefFlash.isChecked();
            boolean tempPrefPract = prefPract.isChecked();
            boolean tempPrefLect = prefLect.isChecked();
            boolean tempPrefTA = prefTA.isChecked();
            boolean genderPreferenceFlag = false;
            boolean locationFlag = false;
            boolean techniqueFlag = false;
            String tempLocations = "";
            String tempTechniques = "";
            if(tempGenderPreference == MALES){
                newGenderPreference = "m";
                genderPreferenceFlag = true;
            }
            else if(tempGenderPreference == FEMALES){
                newGenderPreference = "f";
                genderPreferenceFlag = true;
            }
            else if(tempGenderPreference == ANYONE){
                newGenderPreference = "a";
                genderPreferenceFlag = true;
            }
            else{
                noGenderPreferenceError.show();
            }
            if(tempLocJC){
                tempLocations += "j";
                locationFlag = true;
            }
            if(tempLocFen){
                tempLocations += "f";
                locationFlag = true;
            }
            if(tempLocMIX){
                tempLocations += "m";
                locationFlag = true;
            }
            if(tempLocEngr){
                tempLocations += "e";
                locationFlag = true;
            }
            if(tempLocDorm){
                tempLocations += "d";
                locationFlag = true;
            }
            if(tempLocOnline){
                tempLocations += "o";
                locationFlag = true;
            }
            if(locationFlag){
                newLocations = tempLocations;
            }
            else{
                noLocationSelectedError.show();
            }
            if(tempPrefFlash){
                tempTechniques += "f";
                techniqueFlag = true;
            }
            if(tempPrefPract){
                tempTechniques += "p";
                techniqueFlag = true;
            }
            if(tempPrefLect){
                tempTechniques += "l";
                techniqueFlag = true;
            }
            if(tempPrefTA){
                tempTechniques += "o";
                techniqueFlag = true;
            }
            if(techniqueFlag){
                newTechniques = tempTechniques;
            }
            else{
                noTechniqueSelectedError.show();
            }
            if(genderPreferenceFlag && locationFlag && techniqueFlag) {
                setContentView(R.layout.activity_time);
                screenCount++;
            }
        }
        else if(screenCount == 5) {
            //Do checks before changing screen (and stop the screen from changing if stuff isn't correct
            //Also screenCount should only increment when the screen actually changes, not before or that will screw things up
            CheckBox m1 = findViewById(R.id.m_8_11AM);
            CheckBox m2 = findViewById(R.id.m_11_2);
            CheckBox m3 = findViewById(R.id.m_2_5);
            CheckBox m4 = findViewById(R.id.m_5_8);
            CheckBox m5 = findViewById(R.id.m_8_11PM);
            CheckBox t1 = findViewById(R.id.t_8_11AM);
            CheckBox t2 = findViewById(R.id.t_11_2);
            CheckBox t3 = findViewById(R.id.t_2_5);
            CheckBox t4 = findViewById(R.id.t_5_8);
            CheckBox t5 = findViewById(R.id.t_8_11PM);
            CheckBox w1 = findViewById(R.id.w_8_11AM);
            CheckBox w2 = findViewById(R.id.w_11_2);
            CheckBox w3 = findViewById(R.id.w_2_5);
            CheckBox w4 = findViewById(R.id.w_5_8);
            CheckBox w5 = findViewById(R.id.w_8_11PM);
            CheckBox th1 = findViewById(R.id.th_8_11AM);
            CheckBox th2 = findViewById(R.id.th_11_2);
            CheckBox th3 = findViewById(R.id.th_2_5);
            CheckBox th4 = findViewById(R.id.th_5_8);
            CheckBox th5 = findViewById(R.id.th_8_11PM);
            CheckBox f1 = findViewById(R.id.f_8_11AM);
            CheckBox f2 = findViewById(R.id.f_11_2);
            CheckBox f3 = findViewById(R.id.f_2_5);
            CheckBox f4 = findViewById(R.id.f_5_8);
            CheckBox f5 = findViewById(R.id.f_8_11PM);
            CheckBox sa1 = findViewById(R.id.sa_8_11AM);
            CheckBox sa2 = findViewById(R.id.sa_11_2);
            CheckBox sa3 = findViewById(R.id.sa_2_5);
            CheckBox sa4 = findViewById(R.id.sa_5_8);
            CheckBox sa5 = findViewById(R.id.sa_8_11PM);
            CheckBox su1 = findViewById(R.id.su_8_11AM);
            CheckBox su2 = findViewById(R.id.su_11_2);
            CheckBox su3 = findViewById(R.id.su_2_5);
            CheckBox su4 = findViewById(R.id.su_5_8);
            CheckBox su5 = findViewById(R.id.su_8_11PM);
            String tempTimes = "";
            boolean timeFlag = false;
            if(m1.isChecked()){
                tempTimes += "0";
                timeFlag = true;
            }
            if(m2.isChecked()){
                tempTimes += "1";
                timeFlag = true;
            }
            if(m3.isChecked()){
                tempTimes += "2";
                timeFlag = true;
            }
            if(m4.isChecked()){
                tempTimes += "3";
                timeFlag = true;
            }
            if(m5.isChecked()){
                tempTimes += "4";
                timeFlag = true;
            }
            tempTimes += ":";
            if(t1.isChecked()){
                tempTimes += "0";
                timeFlag = true;
            }
            if(t2.isChecked()){
                tempTimes += "1";
                timeFlag = true;
            }
            if(t3.isChecked()){
                tempTimes += "2";
                timeFlag = true;
            }
            if(t4.isChecked()){
                tempTimes += "3";
                timeFlag = true;
            }
            if(t5.isChecked()){
                tempTimes += "4";
                timeFlag = true;
            }
            tempTimes += ":";
            if(w1.isChecked()){
                tempTimes += "0";
                timeFlag = true;
            }
            if(w2.isChecked()){
                tempTimes += "1";
                timeFlag = true;
            }
            if(w3.isChecked()){
                tempTimes += "2";
                timeFlag = true;
            }
            if(w4.isChecked()){
                tempTimes += "3";
                timeFlag = true;
            }
            if(w5.isChecked()){
                tempTimes += "4";
                timeFlag = true;
            }
            tempTimes += ":";
            if(th1.isChecked()){
                tempTimes += "0";
                timeFlag = true;
            }
            if(th2.isChecked()){
                tempTimes += "1";
                timeFlag = true;
            }
            if(th3.isChecked()){
                tempTimes += "2";
                timeFlag = true;
            }
            if(th4.isChecked()){
                tempTimes += "3";
                timeFlag = true;
            }
            if(th5.isChecked()){
                tempTimes += "4";
                timeFlag = true;
            }
            tempTimes += ":";
            if(f1.isChecked()){
                tempTimes += "0";
                timeFlag = true;
            }
            if(f2.isChecked()){
                tempTimes += "1";
                timeFlag = true;
            }
            if(f3.isChecked()){
                tempTimes += "2";
                timeFlag = true;
            }
            if(f4.isChecked()){
                tempTimes += "3";
                timeFlag = true;
            }
            if(f5.isChecked()){
                tempTimes += "4";
                timeFlag = true;
            }
            tempTimes += ":";
            if(sa1.isChecked()){
                tempTimes += "0";
                timeFlag = true;
            }
            if(sa2.isChecked()){
                tempTimes += "1";
                timeFlag = true;
            }
            if(sa3.isChecked()){
                tempTimes += "2";
                timeFlag = true;
            }
            if(sa4.isChecked()){
                tempTimes += "3";
                timeFlag = true;
            }
            if(sa5.isChecked()){
                tempTimes += "4";
                timeFlag = true;
            }
            tempTimes += ":";
            if(su1.isChecked()){
                tempTimes += "0";
                timeFlag = true;
            }
            if(su2.isChecked()){
                tempTimes += "1";
                timeFlag = true;
            }
            if(su3.isChecked()){
                tempTimes += "2";
                timeFlag = true;
            }
            if(su4.isChecked()){
                tempTimes += "3";
                timeFlag = true;
            }
            if(su5.isChecked()) {
                tempTimes += "4";
                timeFlag = true;
            }
            newTimes = tempTimes;
            if(timeFlag) {
                setContentView(R.layout.activity_order_preferences);
                screenCount++;
            }
            else{
                noTimeSelectedError.show();
            }
        }
        else {
            //If screencount has incremented past 5 (made it to 6), the account creation process is over and its time to display the main menu
            //DON'T FORGET TO DO CHECKS FOR THE PREFERENCE ORDERING SCREEN HERE BEFORE FINISHING ACCOUNT CREATION PROCESS
            Spinner preference1 = findViewById(R.id.preference1);
            Spinner preference2 = findViewById(R.id.preference2);
            Spinner preference3 = findViewById(R.id.preference3);
            Spinner preference4 = findViewById(R.id.preference4);
            newPreferenceOrder = new ArrayList<String>();
            newPreferenceOrder.add(((String)(preference1.getSelectedItem())).trim());
            newPreferenceOrder.add(((String)(preference2.getSelectedItem())).trim());
            newPreferenceOrder.add(((String)(preference3.getSelectedItem())).trim());
            newPreferenceOrder.add(((String)(preference4.getSelectedItem())).trim());
            if(newPreferenceOrder.get(0).equals("none") || newPreferenceOrder.get(1).equals("none") || newPreferenceOrder.get(2).equals("none") || newPreferenceOrder.get(3).equals("none")){
                noPreferenceSelectedError.show();
            }
            else {
                newPreferences = new ArrayList<Preference>();
                newPreferences.add(new Preference(0.0, "gender", newGenderPreference));
                newPreferences.add(new Preference(0.0, "available times", newTimes));
                newPreferences.add(new Preference(0.0, "location to meet", newLocations));
                newPreferences.add(new Preference(0.0, "study techniques", newTechniques));
                for(int i = 0; i < newPreferenceOrder.size(); i++){
                    String temp = newPreferenceOrder.get(i);
                    if(temp.equals("Gender")){
                        newPreferences.get(0).setWeight(i + 1);
                    }
                    if(temp.equals("Time")){
                        newPreferences.get(1).setWeight(i + 1);
                    }
                    if(temp.equals("Location")){
                        newPreferences.get(2).setWeight(i + 1);
                    }
                    if(temp.equals("Technique")){
                        newPreferences.get(3).setWeight(i + 1);
                    }
                }
                newAccount = new Account(newName, newUsername, newPassword, newEmail, newPhoneNum, newMajor, courses, newYear, newPreferences);
                QuSystem.addAccount(newAccount);
                try {
                    QuSystem.writeAccountsToFile(openFileOutput("accounts.txt", MODE_PRIVATE));
                }
                catch(FileNotFoundException e){
                }
                Intent mainMenu = new Intent(this, MainMenuActivity.class);
                mainMenu.putExtra("CURRENT_ACCOUNT", newUsername);
                startActivity(mainMenu);
            }
        }
    }
}
