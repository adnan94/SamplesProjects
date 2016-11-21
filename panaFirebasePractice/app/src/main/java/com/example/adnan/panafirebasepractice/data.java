package com.example.adnan.panafirebasepractice;

/**
 * Created by Adnan on 10/13/2015.
 */
public class data {
    public data(String text) {
        this.text = text;
    }

    public data() {
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
}
