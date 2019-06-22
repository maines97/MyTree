/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import com.mytree.business.logic.AttachmentBusinessLogic;
import com.mytree.business.logic.BusinessLogicLocator;
import com.mytree.business.logic.UserBusinessLogic;
import com.mytree.business.model.Attachment;
import com.mytree.utils.Constants;
import com.mytree.utils.StringUtils;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;


public final class AttachmentsController extends BaseController {

    @FXML
    private TableView<Attachment> attachmentTable;
    @FXML
    private TableColumn<Attachment, String> nameColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label ownerLabel;
    @FXML
    private Label dateLabel;
    //////////
    @FXML
    private Label attachmentPath;
    @FXML
    private TextArea historyTextArea;
    @FXML
    private Button newAttachmentButton;
    ///////////

    private int userId;

    public AttachmentsController() {
        this.userId = -1;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
        reload();
    }

    @Override
    protected void onInitialize() {
        historyTextArea.setEditable(false);
        nameColumn.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getName()));
        attachmentTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue)
                        -> showAttachmentDetails(newValue));
        showAttachmentDetails(null);
        reload();
    }

    @FXML
    private void handleNewAttachment() {
        getNavigationManager().showAttachmentDialog();
        reload();
    }

    @FXML
    private void handleOpenAttachment() {
        if (!attachmentPath.getText().isEmpty()) {
            getNavigationManager().openDocument(attachmentPath.getText());
        }
    }

    private void reload() {
        ObservableList<Attachment> attachments = FXCollections.observableArrayList();
        getAttachments().forEach(attachment -> {
            attachments.add(attachment);
        });
        attachmentTable.setItems(attachments);
        attachmentTable.refresh();
        BusinessLogicLocator locator = BusinessLogicLocator.getInstance();
        boolean disabled = locator.getUserBusinessLogic().getUsers(true).isEmpty();
        newAttachmentButton.setDisable(disabled);

    }

    private Collection<Attachment> getAttachments() {
        BusinessLogicLocator locator = BusinessLogicLocator.getInstance();
        AttachmentBusinessLogic logic = locator.getAttachmentBusinessLogic();
        return userId > -1 ? logic.getAttachments(userId) : logic.getAttachments();
    }

    private void showAttachmentDetails(final Attachment attachment) {
        if (attachment != null) {
            Locale locale = Locale.getDefault();
            DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
            Date from = attachment.getFromDate();
            Date to = attachment.getToDate();
            StringBuilder dateBuilder = new StringBuilder();
            dateBuilder.append(df.format(from));
            if (to.compareTo(from) > 0) {
                dateBuilder.append(" hasta ");
                dateBuilder.append(df.format(to));
            }
            dateLabel.setText(dateBuilder.toString());
            nameLabel.setText(attachment.getName());
            ownerLabel.setText(getOwners(attachment));
            attachmentPath.setText(attachment.getAttachmentPath());
            historyTextArea.setText(attachment.getHistory());
        } else {
            dateLabel.setText("");
            nameLabel.setText("");
            ownerLabel.setText("");
            attachmentPath.setText("");
            historyTextArea.setText("");
        }
    }

    private String getOwners(final Attachment attachment) {
        String owners = Constants.FAMILY;
        if (!attachment.isFamilyOwner()) {
            String token = "#COMMA#";
            StringBuilder ownersBuilder = new StringBuilder();
            BusinessLogicLocator locator = BusinessLogicLocator.getInstance();
            UserBusinessLogic logic = locator.getUserBusinessLogic();
            List<Integer> ownersIds = attachment.getOwners();
            for (Iterator<Integer> iterator = ownersIds.iterator(); iterator.hasNext();) {
                Integer ownerId = iterator.next();
                ownersBuilder.append(logic.getUser(ownerId).getFullName());
                if (iterator.hasNext()) {
                    ownersBuilder.append(token);
                }
            }
            owners = ownersBuilder.toString();
            owners = StringUtils.replaceLast(owners, token, " y ");
            owners = owners.replaceAll(token, ", ");
        }
        return owners;
    }

}
