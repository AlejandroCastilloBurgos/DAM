package com.example.todosejercicios.ut01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todosejercicios.R;

public class Utils {
    private static final String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";

    public static boolean isValidDNI(String dni) {
        if (dni == null || dni.length() != 9) {
            return false;
        }

        try {
            int number = Integer.parseInt(dni.substring(0, 8));
            char expectedChar = dniChars.charAt(number % 23);
            char actualChar = dni.charAt(8);

            return actualChar == expectedChar;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
