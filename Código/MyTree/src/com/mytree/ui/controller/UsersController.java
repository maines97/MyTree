/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import com.mytree.business.logic.BusinessLogicLocator;
import com.mytree.business.model.User;
import com.mytree.ui.model.UserModel;
import com.mytree.utils.Constants;
import java.io.File;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Locale;
import java.time.Period;
import java.time.ZoneId;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class UsersController extends BaseController {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label secondNameLabel;
    @FXML
    private Label firstSurnameLabel;
    @FXML
    private Label secondSurnameLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label deathTxt;
    @FXML
    private Label deathLabel;
    @FXML
    private ImageView pictureImage;

    public UsersController() {
        //CONSTRUCTOR
    }

    @Override
    protected void onInitialize() {
        nameColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().toString())
        );
        userTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showUserDetails(newValue));
        showUserDetails(null);
        reload();
    }

    @FXML
    private void handleNewUser() {
        UserModel userModel = new UserModel(new User());
        getNavigationManager().showUserEditDialog(userModel, true);
        reload();
    }

    @FXML
    private void handleEditUser() {
        User user = checkUserSelection();
        if (user != null) {
            getNavigationManager().showUserEditDialog(new UserModel(user), true);
            reload();
        }
    }
    
    @FXML
    private void showAttachments() {
        User user = checkUserSelection();
        if (user != null) {
            getNavigationManager().showUserAttachmentsDialog(user.getId());
            reload();
        }
    }

    private void reload() {
        ObservableList<User> users = FXCollections.observableArrayList();
        BusinessLogicLocator.getInstance().getUserBusinessLogic().getUsers(true).forEach(user -> users.add(user));
        userTable.setItems(users);
        userTable.refresh();
    }

    private void showUserDetails(final User user) {
        String imagePath = Constants.USER_PROFILE;
        if (user != null) {
            String picturePath = user.getPicturePath();
            if (picturePath != null && !picturePath.isEmpty()) {
                File file = new File(picturePath);
                imagePath = file.exists() ? file.toURI().toString() : Constants.USER_PROFILE;
            }
            usernameLabel.setText(user.getUsername());
            firstNameLabel.setText(user.getFirstName());
            secondNameLabel.setText(user.getSecondName());
            firstSurnameLabel.setText(user.getFirstSurname());
            secondSurnameLabel.setText(user.getSecondSurname());
            countryLabel.setText(user.getCountry());
            birthdayLabel.setText(DateFormat.getDateInstance(DateFormat.LONG, 
                    Locale.getDefault()).format(user.getBirthday()));
            deathLabel.setVisible(user.isDead());
            deathTxt.setVisible(user.isDead());
            if(user.isDead()){                
                Period diff = Period.between(
                        LocalDate.from(Instant.ofEpochMilli(user.getBirthday().getTime()).atZone(ZoneId.systemDefault())), 
                        LocalDate.from(Instant.ofEpochMilli(user.getDeath().getTime()).atZone(ZoneId.systemDefault())));
                int diffYears = diff.getYears();
                deathTxt.setText(DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault()).format(user.getDeath())
                    + " (" + diffYears + " años)" );
            }
        } else {
            usernameLabel.setText("");
            firstNameLabel.setText("");
            secondNameLabel.setText("");
            firstSurnameLabel.setText("");
            secondSurnameLabel.setText("");
            countryLabel.setText("");
            birthdayLabel.setText("");
            deathTxt.setText("");
            deathLabel.setVisible(false);
        }
        pictureImage.setImage(new Image(imagePath));
    }

    private User checkUserSelection() {
        User user = userTable.getSelectionModel().getSelectedItem();
        if (user == null) {
            getNavigationManager().showAlert(AlertType.INFORMATION, Constants.WITHOUT_SELECTION,
                    Constants.WITHOUT_USER_SELECTED, Constants.SELECT_USER);
        }
        return user;
    }

}
