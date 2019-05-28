/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic.impl;

import com.mytree.business.dataaccess.AttachmentRepository;
import com.mytree.business.logic.AttachmentBusinessLogic;
import com.mytree.business.model.Attachment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class AttachmentBusinessLogicImpl extends BusinessLogicBase<AttachmentRepository>
        implements AttachmentBusinessLogic {

    public AttachmentBusinessLogicImpl(final AttachmentRepository repository) {
        super(repository);
    }

    @Override
    public Collection<Attachment> getAttachments() {
        return getRepository().getAttachments();
    }

    @Override
    public Collection<Attachment> getAttachments(final int userId) {
        List<Attachment> attachments = new ArrayList<>();
        getAttachments().forEach(attachment -> {
            if (attachment.isFamilyOwner()
                    || attachment.getOwners().contains(userId)) {
                attachments.add(attachment);
            }
        });
        return attachments;
    }

    @Override
    public void save(final Attachment entity) {
        getRepository().save(entity);
    }
}
