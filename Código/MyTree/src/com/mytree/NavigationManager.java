/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree;

import com.mytree.ui.model.UserModel;
import javafx.scene.control.Alert.AlertType;

public interface NavigationManager {

    void showUsers();

    void showTree();

    void showAttachments();

    void showUserEditDialog(UserModel userModel, boolean allowCancel);

    void showUserRelationshipDialog();

    void showAttachmentDialog();

    void showAlert(AlertType alertType, String title, String header, String content);

    void openDocument(String path);

    void showTextualTree();

    void showUserDetails(int userId);
    
    void showUserAttachmentsDialog(int userId);

}
