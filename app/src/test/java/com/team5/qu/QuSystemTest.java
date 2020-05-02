package com.team5.qu;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class QuSystemTest {

    Account testAccount;

    // Set up the test account
    @Before
    public void setUp() throws Exception {
        String[] a1courses = {"CS 112", "CS 101", "CS 330", "CS 211"};
        Preference[] a1prefs =
                {new Preference(1.0, "gender", ""), new Preference(4.0, "available times", ""),
                        new Preference(2.0, "", ""), new Preference(3.0, "study techniques", "")};
        ArrayList<Preference> a1prefArrList = new ArrayList<>(a1prefs.length);
        Collections.addAll(a1prefArrList, a1prefs);
        ArrayList<String> a1coursesArrList = new ArrayList<>(a1courses.length);
        Collections.addAll(a1coursesArrList, a1courses);
        testAccount = new Account("TestName1", "TestUsername1", "testemail@test com", "99999", "CS", a1prefArrList, a1coursesArrList, 2);
        testAccount.setPassword("1234");
        QuSystem.addAccount(testAccount);
    }

    // Ensure the account is taken
    @Test
    public void checkAvailableAccountTest()
    {
        assertFalse(QuSystem.checkAvailableAccount("TestUsername1"));
    }

    // Ensure the system can get preferences from its accounts
    @Test
    public void getPreferencesFromUsersTest()
    {
        assertEquals(testAccount.getPreferences(), QuSystem.getPreferencesFromUsers().get(0));
    }

    // Ensure the system can get dummy accounts with no password
    @Test
    public void getDummyAccountsTest()
    {
        Account dummyTest = QuSystem.getDummyAccounts().get(0);
        assertEquals(testAccount.getUsername(), dummyTest.getUsername());
        assertNotEquals(testAccount.getPassword(), dummyTest.getPassword());
    }

    // Ensure the system can convert usernames to accounts
    @Test
    public void getAccountFromUsernameTest()
    {
        assertEquals(testAccount, QuSystem.getAccountFromUsername("TestUsername1"));
    }
}