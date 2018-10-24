package lk.universe.core.domain;

import lk.universe.core.enums.UserType;

public class UserPublic {

    private long id;

    private String alias;
    private String firstName;
    private String lastName;
    private String surName;
    private UserType userType;
    private String token;
    private String fullName;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public String setFullName() {
        return fullName;
    }

    public UserPublic(long id, UserType type,String token,String fullName){
        this.userType = type;
        this.id = id;
        this.token = token;
        this.fullName = fullName;
    }
}
