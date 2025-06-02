package com.example.Library_System.User;

import java.util.Date;

public class UserByEmailDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Date registrationDate;

    public UserByEmailDTO(String firstName, Date registrationDate, String email, String lastName) {
        this.firstName = firstName;
        this.registrationDate = registrationDate;
        this.email = email;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
