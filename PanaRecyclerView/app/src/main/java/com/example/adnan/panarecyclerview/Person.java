package com.example.adnan.panarecyclerview;

import java.util.List;

/**
 * Created by adnan on 4/9/2016.
 */
public class Person {
    String name;
    String age;
    int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Person(String name, String age, int image) {
        this.name = name;
        this.age = age;
        this.image = image;
    }

}
