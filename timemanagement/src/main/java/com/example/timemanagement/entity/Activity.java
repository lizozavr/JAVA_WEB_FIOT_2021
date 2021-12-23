package com.example.timemanagement.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Activity {
    private int id;

    private String name;

    private String category;

    public Activity(){}

    public Activity(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }
}
