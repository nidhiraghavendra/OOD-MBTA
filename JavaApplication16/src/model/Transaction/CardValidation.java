/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Transaction;

import java.io.*;
import java.util.*;

/**
 *
 * @author Nidhi Raghavendra
 */
public class CardValidation {

    HashMap<String, Integer> accounts = new HashMap<String, Integer>();

    public CardValidation() {
        accounts.put("visa", 4);
        accounts.put("mastercard", 5);
        accounts.put("amex", 37);
        accounts.put("discover", 6);
    }

    public boolean intakeNumber(Long number) {
        String numberString = String.valueOf(number);
        int cardNumberSize = getSize(number);

        if (cardNumberSize >= 13 && cardNumberSize <= 16) {
            // match it with the available prefixes

            // get the prefix of the number
            // match the value of the prefix to the required value
            if (prefixMatched(getPrefix(number, 1), accounts.get("visa"))
                    || prefixMatched(getPrefix(number, 1), accounts.get("mastercard"))
                    || prefixMatched(getPrefix(number, 2), accounts.get("amex"))
                    || prefixMatched(getPrefix(number, 1), accounts.get("discover"))) {

                int cardNumberSum = runThrough(cardNumberSize, numberString);

                if (isValid(cardNumberSum)) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } else {

            System.out.println("Invalid number of digits in the given input!");
            return false;
        }
        
    }


    public static int runThrough(int cardNumberSize, String numberString) {
        long even = 0;
        long odd = 0;

        // logic to validate card number
        for (int i = cardNumberSize - 1; i >= 0; i--) {
            if (cardNumberSize % 2 == 0) {
                if (i % 2 == 0) {
                    // even positions

                    int digitValue = 2 * Integer.valueOf(String.valueOf(numberString.charAt(i)));
                    int d = getDigit(digitValue);

                    even += d;
                    even = even * 10;

                    System.out.println(even + " : even position");

                } else {
                    // odd positions
                    odd += Integer.valueOf(String.valueOf(numberString.charAt(i)));
                    odd = odd * 10;

                    System.out.println(odd + " : odd position");

                }
            } else {
                if (i % 2 != 0) {
                    // odd positions

                    int digitValue = 2 * Integer.valueOf(String.valueOf(numberString.charAt(i)));
                    int d = getDigit(digitValue);

                    even += d;
                    even = even * 10;

                    System.out.println(even + " : even position");

                } else {
                    // even positions
                    odd += Integer.valueOf(String.valueOf(numberString.charAt(i)));
                    odd = odd * 10;

                    System.out.println(odd + " : odd position");

                }
            }
        }

        return sumOfDoubleEvenPlace(even) + sumOfOddPlace(odd);

    }

    /**
     * Return true if the card number is valid
     */
    public static boolean isValid(long number) {
        // TODO: write your code here

        if (number % 10 != 0) {
            return false;
        }

        return true;
    }

    /**
     * Get the result from Step 2
     */
    public static int sumOfDoubleEvenPlace(long number) {
        // TODO: write your code here
        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number = number / 10;
        }

        return sum;
    }

    /**
     * Return this number if it is a single digit, otherwise, return the sum of
     * the two digits
     */
    public static int getDigit(int number) {
        // TODO: write your code here

        if (number >= 10) {
            return number % 10 + number / 10;
        }
        return number;
    }

    /**
     * Return sum of odd place digits in number
     */
    public static int sumOfOddPlace(long number) {
        // TODO: write your code here
        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number = number / 10;
        }

        return sum;
    }

    /**
     * Return true if the digit d is a prefix for number
     */
    public static boolean prefixMatched(long number, int d) {
        // TODO: write your code here

        if (!String.valueOf(number).startsWith(String.valueOf(d))) {
            return false;
        }

        return true;
    }

    /**
     * Return the number of digits in d
     */
    public static int getSize(long d) {
        // TODO: write your code here
        int count = 0;

        while (d != 0) {
            count++;
            d = d / 10;
        }

        return count;
    }

    /**
     * Return the first k number of digits from number. If the number of digits
     * in number is less than k, return number.
     */
    public static long getPrefix(long number, int k) {
        // TODO: write your code here

        return Long.valueOf(String.valueOf(number).substring(0, k));
    }
}


