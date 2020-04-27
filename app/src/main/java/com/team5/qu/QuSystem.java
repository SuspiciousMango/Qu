package com.team5.qu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class QuSystem
{
    //Acc
    private static ArrayList<Account> accounts = new ArrayList<>();
    private static AccountComparator accountComparator = new AccountComparator();
    //parent directory for all files
    //private static File parentDir;

    /**
     * Sets the parent directory, to store the file
     * @param parentDir  the parent directory value
     */
    //public static void setParentDir(File parentDir)
    //{
    //    QuSystem.parentDir = parentDir;
    //}


    /**
     * Adds an account to the system, done when an account is first created
     * @param newAccount  The new account to be added
     */
    public static void addAccount(Account newAccount)
    {
        int x= Collections.binarySearch(accounts, newAccount, accountComparator);
        if(x<0)
        {
            accounts.add((x+1)*-1,newAccount);
        }
    }

    /**
     * Checks if a username is available to take.
     * @param username  The username to check
     * @return  True if the username is available, false if not.
     */
    public static boolean checkAvailableAccount(String username)
    {
        return getAccountFromUsername(username) == null;
    }

    /**
     * Gets the preferences from all the stored accounts
     * @return  An ArrayList of ArrayList of type Preference, since each account's preferences are stored in an ArrayList.
     */
    public static ArrayList<ArrayList<Preference>> getPreferencesFromUsers()
    {
        ArrayList<ArrayList<Preference>> preferences = new ArrayList<>();
        for (Account a : accounts)
        {
            preferences.add(a.getPreferences());
        }
        return preferences;
    }

    /**
     * Returns an array of dummy accounts made from the original accounts.
     * Dummy accounts do not store password or other sensitive info for security purposes.
     * @return  The ArrayList of dummy accounts
     */
    public static ArrayList<Account> getDummyAccounts()
    {
        ArrayList<Account> dummyAccount = new ArrayList<Account>();
        for (Account a : accounts)
        {
            dummyAccount.add(a.createDummyAccount());
        }
        return dummyAccount;
    }

    /**
     * Gets an account from a username
     * @param username  the username to find
     * @return  The dummy account associated with that username, or null if none is found.
     */
    public static Account getAccountFromUsername(String username)
    {
        Account match = new Account("", username, "","","",new ArrayList<Preference>(),new ArrayList<String>(),4);
        int indexOfAccount = Collections.binarySearch(accounts, match, accountComparator);
        if (indexOfAccount >= 0)
        {
            return accounts.get(indexOfAccount);
        }
        return null;
    }
    /**
     * Finds matches for the requesting user.
     * @param requestingUser  The user who is requesting matches
     * @return  A priority queue of matches, sorted by how closely they match.
     */
    public static PriorityQueue<Account> matchUsers(Account requestingUser)
    {
        return MatchingAlgorithm.matchUsers(getDummyAccounts(), requestingUser);
    }

    /**
     * Informs two users that they have a match.
     * First asks the two users if they accept the match, and if they do, asks them to both confirm that they've matched.
     * If either user rejects the match, the system informs both users that the match was rejected/failed.
     * @param User1  The first matched user
     * @param User2  The second matched user
     */
    public static void informUserMatch(Account User1, Account User2)
    {
        if (User1.acceptAccount(User2) && User2.acceptAccount(User2))
        {
            User1.confirmMatch(User2);
            User2.confirmMatch(User1);
        }
        else
        {
            User1.rejectAccount(User2);
            User2.rejectAccount(User1);
        }
    }

    /**
     * Writes all the accounts stored into a file, for persistent storage
     * @param f the file to write to
     */
    public static void writeAccountsToFile(FileOutputStream f)
    {
        try{
        OutputStreamWriter accountWriter = new OutputStreamWriter(f);
        for (Account a : accounts)
        {
            accountWriter.write(Account.createFile(a));
        }
            accountWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Test");
        }
    }

    /**
     * Given a file, creates an arraylist of accounts.
     * @param f the file to read from
     */
    public static void readAccountsFromFile(FileInputStream f)
    {
        try
        {
            BufferedReader accountReader = new BufferedReader(new InputStreamReader(f));
            String accountLine = accountReader.readLine();
            while (!accountLine.equals(""))
            {
                Account a = new Account(accountLine);
                addAccount(a);
                accountLine = accountReader.readLine();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
