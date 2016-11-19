package com.example.adnan.panagraphspractice;

import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by adnan on 5/17/2016.
 */
public class Utils {
    public static ArrayList<Integer> intList;
    public static ArrayList<String> StringList;

    public static void getData(Comm listener) {
        listener.sucess(StringList, intList);

    }

    public static void getLists(ArrayList<String> stringArrayList, ArrayList<Integer> integerArrayList) {
        intList = integerArrayList;
        StringList = stringArrayList;
    }
}
