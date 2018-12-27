package com.qwert2603.vector_integer_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.math.BigInteger;

/**
 * Integer drawables are taken from https://github.com/alexjlockwood/adp-delightful-details
 */
public class VectorIntegerView extends FrameLayout {

    private static final String DIGIT_KEY = BuildConfig.APPLICATION_ID + ".DIGIT_KEY";
    private static final String SUPER_STATE_KEY = BuildConfig.APPLICATION_ID + ".SUPER_STATE_KEY";

    static final int DIGIT_NTH = 10;
    static final int DIGIT_MINUS = 11;
    static final int MAX_DIGIT = DIGIT_MINUS;

    private final DigitAdapter mDigitAdapter;

    public VectorIntegerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.viv_vector_digit_view, this, true);
        RecyclerView recyclerView = view.findViewById(R.id.viv_RecyclerView);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VectorIntegerView);
        int digit = typedArray.getInteger(R.styleable.VectorIntegerView_viv_vector_integer, 0);
        int defaultDigitColor = ResourcesCompat.getColor(getResources(), R.color.viv_digit_color_default, null);
        int digitColor = typedArray.getColor(R.styleable.VectorIntegerView_viv_digit_color, defaultDigitColor);
        typedArray.recycle();

        mDigitAdapter = new DigitAdapter();
        mDigitAdapter.setDigitColor(digitColor);
        recyclerView.setAdapter(mDigitAdapter);
        recyclerView.setItemAnimator(new DigitItemAnimator(getResources()));
        setInteger(digit, false);
    }

    public void setInteger(long digit, boolean animate) {
        setInteger(BigInteger.valueOf(digit), animate);
    }

    public void setInteger(@NonNull BigInteger digit, boolean animate) {
        mDigitAdapter.setInteger(digit);
        if (!animate) {
            mDigitAdapter.notifyDataSetChanged();
        }
    }

    @NonNull
    public BigInteger getInteger() {
        return mDigitAdapter.getInteger();
    }

    @ColorInt
    public int getDigitColor() {
        return mDigitAdapter.getDigitColor();
    }

    public void setDigitColor(@ColorInt int digitColor) {
        mDigitAdapter.setDigitColor(digitColor);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(SUPER_STATE_KEY, super.onSaveInstanceState());
        bundle.putSerializable(DIGIT_KEY, getInteger());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            final BigInteger bigInteger = (BigInteger) bundle.getSerializable(DIGIT_KEY);
            setInteger(bigInteger != null ? bigInteger : BigInteger.ZERO, false);
            state = bundle.getParcelable(SUPER_STATE_KEY);
        }
        super.onRestoreInstanceState(state);
    }
}
