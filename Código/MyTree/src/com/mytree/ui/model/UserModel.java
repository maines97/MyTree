/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.model;

import com.mytree.business.model.User;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class UserModel {

    private static final int HASH = 7;
    private static final int HASH_RANDOM = 59;

    private IntegerProperty id;
    private StringProperty username;
    private StringProperty firstName;
    private StringProperty secondName;
    private StringProperty firstSurname;
    private StringProperty secondSurname;
    private StringProperty picturePath;
    private StringProperty country;
    private ObjectProperty<Date> birthday;

    public UserModel(final User user) {
        this.id = new SimpleIntegerProperty(user.getId());
        this.username = new SimpleStringProperty(user.getUsername());
        this.firstName = new SimpleStringProperty(user.getFirstName());
        this.secondName = new SimpleStringProperty(user.getSecondName());
        this.firstSurname = new SimpleStringProperty(user.getFirstSurname());
        this.secondSurname = new SimpleStringProperty(user.getSecondSurname());
        this.picturePath = new SimpleStringProperty(user.getPicturePath());
        this.country = new SimpleStringProperty(user.getCountry());
        this.birthday = new SimpleObjectProperty<>(user.getBirthday());

    }

    public IntegerProperty getId() {
        return id;
    }

    public void setId(final IntegerProperty id) {
        this.id = id;
    }

    public StringProperty getUsername() {
        return username;
    }

    public void setUsername(final StringProperty username) {
        this.username = username;
    }

    public StringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(final StringProperty firstName) {
        this.firstName = firstName;
    }

    public StringProperty getSecondName() {
        return secondName;
    }

    public void setSecondName(final StringProperty secondName) {
        this.secondName = secondName;
    }

    public StringProperty getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(final StringProperty firstSurname) {
        this.firstSurname = firstSurname;
    }

    public StringProperty getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(final StringProperty secondSurname) {
        this.secondSurname = secondSurname;
    }

    public StringProperty getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(final StringProperty picturePath) {
        this.picturePath = picturePath;
    }

    public StringProperty getCountry() {
        return country;
    }

    public void setCountry(final StringProperty country) {
        this.country = country;
    }

    public ObjectProperty<Date> getBirthday() {
        return birthday;
    }

    public void setBirthday(final ObjectProperty<Date> birthday) {
        this.birthday = birthday;
    }

    public boolean isNew() {
        return getId().getValue() == -1;
    }

    @Override
    public String toString() {
        return firstSurname.getValue() + " " + secondSurname.getValue() + ", " + firstName.getValue();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == null || !(other instanceof UserModel)) {
            return false;
        }

        UserModel otherUser = (UserModel) other;
        return id.getValue().equals(otherUser.getId().getValue());
    }

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = HASH_RANDOM * hash + Objects.hashCode(this.id);
        hash = HASH_RANDOM * hash + Objects.hashCode(this.username);
        return hash;
    }
}
