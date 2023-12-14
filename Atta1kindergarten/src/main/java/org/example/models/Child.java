// Child.java
package org.example.models;

public class Child {
    private String fullName;
    private String gender;
    private int age;

    public Child( String fullName, String gender, int age) {
        this.fullName = fullName;
        this.gender = gender;
        this.age = age;
    }
    public String getFullName() {
        return fullName;
    }

    public void setAge(int age) {

        this.age = age;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }



}
