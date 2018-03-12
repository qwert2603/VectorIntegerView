package com.qwert2603.vector_integer_view;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class DigitAdapter extends RecyclerView.Adapter<DigitAdapter.DigitViewHolder> {

    @ColorInt
    private final int digitColor;

    private int mInteger;

    DigitAdapter(int digitColor) {
        this.digitColor = digitColor;
    }

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public DigitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viv_item_digit, parent, false);
        ImageView imageView = view.findViewById(R.id.img);
        imageView.setColorFilter(digitColor, PorterDuff.Mode.SRC_ATOP);
        return new DigitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DigitViewHolder holder, int position) {
        holder.setDigit(getDigitAt(mInteger, position));
    }

    @Override
    public int getItemCount() {
        return getDigitCount(mInteger);
    }

    int getInteger() {
        return mInteger;
    }

    void setInteger(final int integer) {
        final int old = mInteger;
        mInteger = integer;
        DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return getDigitCount(old);
            }

            @Override
            public int getNewListSize() {
                return getDigitCount(integer);
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                if (oldItemPosition == 0 && getDigitAt(old, 0) == VectorIntegerView.DIGIT_MINUS) {
                    return getDigitAt(integer, newItemPosition) == VectorIntegerView.DIGIT_MINUS;
                }
                if (newItemPosition == 0 && getDigitAt(integer, 0) == VectorIntegerView.DIGIT_MINUS) {
                    return getDigitAt(old, oldItemPosition) == VectorIntegerView.DIGIT_MINUS;
                }
                return getOldListSize() - oldItemPosition == getNewListSize() - newItemPosition;
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return getDigitAt(old, oldItemPosition) == getDigitAt(integer, newItemPosition);
            }
        }, true).dispatchUpdatesTo(this);
    }

    private static int getDigitCount(int d) {
        return String.valueOf(d).length();
    }

    private static int getDigitAt(int d, int pos) {
        String s = String.valueOf(d);
        char c = s.charAt(pos);
        if (Character.isDigit(c)) return Integer.parseInt(String.valueOf(c));
        if (c == '-') return VectorIntegerView.DIGIT_MINUS;
        throw new IllegalArgumentException();
    }

    private static final int[] ATTRS = {
            R.attr.viv_state_zero,
            R.attr.viv_state_one,
            R.attr.viv_state_two,
            R.attr.viv_state_three,
            R.attr.viv_state_four,
            R.attr.viv_state_five,
            R.attr.viv_state_six,
            R.attr.viv_state_seven,
            R.attr.viv_state_eight,
            R.attr.viv_state_nine,
            R.attr.viv_state_nth,
            R.attr.viv_state_minus,
    };

    static class DigitViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        int d;

        DigitViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img);
        }

        void setDigit(int digit) {
            d = digit;

            int[] state = new int[ATTRS.length];

            for (int i = 0; i < ATTRS.length; i++) {
                if (i == digit) {
                    state[i] = ATTRS[i];
                } else {
                    state[i] = -ATTRS[i];
                }
            }

            mImageView.setImageState(state, true);
        }
    }

}
