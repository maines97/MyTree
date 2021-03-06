/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import com.mytree.business.logic.BusinessLogicLocator;
import com.mytree.business.model.User;
import com.mytree.utils.Constants;
import java.io.File;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Locale;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public final class UserDetailsDialogController extends BaseController {

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
    private Label deathLabel;
    @FXML
    private Label deathTxt;
    @FXML
    private ImageView pictureImage;

    private Stage dialogStage;
    private int userId;

    public UserDetailsDialogController() {
        //CONSTRUCTOR
    }

    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
        loadDetails();
    }

    @Override
    protected void onInitialize() {
        //INITIALIZING
    }

    @FXML
    private void handleClose() {
        dialogStage.close();
    }

    private void loadDetails() {
        User user = BusinessLogicLocator.getInstance().getUserBusinessLogic().getUser(userId);
        String imagePath = Constants.USER_PROFILE;
        if (user.getPicturePath() != null && !user.getPicturePath().isEmpty()) {
            File file = new File(user.getPicturePath());
            imagePath = file.exists() ? file.toURI().toString() : Constants.USER_PROFILE;
        }
        pictureImage.setImage(new Image(imagePath));
        usernameLabel.setText(user.getUsername());
        firstNameLabel.setText(user.getFirstName());
        secondNameLabel.setText(user.getSecondName());
        firstSurnameLabel.setText(user.getFirstSurname());
        secondSurnameLabel.setText(user.getSecondSurname());
        countryLabel.setText(user.getCountry());
        birthdayLabel.setText(DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault()).format(user.getBirthday()));
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
    }
}
