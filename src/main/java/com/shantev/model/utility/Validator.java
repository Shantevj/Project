package com.shantev.model.utility;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validateLoginData(String login, String password) {
        boolean isLoginMeetRequirements = Validator.validateEmail(login);
        boolean isPasswordMeetRequirements = Validator.validatePassword(password);
        return isLoginMeetRequirements && isPasswordMeetRequirements;
    }
    public static boolean validateSignupData(String firstName, String lastName, String login, String password1, String password2) {
        boolean isFirstNameEmpty = firstName.isBlank();
        boolean isLastNameEmpty = lastName.isBlank();
        boolean isLoginMeetRequirements = Validator.validateEmail(login);
        boolean isPasswordMeetRequirements = Validator.validatePassword(password1);
        boolean isPasswordEquals = password1.equals(password2);
        return !isFirstNameEmpty && !isLastNameEmpty && isPasswordEquals && isLoginMeetRequirements && isPasswordMeetRequirements;
    }
    public static boolean validateEventInfo(String theme, String address, Timestamp date) {
        boolean isThemeEmpty = theme.isBlank();
        boolean isAddressEmpty = address.isBlank();
        boolean isDateCorrect = date.after(new Timestamp(new Date().getTime()));
        return !isThemeEmpty && !isAddressEmpty && isDateCorrect;
    }
    public static boolean validateEmail(String email) {
        String emailPatternString = "^[\\w-]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailPatternString);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean validatePassword(String password) {
        String passwordPatternString = ".{1,100}$";
        Pattern pattern = Pattern.compile(passwordPatternString);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
}
