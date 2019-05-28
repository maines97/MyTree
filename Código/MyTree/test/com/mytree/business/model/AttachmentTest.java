/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public final class AttachmentTest {

    public AttachmentTest() {
    }

    @Test
    public void testIdPropertyCase() {
        int expected = 1;
        Attachment instance = new Attachment();
        instance.setId(expected);
        assertEquals(expected, instance.getId());
    }

    @Test
    public void testFamilyOwnerPropertyCase() {
        Attachment instance = new Attachment();
        instance.setFamilyOwner(true);
        assertTrue(instance.isFamilyOwner());
    }

    @Test
    public void testOwnersPropertyCase() {
        List<Integer> expected = new ArrayList<>();
        Attachment instance = new Attachment();
        instance.setOwners(expected);
        assertEquals(expected, instance.getOwners());
    }

    @Test
    public void testNamePropertyCase() {
        String expected = "expected";
        Attachment instance = new Attachment();
        instance.setName(expected);
        assertEquals(expected, instance.getName());
    }

    @Test
    public void testHistoryPropertyCase() {
        String expected = "expected";
        Attachment instance = new Attachment();
        instance.setHistory(expected);
        assertEquals(expected, instance.getHistory());
    }

    @Test
    public void testAttachmentPathPropertyCase() {
        String expected = "expected";
        Attachment instance = new Attachment();
        instance.setAttachmentPath(expected);
        assertEquals(expected, instance.getAttachmentPath());
    }

    @Test
    public void testFromDatePropertyCase() {
        Date expected = new Date();
        Attachment instance = new Attachment();
        instance.setFromDate(expected);
        assertEquals(expected, instance.getFromDate());
        assertNotSame(expected, instance.getFromDate());
    }
    
    @Test
    public void testToDatePropertyCase() {
        Date expected = new Date();
        Attachment instance = new Attachment();
        instance.setToDate(expected);
        assertEquals(expected, instance.getToDate());
        assertNotSame(expected, instance.getToDate());
    }

    @Test
    public void testEqualsSuccessCase() {
        Attachment other = new Attachment();
        other.setId(2);
        Attachment instance = new Attachment();
        instance.setId(2);
        testEquals(instance, other, true);
    }

    @Test
    public void testEqualsNullCase() {
        Object other = null;
        Attachment instance = new Attachment();
        testEquals(instance, other, false);
    }

    @Test
    public void testEqualsDiferentsTypesCase() {
        Object other = new Object();
        Attachment instance = new Attachment();
        testEquals(instance, other, false);
    }

    @Test
    public void testEqualsDiferentsIdsCase() {
        Attachment other = new Attachment();
        other.setId(1);
        Attachment instance = new Attachment();
        instance.setId(2);
        testEquals(instance, other, false);
    }

    // Private
    private void testEquals(final Attachment instance,
            final Object other, final boolean expResult) {
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
    }
}
