/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.model;

import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public final class UserRelationshipTest {

    public UserRelationshipTest() {
    }

    @Test
    public void testRelationshipOfPropertyCase() {
        int expected = 1;
        UserRelationship instance = new UserRelationship();
        instance.setRelationshipOf(expected);
        assertEquals(expected, instance.getRelationshipOf());
    }

    @Test
    public void testRelationshipWithPropertyCase() {
        int expected = 1;
        UserRelationship instance = new UserRelationship();
        instance.setRelationshipWith(expected);
        assertEquals(expected, instance.getRelationshipWith());
    }

    @Test
    public void testFromDatePropertyCase() {
        Date expected = new Date();
        UserRelationship instance = new UserRelationship();
        instance.setFromDate(expected);
        assertEquals(expected, instance.getFromDate());
        assertNotSame(expected, instance.getFromDate());
    }

    @Test
    public void testToDatePropertyCase() {
        Date expected = new Date();
        UserRelationship instance = new UserRelationship();
        instance.setToDate(expected);
        assertEquals(expected, instance.getToDate());
        assertNotSame(expected, instance.getToDate());
    }

    @Test
    public void testFamilyOwnerPropertyCase() {
        UserRelationship instance = new UserRelationship();
        instance.setCurrentRelationship(true);
        assertTrue(instance.isCurrentRelationship());
    }

    @Test
    public void testRelationshipPropertyCase() {
        Relationship expected = Relationship.CHILD;
        UserRelationship instance = new UserRelationship();
        instance.setRelationship(expected);
        assertEquals(expected, instance.getRelationship());
    }

}
