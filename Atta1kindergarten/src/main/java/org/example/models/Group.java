// Group.java
package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private int number;
    private List<Child> children;

    public Group(String name, int number) {
        this.name = name;
        this.number = number;
        this.children = new ArrayList<>();
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

    // Getters and Setters
    // toString() method
    // methods for adding/removing/editing children in the group
}