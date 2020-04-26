package com.team5.qu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class System
{
    //Acc
    private ArrayList<Account> accounts;

    /**
     * Initializes the system by initializing the accounts array
     */
    public System()
    {
        accounts = new ArrayList<Account>();
    }

    /**
     * Adds an account to the system, done when an account is first created
     * @param newAccount  The new account to be added
     */
    public void addAccount(Account newAccount)
    {
        accounts.add(newAccount);
    }

    /**
     * Checks if a username is available to take.
     * @param username  The username to check
     * @return  True if the username is available, false if not.
     */
    public boolean checkAvailableAccount(String username)
    {
        for (Account a : accounts)
        {
            if (a.getUsername().equals(username))
                return false;
        }
        return true;
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
        Account match = null;
        for (Account a : accounts)
        {
            if (a.getUsername().equals(username))
            {
                match = a.createDummyAccount();
                break;
            }
        }
        return match;
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
    public void writeAccountsToFile() throws IOException
    {
        FileWriter accountsFile = new FileWriter("accounts.txt");
        for (Account a : accounts)
        {
            accountsFile.write(Account.createFile(a));
        }
        try
        {
            accountsFile.close();
        }
        catch (IOException e)
        {
        }
    }
}
