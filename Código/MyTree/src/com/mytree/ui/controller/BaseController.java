/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytree.ui.controller;

import com.mytree.NavigationManager;
import javafx.fxml.FXML;

public abstract class BaseController {

    private NavigationManager navigationManager;

    public final NavigationManager getNavigationManager() {
        return navigationManager;
    }

    public final void setNavigationManager(final NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }
    
    protected abstract void onInitialize();
    
    @FXML
    private void initialize() {
        onInitialize();
    }
    
}
