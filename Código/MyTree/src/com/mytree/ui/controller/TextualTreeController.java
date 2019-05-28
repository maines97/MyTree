/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import com.mytree.business.logic.BusinessLogicLocator;
import com.mytree.business.logic.UserBusinessLogic;
import com.mytree.business.model.User;
import com.mytree.business.model.UserRelationship;
import com.mytree.utils.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public final class TextualTreeController extends BaseController {

    @FXML
    private TreeTableView<User> userTreeTable;
    @FXML
    private TreeTableColumn<User, String> nameColumn;
    @FXML
    private TreeTableColumn<User, Integer> userDetailsLinkColumn;
    @FXML
    private TreeTableColumn<User, Integer> attachmentsLinkColumn;
    @FXML
    private ComboBox userComboBox;
    @FXML
    private CheckBox ascendingCkeckbox;

    public TextualTreeController() {
    }

    @Override
    protected void onInitialize() {
        ObservableList<User> users = FXCollections.observableArrayList();
        BusinessLogicLocator.getInstance().getUserBusinessLogic().getUsers(false).forEach((user) -> {
            users.add(user);
        });
        userComboBox.setItems(users);
        userComboBox.getSelectionModel().selectFirst();
        nameColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getValue().toString());
        });
        userDetailsLinkColumn.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getValue().getId()).asObject();
        });
        userDetailsLinkColumn.setCellFactory(value -> {
            return new LinkTreeTableCell<>(Constants.USER_DETAILS, (item) -> {
                getNavigationManager().showUserDetails(item);
            });
        });
        attachmentsLinkColumn.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getValue().getId()).asObject();
        });
        attachmentsLinkColumn.setCellFactory(value -> {
            return new LinkTreeTableCell<>(Constants.ATTACHMENTS, (item) -> {
                getNavigationManager().showUserAttachmentsDialog(item);
            });
        });

        loadTextualTree();
    }

    @FXML
    private void handleUserComboBox() {
        loadTextualTree();
    }

    @FXML
    private void handleAscendingCkeckbox() {
        loadTextualTree();
    }

    private void loadTextualTree() {
        boolean isAscending = ascendingCkeckbox.isSelected();
        User user = (User) userComboBox.getValue();
        if (user != null) {
            TreeItem<User> root = new TreeItem<>(user);
            root.getChildren().setAll(getTreeItemChildren(user, isAscending));

            userTreeTable.setRoot(root);
            userTreeTable.refresh();
        }
    }

    private List<TreeItem<User>> getTreeItemChildren(final User user, final boolean isAscending) {
        int id = user.getId();
        UserBusinessLogic userBusinessLogic = BusinessLogicLocator.getInstance()
                .getUserBusinessLogic();

        Collection<UserRelationship> relationships = isAscending ? user.getParents() : user.getChildren();
        List<TreeItem<User>> items = new ArrayList<>();
        relationships.forEach((rel) -> {
            int relationId = rel.getRelationshipOf() == id ? rel.getRelationshipWith() : id;
            User relationUser = userBusinessLogic.getUser(relationId);
            TreeItem<User> item = new TreeItem<>(relationUser);
            item.getChildren().setAll(getTreeItemChildren(relationUser, isAscending));
            items.add(item);
        });

        return items;
    }

    public static final class LinkTreeTableCell<S, T> extends TreeTableCell<S, T> {

        private final Hyperlink link;
        private T item;

        public LinkTreeTableCell(final String linkText,
                final LinkTreeTableCellActionEvent<T> actionEvent) {

            this.link = new Hyperlink(linkText);
            this.link.setOnAction((event) -> {
                actionEvent.onClickLink(item);
            });
        }

        @Override
        protected void updateItem(final T i, final boolean empty) {
            item = i;
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(link);
            }
        }
    }

    public interface LinkTreeTableCellActionEvent<T> {

        void onClickLink(T item);
    }
}
