package com.team5.qu;

import java.util.ArrayList;
import java.util.Collections;

//would have been smarter to have made a class with display info...
public class Account /*extends Comparable*/{
    
//people they match with that they haven't confirmed or deny
private ArrayList<Account> matched=new ArrayList<>();

//waits until both sides say yes
private ArrayList<Account> pending=new ArrayList<>();

//people that they have confirmed match 
private ArrayList<Account> confirmed=new ArrayList<>();

//what they prefer
private ArrayList<Preference> preferences=new ArrayList<>();

//the people that were rejected
private ArrayList<String> rejected=new ArrayList<>();


private ArrayList<String> courses=new ArrayList<>();
private String name;
private String username;
private String password="";
private String email;
private String phoneNumber;
private String major;
private int year;

private boolean nameVisible=false;
private boolean emailVisible=false;

private AccountComparator c= new AccountComparator(); 
//private boolean flexible;

//TODO Check for password:not needed


public Account(String name,String username, String password, String email,String phoneNumber, String major, ArrayList<String> courses, int year, ArrayList<Preference> preferenceOrder){
    this.name=name;
    this.username=username;
    this.password=password;
    this.email=email;
    this.phoneNumber=phoneNumber;
    this.major=major;
    this.year=year;
    this.courses=courses;
    /*gender, meeting, times, location, study techniques
    */

    //*Edited by jayson to make UI integration easier
    preferences = preferenceOrder;
    //preferences.add(new Preference(0.0, "gender", ""));
    //preferences.add(new Preference(0.0, "available times", ""));
    //preferences.add(new Preference(0.0, "location to meet", ""));
    //preferences.add(new Preference(0.0, "study techniques", ""));
    //*

}
/**
 *  
    creates a new account with all the weights of the preferences set to 0

    @param name first and last name of the user
    @param username Account's name (each username must be unique)
    @param password hidden from other users
    @param email current email address
    @param phoneNumber current phone number
    @param major current major
    @param year year enrolled
    */
public Account(String name,String username, String password, String email,String phoneNumber, String major, ArrayList<String> courses, int year){
    this.name=name;
    this.username=username;
    this.password=password;
    this.email=email;
    this.phoneNumber=phoneNumber;
    this.major=major;
    this.courses=courses;
    this.year=year;
    /*gender, meeting, times, location, study techniques

    */
    
    preferences.add(new Preference(0.0, "gender", ""));
    preferences.add(new Preference(0.0, "available times", ""));
    preferences.add(new Preference(0.0, "location to meet", ""));
    preferences.add(new Preference(0.0, "study techniques", ""));

}
public Account(String [] info){
    this.name=info[0];
    this.username=info[1];
    this.password=info[2];
    this.email=info[3];
    this.phoneNumber=info[4];
    this.major=info[5];
    this.year=Integer.valueOf(info[8]);
    
    String [] s=info[6].replace("[","").replace("]","").replace(",","").split("\'");
    String []temp=new String[s.length+1];
    for(int i=0; i<s.length;i++){
        temp[i]=s[i];
    }
    temp[s.length]="";
    s=temp;
    for(int i=0;i<(s.length/3);i++){
    preferences.add(new Preference(Double.valueOf(s[i*3]),s[i*3+1],s[i*3+2]));
    }
    s=info[7].replace("[","").replace("]","").replace(" ","").split(",");
    courses.ensureCapacity(s.length);
    for(int i=0;i<s.length;i++){
        courses.add(s[i]);
    }
    
}
/*
//public Account(String name,String username, String email, String phoneNumber, String major,ArrayList<Preference> preferences, int year)
public Account createDummyAccount(){    
    return new Account( name, username, email, phoneNumber, major, preferences.clone(), year);
}
*/

/**
 * 
 * @param name
 * @param username
 * @param email
 * @param phoneNumber
 * @param major
 * @param preferences sets 
 * @param year
 * 
 * Used to clone the main account
 */
public Account(String name,String username, String email,String phoneNumber, String major,ArrayList<Preference> preferences, ArrayList<String> courses, int year){
    this.name=name;
    this.username=username;
    this.email=email;
    this.phoneNumber=phoneNumber;
    this.major=major;
    this.preferences=preferences;
    this.year=year;
    this.courses=courses;
}
/*
private Account(String name,String username, String email,String phoneNumber, String major,ArrayList<Preference> preferences, ArrayList<String> courses, int year){
    this.name=name;
    this.username=username;
    this.email=email;
    this.phoneNumber=phoneNumber;
    this.major=major;
    this.preferences=preferences;
    this.year=year;
    this.courses=courses;
}*/

/**
 * @return the username
 */
public String getUsername() {
    return username;
}
/**
 * @return the confirmed
 */
public ArrayList<Account> getConfirmed() {
    return confirmed;
}
/**
 * @return the email
 */
public String getEmail() {
    return email;
}
/**
 * @return the major
 */
public String getMajor() {
    return major;
}

@SuppressWarnings("unchecked")
/**
 * All of the ArrayLists are in alphabtical order except courses and preferences. getMatched returns a randomized shallow copy of the matched ArrayList
 * @return the matched
 */
public ArrayList<Account> getMatched() {
    ArrayList<Account>x=(ArrayList<Account>)matched.clone();
    Collections.shuffle(x);
    return x;
}
/**
 * @return the name
 */
public String getName() {
    return name;
}
/**
 * @return the password
 */
public String getPassword() {
    return password;
}
/**
 * @return the phoneNumber
 */
public String getPhoneNumber() {
    return phoneNumber;
}
/**
 * @return the preferences
 */
public ArrayList<Preference> getPreferences() {
    return preferences;
}
/**
 * @return the rejected
 */
public ArrayList<String> getRejected() {
    return rejected;
}
/**
 * @return the pending
 */
public ArrayList<Account> getPending() {
    return pending;
}
/**
 * @return the year
 */
public int getYear() {
    return year;
}
/**
 * @return the courses
 */
public ArrayList<String> getCourses(){
    return courses;
}



/**
 * @param year the year to set
 */
public void setYear(int year) {
    this.year = year;
}

/**
 * @param email the email to set
 */
public void setEmail(String email) {
    this.email = email;
}
/**
 * @param major the major to set
 */
public void setMajor(String major) {
    this.major = major;
}

//this method doesn't need to be used
/**
 * @param name the name to set
 */
public void setName(String name) {
    this.name = name;
}

/**
 * @param nameVisible the nameVisible to set
 */
public void setNameVisible(boolean nameVisible) {
    this.nameVisible = nameVisible;
}

/**
 * @param password the password to set
 */
public void setPassword(String password) {
    this.password = password;
}
/**
 * @param phoneNumber the phoneNumber to set
 */
public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
}
public void setCourses(ArrayList<String> courses){
    this.courses=courses;
}

