package com.team5.qu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class MatchingAlgorithm
{
    // I hate java
    private static int matchRating = 0;

    /**
     * Finds matches for a requesting user.
     *
     * @param accounts  All the other accounts stored in the system
     * @param requestingUser  The user who wants to find suitable matches
     * @return  A priority queue of all the users that the requesting user matched with, sorted by how closely they matched
     */
    static PriorityQueue<Account> matchUsers(ArrayList<Account> accounts, Account requestingUser)
    {
        // Need to implement a silly comparator to enqueue accounts based on how well they matched up
        // The account that had a higher match rating will be higher in the list, and so on
        Comparator<Account> accountComparator = new Comparator<Account>()
        {
            @Override
            public int compare(Account a1, Account a2)
            {
                return matchRating;
            }
        };
        AccountComparator actualAccountComparator = new AccountComparator();
        PriorityQueue<Account> matchedUsers = new PriorityQueue<Account>(accountComparator);
        // The main for loop to sift through all stored accounts and find matches for the requesting user
        for (Account otherAccount : accounts)
        {
            // Check if the user already accepted or rejected the other user. If they have, skip them
            int pendingCheck = Collections.binarySearch(requestingUser.getPending(), otherAccount, actualAccountComparator);
            int confirmCheck = Collections.binarySearch(requestingUser.getConfirmed(), otherAccount, actualAccountComparator);
            int rejectCheck = Collections.binarySearch(requestingUser.getRejected(), otherAccount.getUsername());
            boolean notInArray = (pendingCheck < 0) && (confirmCheck < 0) && (rejectCheck < 0);
            // Don't let the user match with themselves
            if ((!otherAccount.getUsername().equals(requestingUser.getUsername())) && notInArray)
            {
                matchRating = 0;
                // Increase rating based on number of shared courses
                for (String course : requestingUser.getCourses())
                {
                    for (String otherCourse : otherAccount.getCourses())
                    {
                        if (course.equals(otherCourse))
                            matchRating += 1;
                    }
                }
                boolean hasMutualTimes = false;
                // Only continue with the loop if the two accounts have shared courses. If they don't share any courses,
                // They will not match.
                if (matchRating != 0)
                {
                    if (otherAccount.getMajor().equals(requestingUser.getMajor()))
                    {
                        matchRating += 3;
                    }
                    int i;
                    ArrayList<Preference> requestPreference = requestingUser.getPreferences();
                    ArrayList<Preference> otherPreferences = otherAccount.getPreferences();
                    // Main for loop to compare preferences.
                    for (i = 0; i < requestPreference.size(); i++)
                    {
                        switch (i)
                        {
                        /*
                            Preferences[0] is the gender
                            Preferences[2] is the location to meet
                            Preferences[3] is the study technique
                            These 4 preferences are a simple character, so they can all be processed the same way.
                            Check if the character is the same, and increases match rating if they are.
                            Preferences[1] is the available times, which is a full string and more complex.
                            Splits the string into the available times for each day. Then, for each day,
                            Finds out the number of available times that match up between the two users.
                            If no times match up, the two users will not be matched.
                         */
                            case 0:
                            case 2:
                            case 3:
                                if (requestPreference.get(i).getChosenOptions().equals(otherPreferences.get(i).getChosenOptions()))
                                    matchRating += 1 * ((requestPreference.size()+1)-requestPreference.get(i).getWeight());
                                break;
                            case 1:
                                String[] requestingUserTimes = requestPreference.get(i).getChosenOptions().split(",");
                                String[] otherUserTimes = otherPreferences.get(i).getChosenOptions().split(",");
                                int j;
                                /*
                                    Both the arrays should be of size 7, where each entry is the available times
                                    for each day. This for loop goes through each days and checks if any available times
                                    match up. If they do, increase the rating.
                                    The way it does it is by having an array of size 26, since each available hour
                                    is represented by a character from a-z. A character will increment the element
                                    at the corresponding array index by 1 if it's in the string.
                                    For example, if the string is acde, the array will be [1, 0, 1, 1, 1, 0, 0....].
                                    This happens for both the other user and the requesting user. After that array is
                                    created, compare the two arrays to see if both elements are 1.
                                 */
                                for (j = 0; j < requestingUserTimes.length; j++)
                                {
                                    String requestingUserDay = requestingUserTimes[j];
                                    String otherUserDay = otherUserTimes[j];
                                    int[] findRequestingCommonTimes = new int[4];
                                    int[] findOtherCommonTimes = new int[4];
                                    for (int k = 0; k < requestingUserDay.length(); k++)
                                    {
                                        findRequestingCommonTimes[(requestingUserDay.charAt(k) - '0')]++;
                                    }
                                    for (int k = 0; k < otherUserDay.length(); k++)
                                    {
                                        findOtherCommonTimes[(otherUserDay.charAt(k) - '0')]++;
                                    }
                                    for (int k = 0; k < findRequestingCommonTimes.length; k++)
                                    {
                                        if ((findRequestingCommonTimes[k] == 1) && (findOtherCommonTimes[k] == 1))
                                        {
                                            hasMutualTimes = true;
                                            matchRating += 1 * ((requestPreference.size()+1)-requestPreference.get(i).getWeight());
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
                // If the other user passes an arbitrary match rating and has mutual times, they are added to the matched
                // users list.
                if (matchRating >= 8 && hasMutualTimes)
                {
                    matchedUsers.add(otherAccount);
                }
            }
        }
        return matchedUsers;
    }
}
