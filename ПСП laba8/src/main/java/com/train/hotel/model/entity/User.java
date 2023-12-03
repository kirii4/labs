package com.train.hotel.model.entity;

import java.util.Date;
public class User {


    private String role;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Date dob;
    private String mobileNo;
    private long id;

    public String getRole() {
        return role;
    }
    public long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public Date getDob() {
        return dob;
    }
    public String getMobileNo() {
        return mobileNo;
    }

    public static class UserBuilder{
        private User user;

        public UserBuilder() {
            user = new User();
        }
        public UserBuilder setRole(String role) {
            user.role = role;
            return this;
        }
        public UserBuilder setId(long id) {
            user.id = id;
            return this;
        }
        public UserBuilder setFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }
        public UserBuilder setLastName(String lastName) {
            user.lastName = lastName;
            return this;
        }
        public UserBuilder setLogin(String login) {
            user.login = login;
            return this;
        }
        public UserBuilder setPassword(String password) {
            user.password = password;
            return this;
        }
        public UserBuilder setDob(Date dob) {
            user.dob = dob;
            return this;
        }
        public UserBuilder setMobileNo(String mobileNo) {
            user.mobileNo = mobileNo;
            return this;
        }

        public User build(){
            return user;
        }

    }
}