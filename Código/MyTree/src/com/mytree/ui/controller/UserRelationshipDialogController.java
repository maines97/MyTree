/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import com.mytree.business.logic.BusinessLogicLocator;
import com.mytree.business.model.Relationship;
import com.mytree.business.model.User;
import com.mytree.business.model.UserRelationship;
import com.mytree.ui.model.UserModel;
import com.mytree.utils.Constants;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public final class UserRelationshipDialogController extends BaseController {

    @FXML
    private Label thirdFieldLabel;
    @FXML
    private Label fromFieldLabel;
    @FXML
    private Label toFieldLabel;
    @FXML
    private ComboBox userComboBox;
    @FXML
    private ComboBox relationshipTypeComboBox;
    @FXML
    private ComboBox thirdFieldComboBox;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;
    @FXML
    private CheckBox currentRelationshipCheckbox;

    private Stage dialogStage;

    public UserRelationshipDialogController() {
    }

    @Override
    protected void onInitialize() {
        reload();
    }

    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleUserComboBoxSelection() {
        List<User> excludedUsers = new ArrayList<User>();
        excludedUsers.add((User) userComboBox.getValue());
        thirdFieldComboBox.setItems(getUsers(excludedUsers));
        thirdFieldComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleRelationshipTypeComboBoxSelection() {
        updateFormContent();
    }

    @FXML
    private void handleCurrentRelationshipCheckbox() {
        updateFormContent();
    }

    @FXML
    private void handleAddUser() {
        getNavigationManager().showUserEditDialog(new UserModel(new User()), true);
        handleUserComboBoxSelection();
        thirdFieldComboBox.getSelectionModel().selectLast();
    }

    @FXML
    private void handleSave() {
        String relationship = (String) relationshipTypeComboBox.getValue();
        User of = (User) userComboBox.getValue();
        User with = (User) thirdFieldComboBox.getValue();
        Date from = Date.from(Instant.from(fromDatePicker.getValue().atStartOfDay(ZoneId.systemDefault())));
        Date to = Date.from(Instant.from(toDatePicker.getValue().atStartOfDay(ZoneId.systemDefault())));
        boolean currentRelationship = currentRelationshipCheckbox.isSelected();

        // Create user relationship
        UserRelationship userRelationship = new UserRelationship();
        userRelationship.setRelationship(Relationship.getRelationship(relationship));
        userRelationship.setRelationshipOf(of != null ? of.getId() : -1);
        userRelationship.setRelationshipWith(with != null ? with.getId() : -1);
        userRelationship.setFromDate(from);
        userRelationship.setToDate(to);
        userRelationship.setCurrentRelationship(currentRelationship);

        // Save it
        if (validateUserRelationship(userRelationship)) {
            BusinessLogicLocator.getInstance().getUserRelationshipBusinessLogic().save(userRelationship);
            dialogStage.close();
        }

    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private void handleDatePicker() {
        LocalDate from = fromDatePicker.getValue();
        LocalDate to = toDatePicker.getValue();
        if (from.compareTo(to) > 0) {
            toDatePicker.setValue(from);
        }
    }

    private void reload() {
        userComboBox.setItems(getUsers(null));
        userComboBox.getSelectionModel().selectFirst();

        relationshipTypeComboBox.getItems().addAll(Constants.FATHER, 
                Constants.MOTHER, Constants.CHILD, 
                Constants.MARRIED, Constants.UNION);
        relationshipTypeComboBox.getSelectionModel().selectFirst();

        LocalDate currentDate = LocalDate.from(
                Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()));
        toDatePicker.setValue(currentDate);
        fromDatePicker.setValue(currentDate);

        handleUserComboBoxSelection();
        updateFormContent();
    }

    private ObservableList<User> getUsers(final List<User> excludedUsers) {
        ObservableList<User> users = FXCollections.observableArrayList();
        BusinessLogicLocator.getInstance().getUserBusinessLogic().getUsers(true).forEach((user) -> {
            if (excludedUsers == null || !excludedUsers.contains(user)) {
                users.add(user);
            }
        });
        return users;
    }

    private void updateFormContent() {
        String relationType = (String) relationshipTypeComboBox.getValue();
        boolean isConjugate = relationType.equals(Constants.MARRIED) || relationType.equals(Constants.UNION);
        boolean mustShowToFields = isConjugate && !currentRelationshipCheckbox.isSelected();
        thirdFieldLabel.setText(relationType);
        fromFieldLabel.setVisible(isConjugate);
        fromDatePicker.setVisible(isConjugate);
        toFieldLabel.setVisible(mustShowToFields);
        toDatePicker.setVisible(mustShowToFields);
        currentRelationshipCheckbox.setVisible(isConjugate);
    }

    private boolean validateUserRelationship(final UserRelationship userRelationship) {

        // Validate fields
        StringBuilder resultBuilder = new StringBuilder();
        if (userRelationship.getRelationshipOf() == -1
                || userRelationship.getRelationshipWith() == -1) {
            resultBuilder.append(Constants.RELATIONSHIP_MUST_BE_DEFINED);
        }

        // Present error
        String result = resultBuilder.toString();
        boolean isValidUserRelationship = result.isEmpty();
        if (!isValidUserRelationship) {
            getNavigationManager().showAlert(AlertType.ERROR, Constants.USER_RELATIONSHIP_ERROR, 
                    Constants.ADD_USER_RELATIONSHIP_ERROR, result);
        }
        return isValidUserRelationship;
    }
}
