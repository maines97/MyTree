/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import com.mytree.business.logic.BusinessLogicLocator;
import com.mytree.business.model.User;
import com.mytree.business.model.UserRelationship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.fxml.FXML;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class TreeController extends BaseController {

    @FXML
    private WebView webView;

    private WebEngine webEngine;

    public TreeController() {
    }

    public void reload() {
        JSONArray items = getTreeItems();
        webEngine.executeScript("reloadTree('" + items.toString() + "')");
    }

    @Override
    protected void onInitialize() {
        webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/com/mytree/ui/view/tree/index.html").toExternalForm());
        webEngine.getLoadWorker().stateProperty()
                .addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {
            if (newState == State.SUCCEEDED) {
                reload();
            }
        });
    }

    @FXML
    private void handleAddRelationship() {
        getNavigationManager().showUserRelationshipDialog();
        reload();
    }

    private JSONArray getTreeItems() {
        JSONArray items = new JSONArray();
        BusinessLogicLocator.getInstance().getUserBusinessLogic().getUsers(false).forEach((User user) -> {
            items.add(getTreeItem(user));
        });
        return items;
    }

    private JSONObject getTreeItem(final User user) {
        JSONObject item = new JSONObject();
        item.put("id", user.getId());
        item.put("title", user.getFullName());
        item.put("parents", getRelationshipsIds(user.getParents()));
        item.put("spouses", getRelationshipsIds(user.getSpouses()));
        return item;
    }

    private List<Integer> getRelationshipsIds(final Collection<UserRelationship> userRelationships) {
        List<Integer> result = new ArrayList<>();
        userRelationships.forEach((userRelationship) -> {
            result.add(userRelationship.getRelationshipWith());
        });
        return result;
    }
}
