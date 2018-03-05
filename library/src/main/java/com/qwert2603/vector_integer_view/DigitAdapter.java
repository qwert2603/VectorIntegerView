package com.qwert2603.vector_integer_view;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class DigitAdapter extends RecyclerView.Adapter<DigitAdapter.DigitViewHolder> {

    private int mInteger;

    @NonNull
    @SuppressLint("InflateParams")
    @Override
    public DigitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DigitViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viv_item_digit, parent, false));
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
                return oldItemPosition == newItemPosition;
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return getDigitAt(old, oldItemPosition) == getDigitAt(integer, newItemPosition);
            }
        }, false).dispatchUpdatesTo(this);
    }

    private int getDigitCount(int d) {
        return String.valueOf(d).length();
    }

    private int getDigitAt(int d, int pos) {
        String s = String.valueOf(d);
        char c = s.charAt(s.length() - 1 - pos);
        if (Character.isDigit(c)) {
            return Integer.parseInt("" + c);
        }
        if (c == '-') {
            return VectorIntegerView.DIGIT_MINUS;
        }
        throw new IllegalArgumentException();
    }

    class DigitViewHolder extends RecyclerView.ViewHolder {
        final int[] attrs = {
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

        ImageView mImageView;
        int d;

        DigitViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.img);
        }

        void setDigit(int digit) {
            d = digit;

            int[] state = new int[attrs.length];

            for (int i = 0; i < attrs.length; i++) {
                if (i == digit) {
                    state[i] = attrs[i];
                } else {
                    state[i] = -attrs[i];
                }
            }

            mImageView.setImageState(state, true);
        }
    }

}
