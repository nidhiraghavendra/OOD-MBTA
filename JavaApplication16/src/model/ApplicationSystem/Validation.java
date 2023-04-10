/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ApplicationSystem;

import java.util.regex.Pattern;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Validation {

    String name;
    String email;
    String location;
    String address;
    String username;
    String password;
    int phone;
    int zipcode;
    Pattern p;

    public boolean validateName(String name) {
        String nameValidate = "[A-Za-z][A-Za-z\\s]+";
        p = Pattern.compile(nameValidate);

        if (!p.matcher(name).matches()) {

            return true;
        }

        return false;

    }

    public boolean validateEmail(String email) {
        String emailvalidate = "[a-z0-9!-_.&#*]{2,100}[@][a-z]{3,100}[.][a-z]{3}";
        p = Pattern.compile(emailvalidate);

        if (!p.matcher(email)
                .matches()) {

            return true;
        }
        return false;
    }

    public boolean valdiateAddress(String address) {
        String addressValidate = "[0-9]{1,3}\\s[A-Za-z]{1,30}\\s[A-Za-z]{1,30}\\s[A-Za-z]{1,30}";
        p = Pattern.compile(addressValidate);

        if (!p.matcher(address)
                .matches()) {

            return true;
        }
        return false;
    }

    public boolean validateUsername(String username) {
        String uservalidate = "[a-zA-Z0-9]{4,100}";
        p = Pattern.compile(uservalidate);

        if (!p.matcher(username)
                .matches()) {

            return true;
        }
        return false;
    }

    public boolean validatePassword(String password) {
        String passvalidate = "[a-zA-Z0-9!@_*$#%&^()-]{4,100}";
        p = Pattern.compile(passvalidate);

        if (!p.matcher(password)
                .matches()) {

            return true;
        }
        return false;
    }

    public boolean validateLocation(String location) {
        String locvalidate = "[a-zA-Z]{1,100}";
        p = Pattern.compile(locvalidate);

        if (!p.matcher(location)
                .matches()) {

            return true;
        }
        return false;
    }

    public boolean validateVIN(String vin) {
        String locvalidate = "[a-zA-Z0-9]{10}";
        p = Pattern.compile(locvalidate);

        if (!p.matcher(vin)
                .matches()) {

            return true;
        }
        return false;
    }

    public boolean validateDL(String location) {
        String locvalidate = "[A-Z][A-Z0-9]{6}";
        p = Pattern.compile(locvalidate);

        if (!p.matcher(location)
                .matches()) {

            return true;
        }
        return false;
    }

    public boolean validatePlate(String location) {
        String locvalidate = "[A-Z]{2}[0-9]{4}";
        p = Pattern.compile(locvalidate);

        if (!p.matcher(location)
                .matches()) {

            return true;
        }
        return false;
    }

    public boolean validatePhone(int phone) {
        if (String.valueOf(phone)
                .length() != 10) {

            return true;
        }
        return false;
    }

    public boolean validateZipcode(String zipcode) {
        if (String.valueOf(zipcode)
                .length() < 5 || String.valueOf(zipcode)
                        .length() >= 6) {

            return true;
        }

        return false;
    }

    public boolean validateMonth(String mm) {
        if (Pattern.matches("^(0?[1-9]|1[012])$", mm)) {

            return true;
        }

        return false;
    }

    public boolean validateYear(String y) {
        if (Pattern.matches("[2][4-9]", y)) {
            return true;
        }

        return false;
    }

    public boolean validateCVV(String y) {
        if (Pattern.matches("[1-9]{3}", y)) {
            return true;
        }

        return false;
    }
}
