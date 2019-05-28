/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.assembler;

import com.mytree.business.dataaccess.AttachmentRepository;
import com.mytree.business.dataaccess.UserRepository;
import com.mytree.business.dataaccess.UserRelationshipRepository;
import com.mytree.business.dataaccess.impl.AttachmentRepositoryImpl;
import com.mytree.business.dataaccess.impl.UserRelationshipRepositoryImpl;
import com.mytree.business.dataaccess.impl.UserRepositoryImpl;
import com.mytree.business.logic.AttachmentBusinessLogic;
import com.mytree.business.logic.BusinessLogicLocator;
import com.mytree.business.logic.UserBusinessLogic;
import com.mytree.business.logic.UserRelationshipBusinessLogic;
import com.mytree.business.logic.impl.AttachmentBusinessLogicImpl;
import com.mytree.business.logic.impl.UserBusinessLogicImpl;
import com.mytree.business.logic.impl.UserRelationshipBusinessLogicImpl;
import com.mytree.business.model.Attachment;
import com.mytree.business.model.User;
import com.mytree.business.model.UserRelationship;
import com.mytree.utils.Constants;
import java.util.ArrayList;
import java.util.List;

public final class Assembler {

    private static final Assembler INSTANCE = new Assembler();

    public static Assembler getInstance() {
        return INSTANCE;
    }

    private Assembler() {
    }

    public void assemble() {
        List<Attachment> attachments = new ArrayList<>();
        List<User> users = new ArrayList<>();
        UserRelationship[][] userRelationships = 
                new UserRelationship[Constants.DEFAULT_MAX_USERS_COUNT][Constants.DEFAULT_MAX_USERS_COUNT];

        // Make repositories
        UserRelationshipRepository userRelationshipRepository = new UserRelationshipRepositoryImpl(userRelationships);
        UserRepository userRepository = new UserRepositoryImpl(users);
        AttachmentRepository attachmentRepository = new AttachmentRepositoryImpl(attachments);

        // Make business logics
        UserRelationshipBusinessLogic userRelationshipBusinessLogic 
                = new UserRelationshipBusinessLogicImpl(userRelationshipRepository);
        UserBusinessLogic userBusinessLogic 
                = new UserBusinessLogicImpl(userRepository, userRelationshipBusinessLogic);
        AttachmentBusinessLogic attachmentBusinessLogic 
                = new AttachmentBusinessLogicImpl(attachmentRepository);

        // Make business logics accessors
        BusinessLogicLocator locator = BusinessLogicLocator.getInstance();
        locator.setUserRelationshipBusinessLogic(userRelationshipBusinessLogic);
        locator.setUserBusinessLogic(userBusinessLogic);
        locator.setAttachmentBusinessLogic(attachmentBusinessLogic);
    }

}
