package com.qwert2603.vector_integer_view;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;

import java.util.List;

class DigitItemAnimator extends DefaultItemAnimator {

    private final int animationDuration;

    private static class VHI extends RecyclerView.ItemAnimator.ItemHolderInfo {
        final int d;

        VHI(int d) {
            this.d = d;
        }
    }

    DigitItemAnimator(@NonNull Resources resources) {
        animationDuration = resources.getInteger(R.integer.viv_animation_duration);
    }

    @Override
    public long getMoveDuration() {
        return animationDuration;
    }

    @Override
    public long getAddDuration() {
        return animationDuration;
    }

    @Override
    public long getRemoveDuration() {
        return animationDuration;
    }

    @Override
    public long getChangeDuration() {
        return animationDuration;
    }

    @Override
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
        return true;
    }

    @NonNull
    @Override
    public RecyclerView.ItemAnimator.ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State state,
                                                                               @NonNull RecyclerView.ViewHolder viewHolder,
                                                                               int changeFlags,
                                                                               @NonNull List<Object> payloads) {
        if (changeFlags == FLAG_CHANGED) {
            return new VHI(((DigitAdapter.DigitViewHolder) viewHolder).d);
        }
        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads);
    }

    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder,
                                 @NonNull final RecyclerView.ViewHolder newHolder,
                                 @NonNull RecyclerView.ItemAnimator.ItemHolderInfo preInfo,
                                 @NonNull RecyclerView.ItemAnimator.ItemHolderInfo postInfo) {
        if (preInfo instanceof VHI) {
            final DigitAdapter.DigitViewHolder digitViewHolder = (DigitAdapter.DigitViewHolder) newHolder;
            int a = digitViewHolder.d;
            digitViewHolder.setDigit(((VHI) preInfo).d);
            digitViewHolder.setDigit(a);
            newHolder.itemView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dispatchAnimationFinished(newHolder);
                }
            }, animationDuration);
            return false;
        }
        return super.animateChange(oldHolder, newHolder, preInfo, postInfo);
    }

    @Override
    public boolean animateAdd(final RecyclerView.ViewHolder holder) {
        final DigitAdapter.DigitViewHolder digitViewHolder = (DigitAdapter.DigitViewHolder) holder;
        int a = digitViewHolder.d;
        digitViewHolder.setDigit(VectorIntegerView.DIGIT_NTH);
        digitViewHolder.setDigit(a);
        holder.itemView.postDelayed(new Runnable() {
            @Override
            public void run() {
                dispatchAddFinished(holder);
            }
        }, animationDuration);
        return false;
    }

    @Override
    public boolean animateRemove(final RecyclerView.ViewHolder holder) {
        ((DigitAdapter.DigitViewHolder) holder).setDigit(VectorIntegerView.DIGIT_NTH);
        holder.itemView.postDelayed(new Runnable() {
            @Override
            public void run() {
                dispatchRemoveFinished(holder);
            }
        }, animationDuration);
        return false;
    }
}
