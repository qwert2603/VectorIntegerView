package com.qwert2603.vector_integer_view;

import android.support.annotation.NonNull;

import java.math.BigInteger;

class Utils {
    static int getDigitCount(@NonNull BigInteger d) {
        return String.valueOf(d).length();
    }

    static int getDigitAt(@NonNull BigInteger d, int pos) {
        String s = String.valueOf(d);
        char c = s.charAt(pos);
        if (Character.isDigit(c)) return Integer.parseInt(String.valueOf(c));
        if (c == '-') return VectorIntegerView.DIGIT_MINUS;
        throw new IllegalArgumentException();
    }
}
