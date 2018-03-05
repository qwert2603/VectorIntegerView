package com.qwert2603.vector_integer_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import java.util.List;

class DigitItemAnimator extends DefaultItemAnimator {
    private class VHI extends RecyclerView.ItemAnimator.ItemHolderInfo {
        int d;

        VHI(int d) {
            this.d = d;
        }
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
            int a = ((DigitAdapter.DigitViewHolder) newHolder).d;
            ((DigitAdapter.DigitViewHolder) newHolder).setDigit(((VHI) preInfo).d);
            ((DigitAdapter.DigitViewHolder) newHolder).setDigit(a);
            newHolder.itemView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dispatchAnimationFinished(newHolder);
                }
            }, 400);
            return false;
        }
        return super.animateChange(oldHolder, newHolder, preInfo, postInfo);
    }

    @Override
    public boolean animateAdd(final RecyclerView.ViewHolder holder) {
        int a = ((DigitAdapter.DigitViewHolder) holder).d;
        ((DigitAdapter.DigitViewHolder) holder).setDigit(VectorIntegerView.DIGIT_NTH);
        ((DigitAdapter.DigitViewHolder) holder).setDigit(a);
        holder.itemView.postDelayed(new Runnable() {
            @Override
            public void run() {
                dispatchAddFinished(holder);
            }
        }, 400);
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
        }, 400);
        return false;
    }
}
