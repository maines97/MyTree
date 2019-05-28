/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic.impl;

import com.mytree.business.dataaccess.AttachmentRepository;
import com.mytree.business.model.Attachment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class AttachmentBusinessLogicImplTest {

    private AttachmentRepository repositoryMock;
    private AttachmentBusinessLogicImpl logic;

    public AttachmentBusinessLogicImplTest() {
    }

    @Before
    public void setUp() {
        repositoryMock = Mockito.mock(AttachmentRepository.class);
        logic = new AttachmentBusinessLogicImpl(repositoryMock);
    }

    @Test
    public void testGetAttachmentsCase() {
        Collection<Attachment> expected = new ArrayList<>();
        when(repositoryMock.getAttachments()).thenReturn(expected);
        assertEquals(expected, logic.getAttachments());
    }

    @Test
    public void testGetAttachmentsByUserIdFamilyOwnerCase() {
        Attachment fakeAttachement = new Attachment();
        fakeAttachement.setFamilyOwner(true);
        Collection<Attachment> expected = new ArrayList<>();
        expected.add(fakeAttachement);
        when(repositoryMock.getAttachments()).thenReturn(expected);
        assertEquals(expected, logic.getAttachments(0));
    }

    @Test
    public void testGetAttachmentsByUserIdUserOwnerCase() {
        List<Integer> owners = new ArrayList<>();
        owners.add(1);
        Attachment fakeAttachement = new Attachment();
        fakeAttachement.setOwners(owners);
        Collection<Attachment> expected = new ArrayList<>();
        expected.add(fakeAttachement);
        when(repositoryMock.getAttachments()).thenReturn(expected);
        assertEquals(expected, logic.getAttachments(1));
    }

    @Test
    public void testSaveAttachmentsCase() {
        Attachment fakeAttachment = new Attachment();
        logic.save(fakeAttachment);
        verify(repositoryMock).save(fakeAttachment);
    }
}