/*********************************************************************** */
/**
 * Given a Preference, the same preference in its list will be found and updated
 * @param preference the preferences to set
 */

public boolean setPreference(Preference preference,int index) {
    if(preferences.get(index).getName().equals(preference.getName())){
    preferences.get(index).setWeight(preference.getWeight());
    preferences.get(index).setChosenOptions(preference.getChosenOptions());
    return true;
    }
    return false;
    /*
    else{
        System.out.println("bad indexing");
    }*/

}
public boolean setPreference(Preference preference) {
    for(int i=0;i<preferences.size();i++){
        if(setPreference(preference,i)){
            return true;
        }
    }
    return false;
}
public void setPreference(ArrayList<Preference> preference) {
    for(int i=0;i<preference.size();i++){
        setPreference(preference.get(i));
    }
}
/************************************************************************* */

private void insertSorted(ArrayList<Account> list,Account a) {
    int x=Collections.binarySearch(list, a, c);
    if(x<0){
   list.add((x+1)*-1,a);}
}

/**
 * @param confirm the confirm to add
 */
private boolean addConfirmed(Account confirm) {
    insertSorted(confirmed,confirm);
   //return confirmed.add(confirm);
   return true;
}
/**
 * implement binary search later
 * @param confirm the confirm to remove
 */
public boolean removeConfirmed(Account confirm) {
    int x=Collections.binarySearch(confirmed, confirm, c);
    if(x>=0){
        confirmed.remove(x);
        return true;
    }
    return false;
}

/**
 * after an arbitrary number of days, the system can remove the people from public
 * Or user can remove from rejected
 * @param reject the reject to remove
 */
public boolean removeRejected(String reject) {

    int x=Collections.binarySearch(rejected, reject);
    if(x>=0){
        confirmed.remove(x);
        return true;
    }
    return false;
}
/**
 * @param reject the reject to add
 */
private boolean addRejected(String reject) {
    rejected.add(Collections.binarySearch(rejected, reject),reject);
    return true;
}
/**
 * NO DUPLICATES
 * people they match with that they haven't confirmed or deny
 * @param match the matched to add
 * @return returns false if it is not a duplicate
 */
public boolean addMatched(Account match) {
    //people they match with that they haven't confirmed or deny

/*
boolean inMatched=Collections.binarySearch(matched,match, c)<0;
boolean inConfirmed=Collections.binarySearch(confirmed,match, c)<0;
boolean inPending=Collections.binarySearch(pending,match, c)<0;
boolean inRejected=Collections.binarySearch(rejected,match.getName())<0;
*/
if(this.equals(match)){
    return false;
}
//if(inMatched&&inConfirmed&&inPending&&inRejected){
insertSorted(matched,match);

//if account was given instead, could match through here
//match.addMatched(this);
    return true;
/*}
return false;*/

}
/**
 * @param match the matched to remove
 */
