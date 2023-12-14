package org.example.models;

import java.util.List;

public class Group {
    private final int id;
    private String name;
    private int number;
    private List<Child> children;

    public Group(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public List<Child> getChildren() {
        return children;
    }

    public final int getId() {

        return 0;
    }
}