package com.qwert2603.vector_integer_view;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void getDigitCount() {
        assertEquals(1, Utils.getDigitCount(BigInteger.valueOf(0)));
        assertEquals(1, Utils.getDigitCount(BigInteger.valueOf(1)));
        assertEquals(2, Utils.getDigitCount(BigInteger.valueOf(10)));
        assertEquals(2, Utils.getDigitCount(BigInteger.valueOf(42)));
        assertEquals(2, Utils.getDigitCount(BigInteger.valueOf(99)));
        assertEquals(3, Utils.getDigitCount(BigInteger.valueOf(100)));
        assertEquals(3, Utils.getDigitCount(BigInteger.valueOf(853)));
        assertEquals(14, Utils.getDigitCount(BigInteger.valueOf(45269739476345L)));
        assertEquals(2, Utils.getDigitCount(BigInteger.valueOf(-1)));
        assertEquals(2, Utils.getDigitCount(BigInteger.valueOf(-8)));
        assertEquals(3, Utils.getDigitCount(BigInteger.valueOf(-18)));
        assertEquals(9, Utils.getDigitCount(BigInteger.valueOf(-67645487)));
    }

    @Test
    public void getDigitAt() {
        assertEquals(0, Utils.getDigitAt(BigInteger.valueOf(0), 0));
        assertEquals(3, Utils.getDigitAt(BigInteger.valueOf(3), 0));
        assertEquals(2, Utils.getDigitAt(BigInteger.valueOf(2918), 0));
        assertEquals(9, Utils.getDigitAt(BigInteger.valueOf(2918), 1));
        assertEquals(1, Utils.getDigitAt(BigInteger.valueOf(2918), 2));
        assertEquals(VectorIntegerView.DIGIT_MINUS, Utils.getDigitAt(BigInteger.valueOf(-2918), 0));
        assertEquals(2, Utils.getDigitAt(BigInteger.valueOf(-2918), 1));
        assertEquals(9, Utils.getDigitAt(BigInteger.valueOf(-2918), 2));
        assertEquals(1, Utils.getDigitAt(BigInteger.valueOf(-10000), 1));
        assertEquals(0, Utils.getDigitAt(BigInteger.valueOf(-10000), 2));
        assertEquals(7, Utils.getDigitAt(BigInteger.valueOf(-10070), 4));
    }
}