private boolean removeMatched(Account match) {

    int x=Collections.binarySearch(matched,match, c);
    if(x>=0){
        confirmed.remove(x);
        return true;
    }
    return false;
}

/**
 * @param pend the pending to set
 */
private boolean addPending(Account pend) {    
    insertSorted(pending,pend);
    return true;
}
/**
 * @param pend the pending to set
 */
private boolean removePending(Account pend) {

    int x=Collections.binarySearch(pending, pend, c);
    if(x>=0){
        confirmed.remove(x);
        return true;
    }
    return false;
}


/**********************************************/

/**
 * 
 * @return returns a dummy account where all information is sent except the password
 * courses are shallow copy 
 */
public Account createDummyAccount(){    
    return new Account( name, username, email, phoneNumber, major, clonePreferences(), courses, year);
}
/**
 * Creates a deep copy of the user's preferences
 * @return returns a deep copy of the list of preferences.
 */
public ArrayList<Preference> clonePreferences(){
    ArrayList<Preference> b=new ArrayList<>();
    for(int i =0;i<preferences.size();i++){
        b.add(preferences.get(i));
    }
    return b;
}

/**
 * moves an account from Matched to Pending
 * @param a the account to accept
 * @return true if the account could be removed from matched and added to pending
 */
//put some handling for persistent
public boolean acceptAccount(Account a){
    if(removeMatched(a)){
        if(addPending(a)){   
        return true;
        }
    }
    for(Account x: pending){
        if(this.equals(x)){
            return true;
        }
    }
    return false;
}
/**
 * moves an account from matched to rejected
 * @param a the account to reject
 * @return removes from matched and adds the username to reject to the rejected 
 */
public boolean rejectAccount(Account a){
    if(removeMatched(a)){
        if(addRejected(a.getUsername())){   
        return true;
        }
    }
    for(String x: rejected){
        if(username.equals(x)){
            return true;
        }
    }
    return false;
}
/**
 * searches through the already confirmed matches and returns true if it's already there. If it's not, it will
 * then search through pending and try to remove it
 * @param a
 * @return returns true if already confirmed and if it is able to successfully move the account to confirmed
 */
public boolean confirmMatch(Account a){
    if(a==null){
        return false;
    }
    for(Account x: confirmed){
        if(a.equals(x)){
            removePending(a);
            return true;
        }
    }
    if(removePending(a)){
        if(addConfirmed(a)){   
        return true;
        }
    }
    return false;
}

@Override
public String toString() {
    // TODO Auto-generated method stub
    return name;
}
/**
 * @param other the object to compare
 * @return returns true if the Usernames match
 */
@Override
public boolean equals(Object other) {
        // TODO Auto-generated method stub
        if ( ! (other instanceof Account)){
            return false;
        }
        Account o = (Account) other;
        return o.getUsername().equals(username);
    }
/*
public static Account createAccount(String a) {
    return null;
}*/
public static String createFile(Account a) {
    String s=a.getName()+"//";
    s+=a.getUsername()+"//";
    s+=a.getPassword()+"//";
    s+=a.getEmail()+"//";
    s+=a.getPhoneNumber()+"//";
    s+=a.getMajor()+"//";
    s+=a.getPreferences().toString()+"//";
    s+=a.getCourses()+"//";
    s+=a.getYear()+"//"+a.getMatched().toString()+"//"+a.getPending().toString()+"//"+a.getConfirmed().toString()+"//"+a.getRejected().toString()+"\n";
    return s;
}
public void printAll(){
    Account a=this;
    System.out.println(a.getEmail()); 
    System.out.println(a.getMajor());
    System.out.println(a.getName());
    System.out.println(a.getPassword());
    System.out.println(a.getPhoneNumber());
    System.out.println(a.getUsername());
    System.out.println(a.getYear());
    
    
    System.out.println(a.getCourses());
    System.out.println(a.getPreferences());
    System.out.println(a.getConfirmed()); 
        
    System.out.println(a.getPending()); 
    
    System.out.println(a.getRejected()); 
    
    
    System.out.println(a.getMatched());  
    
}
}
/*
public void insert(ArrayList<Account> ,int x){
    for (int i = 0; i < size(); i++) {
        // if the element you are looking at is smaller than x, 
        // go to the next element
        if (get(i) < x) continue;
        // if the element equals x, return, because we don't add duplicates
        if (get(i) == x) return;
        // otherwise, we have found the location to add x
        add(i, x);
        return;
    }
    // we looked through all of the elements, and they were all
    // smaller than x, so we add ax to the end of the list
    add(x);
}*/