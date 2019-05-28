/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import javafx.fxml.FXML;

public final class RootLayoutController extends BaseController {

    public RootLayoutController() {
    }

    @Override
    protected void onInitialize() {
    }

    @FXML
    private void handleTreeButton() {
        getNavigationManager().showTree();
    }
    
    @FXML
    private void handleTextualTreeButton() {
        getNavigationManager().showTextualTree();
    }

    @FXML
    private void handleUsersButton() {
        getNavigationManager().showUsers();
    }

    @FXML
    private void handleAttachmentButton() {
        getNavigationManager().showAttachments();
    }
}
