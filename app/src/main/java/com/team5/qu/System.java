package com.team5.qu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class System
{
    //Acc
    private ArrayList<Account> accounts;
    private AccountComparator accountComparator = new AccountComparator();
    //parent directory for all files
    private File parentDir;

    /**
     * Initializes the system by initializing the accounts array
     */
    public System(File dataLoc)
    {
        accounts = new ArrayList<Account>();
        parentDir = dataLoc;
        //readAccountsFromFile();
    }

    /**
     * Adds an account to the system, done when an account is first created
     * @param newAccount  The new account to be added
     */
    public void addAccount(Account newAccount)
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
    public boolean checkAvailableAccount(String username)
    {
        return getAccountFromUsername(username) == null;
    }

    /**
     * Gets the preferences from all the stored accounts
     * @return  An ArrayList of ArrayList of type Preference, since each account's preferences are stored in an ArrayList.
     */
    public ArrayList<ArrayList<Preference>> getPreferencesFromUsers()
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
    public ArrayList<Account> getDummyAccounts()
    {
        ArrayList<Account> dummyAccount = new ArrayList<Account>();
        for (Account a : accounts)
        {
            dummyAccount.add(a.createDummyAccount());
        }
        return dummyAccount;
    }

    /**
     * Gets a dummy account from a username
     * @param username  the username to find
     * @return  The dummy account associated with that username, or null if none is found.
     */
    public Account getAccountFromUsername(String username)
    {
        Account match = new Account("", username, "","","","",new ArrayList<String>(),4);
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
    public PriorityQueue<Account> matchUsers(Account requestingUser)
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
    public void informUserMatch(Account User1, Account User2)
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
     */
    public void writeAccountsToFile()
    {
        try{
        File accountsFile = new File(parentDir, "accounts.txt");
        if(!accountsFile.exists()){
            accountsFile.delete();
        }
        FileWriter accountWriter = new FileWriter(accountsFile);
        for (Account a : accounts)
        {
            accountWriter.write(Account.createFile(a));
        }
            accountWriter.close();
        }
        catch (IOException e)
        {
        }
    }
}
