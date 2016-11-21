package com.example.adnan.panarecyclerview;

/**
 * Created by adnan on 10/17/16.
 */
public interface ItemTouchHelperAdaptor {

        boolean onItemMove(int fromPosition, int toPosition);
        void onItemDismiss(int position);


}
