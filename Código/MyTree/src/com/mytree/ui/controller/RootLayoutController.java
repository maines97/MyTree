/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public final class RootLayoutController extends BaseController {
    
    @FXML
    private Label title;

    public RootLayoutController() {
    }

    @Override
    protected void onInitialize() {
        title.setText("Árbol Gráfico");
    }

    @FXML
    private void handleTreeButton() {
        title.setText("Árbol Gráfico");
        getNavigationManager().showTree();
    }
    
    @FXML
    private void handleTextualTreeButton() {
        title.setText("Árbol Textual");
        getNavigationManager().showTextualTree();
    }

    @FXML
    private void handleUsersButton() {
        title.setText("Familia");
        getNavigationManager().showUsers();
    }

    @FXML
    private void handleAttachmentButton() {
        title.setText("Adjuntos");
        getNavigationManager().showAttachments();
    }
}
