
package com.example.adnan.panagraphspractice;

import android.support.v4.app.FragmentActivity;


/**
 * Baseclass of all Activities of the Demo Application.
 * 
 * @author Philipp Jahoda
 */
public abstract class DemoBase extends FragmentActivity {

    public static String[] mMonths = new String[] {
            "U S A", "Hungary ", "Canada", "Poland", "Europian Union", "Mexico", "Taiwan", "Russia", "Brazil", "South Africa"
    };

    public static String[] mParties = new String[] {
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
