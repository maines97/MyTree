/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import com.mytree.business.logic.BusinessLogicLocator;
import com.mytree.business.model.Attachment;
import com.mytree.business.model.Relationship;
import com.mytree.business.model.User;
import com.mytree.business.model.UserRelationship;
import com.mytree.utils.Constants;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

public final class AttachmentDialogController extends BaseController {

    private static final int HASH = 7;
    private static final int HASH_RANDOM = 79;

    @FXML
    private TextField nameField;
    @FXML
    private ComboBox ownerTypeComboBox;
    @FXML
    private CheckComboBox ownerComboBox;
    @FXML
    private TextArea historyTextArea;
    @FXML
    private Label attachmentPathLabel;
    @FXML
    private DatePicker fromDatePicker;
    @FXML
    private DatePicker toDatePicker;

    private Stage dialogStage;
    private FileChooser fileChooser;
    private Map<String, ObservableList<OwnerItem>> ownersByRelationship;

    public void setDialogStage(final Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    protected void onInitialize() {
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        fromDatePicker.setValue(now);
        toDatePicker.setValue(now);
        fileChooser = new FileChooser();

        // Get owner data
        BusinessLogicLocator locator = BusinessLogicLocator.getInstance();
        Collection<User> users = locator.getUserBusinessLogic().getUsers(true);
        Map<Integer, User> usersMap = new HashMap<>();
        users.forEach((user) -> usersMap.put(user.getId(), user));
        ObservableList<OwnerItem> userList = getAttachmentUserOwners(users);
        ObservableList<OwnerItem> marriedList = getAttachmentUserRelationshipOwners(usersMap, Relationship.MARRIED);
        ObservableList<OwnerItem> unionList = getAttachmentUserRelationshipOwners(usersMap, Relationship.UNION);

        ownersByRelationship = new HashMap<>();
        ownersByRelationship.put(Constants.PEOPLE, userList);
        if (marriedList.size() > 0) {
            ownersByRelationship.put(Constants.MARRIED, marriedList);
        }
        if (unionList.size() > 0) {
            ownersByRelationship.put(Constants.UNION, unionList);
        }
        reload();
    }

    @FXML
    private void handleOwnerTypeComboBoxSelection() {
        String ownerType = (String) ownerTypeComboBox.getValue();
        ownerComboBox.getItems().clear();
        ownerComboBox.getItems().addAll(ownersByRelationship.get(ownerType));
        ownerComboBox.getCheckModel().check(0);
    }

    @FXML
    private void handleSelectAttachment() {
        File file = fileChooser.showOpenDialog(dialogStage);
        attachmentPathLabel.setText(file.getAbsolutePath());
    }

    @FXML
    private void handleSave() {
        ObservableList<OwnerItem> checkedOwnerItems = ownerComboBox.getCheckModel().getCheckedItems();
        boolean isFamilyOwner = false;
        List<Integer> owners = new ArrayList<>();
        for (int i = 0; i < checkedOwnerItems.size() && !isFamilyOwner; i++) {
            OwnerItem ownerItem = checkedOwnerItems.get(i);
            isFamilyOwner = Constants.FAMILY.equals(ownerItem.getTitle());
            owners.addAll(ownerItem.getOwners());
        }

        // Create attachment
        Attachment attachment = new Attachment();
        attachment.setName(nameField.getText());
        attachment.setHistory(historyTextArea.getText());
        attachment.setAttachmentPath(attachmentPathLabel.getText());
        attachment.setOwners(owners);
        attachment.setFamilyOwner(isFamilyOwner);
        attachment.setFromDate(Date.from(Instant.from(fromDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()))));
        attachment.setToDate(Date.from(Instant.from(toDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()))));

        // Save it
        if (validateAttachment(attachment)) {
            BusinessLogicLocator.getInstance().getAttachmentBusinessLogic().save(attachment);
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
        ownerTypeComboBox.getItems().addAll(ownersByRelationship.keySet());
        ownerTypeComboBox.getSelectionModel().selectFirst();
        handleOwnerTypeComboBoxSelection();
    }

    private ObservableList<OwnerItem> getAttachmentUserOwners(final Collection<User> users) {
        ObservableList<OwnerItem> result = FXCollections.observableArrayList();
        result.add(new OwnerItem(new ArrayList<>(), Constants.FAMILY));
        users.forEach((user) -> {
            List<Integer> owners = new ArrayList<>();
            owners.add(user.getId());
            result.add(new OwnerItem(owners, user.toString()));
        });

        return result;
    }

    private ObservableList<OwnerItem> getAttachmentUserRelationshipOwners(final Map<Integer, User> users,
            final Relationship relationship) {
        List<Relationship> relationships = new ArrayList<>();
        relationships.add(relationship);
        ObservableList<OwnerItem> result = FXCollections.observableArrayList();
        BusinessLogicLocator locator = BusinessLogicLocator.getInstance();
        locator.getUserRelationshipBusinessLogic().getRelationships(relationships)
                .forEach((UserRelationship userRelationship) -> {
            List<Integer> owners = new ArrayList<>();
            owners.add(userRelationship.getRelationshipOf());
            owners.add(userRelationship.getRelationshipWith());
            User of = users.get(userRelationship.getRelationshipOf());
            User with = users.get(userRelationship.getRelationshipWith());
            String relationshipTitle = of.toString() + " " + with.toString();
            OwnerItem ownerItem = new OwnerItem(owners, relationshipTitle);
            if (!result.contains(ownerItem)) {
                result.add(ownerItem);
            }
        });

        return result;
    }

    private boolean validateAttachment(final Attachment attachment) {

        // Validate fields
        StringBuilder resultBuilder = new StringBuilder();
        if (attachment.getName().trim().isEmpty()) {
            resultBuilder.append(Constants.NAME_REQUIRED);
        }
        if (attachment.getOwners().isEmpty() && !attachment.isFamilyOwner()) {
            resultBuilder.append(Constants.OWNERS_MUST_BE_SELECTED);
        }

        // Present error
        String result = resultBuilder.toString();
        boolean isValidUserRelationship = result.isEmpty();
        if (!isValidUserRelationship) {
            getNavigationManager().showAlert(Alert.AlertType.ERROR,
                    Constants.ATTACHMENT_ERROR,
                    Constants.ADD_ATTACHMENT_ERROR, result);
        }
        return isValidUserRelationship;
    }

    public static final class OwnerItem {

        private final List<Integer> owners;
        private final String title;

        public OwnerItem(final List<Integer> owners, final String title) {
            this.owners = owners;
            this.title = title;
            Collections.sort(this.owners);
        }

        public Collection<Integer> getOwners() {
            return owners;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return title;
        }

        @Override
        public boolean equals(final Object other) {
            if (other == null || !(other instanceof OwnerItem)) {
                return false;
            }

            OwnerItem otherOwnerItem = (OwnerItem) other;
            return owners.equals(otherOwnerItem.getOwners());
        }

        @Override
        public int hashCode() {
            int hash = HASH;
            hash = HASH_RANDOM * hash + Objects.hashCode(this.owners);
            return hash;
        }
    }
}
