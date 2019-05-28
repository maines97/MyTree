/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess.impl;

import com.mytree.business.model.Attachment;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.mockito.Mockito;

public final class AttachmentRepositoryImplTest {

    private List<Attachment> dataSourceMock;
    private AttachmentRepositoryImpl repository;

    public AttachmentRepositoryImplTest() {

    }

    @Before
    public void setUp() {
        dataSourceMock = Mockito.mock(List.class);
        repository = new AttachmentRepositoryImpl(dataSourceMock);
    }

    @Test
    public void testGetAttachmentsCase() {
        assertEquals(dataSourceMock, repository.getAttachments());
    }

    @Test
    public void testSaveNewUserCase() {
        Attachment fakeAttachment = new Attachment();
        repository.save(fakeAttachment);
        Mockito.verify(dataSourceMock).add(0, fakeAttachment);
    }

}
