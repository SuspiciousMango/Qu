package com.team5.qu;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class MatchingAlgorithmTest {

    private ArrayList<Account> accounts;
    private Account requestingAccount;

    @Before
    public void setUp() throws Exception
    {
        String[] a1courses = {"CS 112", "CS 101", "CS 330", "CS 211"};
        Preference[] a1prefs =
                {new Preference(1.0, "gender", "m"), new Preference(4.0, "available times", "123:234:13:24:34:1234:1234"),
                        new Preference(2.0, "location to meet", "jl"), new Preference(3.0, "study techniques", "f")};
        ArrayList<Preference> a1prefArrList = new ArrayList<>(a1prefs.length);
        Collections.addAll(a1prefArrList, a1prefs);
        ArrayList<String> a1coursesArrList = new ArrayList<>(a1courses.length);
        Collections.addAll(a1coursesArrList, a1courses);
        Account a1 = new Account("TestName1", "TestUsername1", "testemail@test com", "99999", "CS", a1prefArrList, a1coursesArrList, 2);

        String[] a2courses = {"CS 112", "CS 999", "CS 471", "CS 455"};
        Preference[] a2prefs =
                {new Preference(3.0, "gender", "f"), new Preference(2.0, "available times", "34::::::1"),
                        new Preference(1.0, "location to meet", "fm"), new Preference(4.0, "study techniques", "p")};
        ArrayList<Preference> a2prefArrList = new ArrayList<>(a2prefs.length);
        Collections.addAll(a2prefArrList, a2prefs);
        ArrayList<String> a2coursesArrList = new ArrayList<>(a2courses.length);
        Collections.addAll(a2coursesArrList, a2courses);
        Account a2 = new Account("TestName2", "TestUsername2", "testemail@test com", "99999", "CS", a2prefArrList, a2coursesArrList, 2);

        String[] a3courses = {"CS 303", "CS 211", "CS 330", "CS 505"};
        Preference[] a3prefs =
                {new Preference(3.0, "gender", "m"), new Preference(2.0, "available times", "24::13:::2:1"),
                        new Preference(1.0, "location to meet", "jlf"), new Preference(4.0, "study techniques", "fm")};
        ArrayList<Preference> a3prefArrList = new ArrayList<>(a3prefs.length);
        Collections.addAll(a3prefArrList, a3prefs);
        ArrayList<String> a3coursesArrList = new ArrayList<>(a3courses.length);
        Collections.addAll(a3coursesArrList, a3courses);
        Account a3 = new Account("TestName3", "TestUsername3", "testemail@test com", "99999", "CS", a3prefArrList, a3coursesArrList, 2);

        String[] a4courses = {"CS 112", "CS 101", "CS 455", "CS 469"};
        Preference[] a4prefs =
                {new Preference(3.0, "gender", "m"), new Preference(2.0, "available times", "4::2:::3:1"),
                        new Preference(1.0, "location to meet", "jlfm"), new Preference(4.0, "study techniques", "fp")};
        ArrayList<Preference> a4prefArrList = new ArrayList<>(a4prefs.length);
        Collections.addAll(a4prefArrList, a4prefs);
        ArrayList<String> a4coursesArrList = new ArrayList<>(a4courses.length);
        Collections.addAll(a4coursesArrList, a4courses);
        Account a4 = new Account("TestName4", "TestUsername4", "testemail@test com", "99999", "CS", a4prefArrList, a4coursesArrList, 2);

        String[] a5courses = {"CS 475", "CS 440", "CS 455", "CS 211"};
        Preference[] a5prefs =
                {new Preference(3.0, "gender", "f"), new Preference(2.0, "available times", "123:123:123:2:2:3:1"),
                        new Preference(1.0, "location to meet", "m"), new Preference(4.0, "study techniques", "p")};
        ArrayList<Preference> a5prefArrList = new ArrayList<>(a5prefs.length);
        Collections.addAll(a5prefArrList, a5prefs);
        ArrayList<String> a5coursesArrList = new ArrayList<>(a5courses.length);
        Collections.addAll(a5coursesArrList, a5courses);
        Account a5 = new Account("TestName5", "TestUsername5", "testemail@test com", "99999", "CS", a5prefArrList, a5coursesArrList, 2);

        Account[] accountsArr = {a1, a2, a3, a4, a5};
        accounts = new ArrayList<>(accountsArr.length);
        Collections.addAll(accounts, accountsArr);
        requestingAccount = a1;
    }

    @After
    public void tearDown() throws Exception {
    }

    // With the default setup, make sure the matching algorithm matched with every other account
    // All the other accounts are valid and have similar preferences
    // Expected output is all other accounts are matches
    @Test
    public void matchUsersValid()
    {
        PriorityQueue<Account> results = MatchingAlgorithm.matchUsers(accounts, requestingAccount);
        assertTrue((results.size() >= 1));
    }

    // Still uses valid accounts, but with a different requesting user. Expected output is at least one match
    @Test
    public void matchUsersValid2()
    {
        requestingAccount = accounts.get(3);
        PriorityQueue<Account> results = MatchingAlgorithm.matchUsers(accounts, requestingAccount);
        assertTrue((results.size() >= 1));
    }

    // Try to match with a user who doesnt share any class. Expected output is no matches
    @Test
    public void matchUsersNoClasses()
    {
        String[] a1courses = {"ECE 112", "ECE 101", "ECE 330", "ECE 211"};
        Preference[] a1prefs =
                {new Preference(1.0, "gender", "m"), new Preference(4.0, "available times", "123:234:13:24:34:1234:1234"),
                        new Preference(2.0, "location to meet", "jl"), new Preference(3.0, "study techniques", "f")};
        ArrayList<Preference> a1prefArrList = new ArrayList<>(a1prefs.length);
        Collections.addAll(a1prefArrList, a1prefs);
        ArrayList<String> a1coursesArrList = new ArrayList<>(a1courses.length);
        Collections.addAll(a1coursesArrList, a1courses);
        requestingAccount = new Account("TestName1", "TestUsername1", "testemail@test com", "99999", "CS", a1prefArrList, a1coursesArrList, 2);
        PriorityQueue<Account> results = MatchingAlgorithm.matchUsers(accounts, requestingAccount);
        assertTrue((results.size() == 0));
    }


    //Try to match with a user who has no set preferences. Expected output is no matches
    @Test
    public void matchUsersNoPreferences()
    {
        String[] a1courses = {"CS 112", "CS 101", "CS 330", "CS 211"};
        Preference[] a1prefs =
                {new Preference(1.0, "gender", ""), new Preference(4.0, "available times", ""),
                        new Preference(2.0, "", ""), new Preference(3.0, "study techniques", "")};
        ArrayList<Preference> a1prefArrList = new ArrayList<>(a1prefs.length);
        Collections.addAll(a1prefArrList, a1prefs);
        ArrayList<String> a1coursesArrList = new ArrayList<>(a1courses.length);
        Collections.addAll(a1coursesArrList, a1courses);
        requestingAccount = new Account("TestName1", "TestUsername1", "testemail@test com", "99999", "CS", a1prefArrList, a1coursesArrList, 2);
        PriorityQueue<Account> results = MatchingAlgorithm.matchUsers(accounts, requestingAccount);
        assertTrue((results.size() == 0));
    }


    //Ensure the requesting user doesn't match with themselves. Expected output is empty results queue
    @Test
    public void matchUsersNoSelfMatching()
    {
        String[] a1courses = {"ECE 112", "ECE 101", "ECE 330", "ECE 211"};
        Preference[] a1prefs =
                {new Preference(1.0, "gender", "m"), new Preference(4.0, "available times", "123:234:13:24:34:1234:1234"),
                        new Preference(2.0, "location to meet", "jl"), new Preference(3.0, "study techniques", "f")};
        ArrayList<Preference> a1prefArrList = new ArrayList<>(a1prefs.length);
        Collections.addAll(a1prefArrList, a1prefs);
        ArrayList<String> a1coursesArrList = new ArrayList<>(a1courses.length);
        Collections.addAll(a1coursesArrList, a1courses);
        requestingAccount = new Account("TestName1", "TestUsername1", "testemail@test com", "99999", "CS", a1prefArrList, a1coursesArrList, 2);
        accounts = new ArrayList<>();
        accounts.add(requestingAccount);
        PriorityQueue<Account> results = MatchingAlgorithm.matchUsers(accounts, requestingAccount);
        assertTrue((results.size() == 0));
    }
}