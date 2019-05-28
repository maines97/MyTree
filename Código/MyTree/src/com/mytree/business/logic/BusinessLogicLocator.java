/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic;

public final class BusinessLogicLocator {

    private static final BusinessLogicLocator INSTANCE = new BusinessLogicLocator();

    public static BusinessLogicLocator getInstance() {
        return INSTANCE;
    }

    private UserBusinessLogic userBusinessLogic;
    private UserRelationshipBusinessLogic userRelationshipBusinessLogic;
    private AttachmentBusinessLogic attachmentBusinessLogic;

    private BusinessLogicLocator() {
    }

    public UserBusinessLogic getUserBusinessLogic() {
        return userBusinessLogic;
    }

    public void setUserBusinessLogic(final UserBusinessLogic userBusinessLogic) {
        this.userBusinessLogic = userBusinessLogic;
    }

    public UserRelationshipBusinessLogic getUserRelationshipBusinessLogic() {
        return userRelationshipBusinessLogic;
    }

    public void setUserRelationshipBusinessLogic(final UserRelationshipBusinessLogic userRelationshipBusinessLogic) {
        this.userRelationshipBusinessLogic = userRelationshipBusinessLogic;
    }

    public AttachmentBusinessLogic getAttachmentBusinessLogic() {
        return attachmentBusinessLogic;
    }

    public void setAttachmentBusinessLogic(final AttachmentBusinessLogic attachmentBusinessLogic) {
        this.attachmentBusinessLogic = attachmentBusinessLogic;
    }
}
