/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess.impl;

import com.mytree.business.dataaccess.AttachmentRepository;
import com.mytree.business.model.Attachment;
import java.util.Collection;
import java.util.List;

public final class AttachmentRepositoryImpl
        extends RepositoryBase<List<Attachment>>
        implements AttachmentRepository {

    private int identity = 0;

    public AttachmentRepositoryImpl(final List<Attachment> dataSource) {
        super(dataSource);
    }

    @Override
    public Collection<Attachment> getAttachments() {
        return getDataSource();
    }

    @Override
    public void save(final Attachment entity) {
        if (entity.getId() < 0) {
            entity.setId(identity);
            getDataSource().add(identity++, entity);
        } 
    }

}
