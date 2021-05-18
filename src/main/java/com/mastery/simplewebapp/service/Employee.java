package com.mastery.simplewebapp.service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Employee {

    private long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String firstName;

    private Gender gender;

    public Employee() {
    }

    public Employee(long id, String firstName, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
