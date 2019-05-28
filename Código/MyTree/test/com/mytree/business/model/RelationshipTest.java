/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.model;

import com.mytree.utils.Constants;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public final class RelationshipTest {

    public RelationshipTest() {
    }

    @Test
    public void testGetParentOppositiveCase() {
        assertEquals(Relationship.CHILD, Relationship.PARENT.getOpposite());
    }

    @Test
    public void testGetChildOppositiveCase() {
        assertEquals(Relationship.PARENT, Relationship.CHILD.getOpposite());
    }

    @Test
    public void testGetMarriedOppositiveCase() {
        assertEquals(Relationship.MARRIED, Relationship.MARRIED.getOpposite());
    }

    @Test
    public void testGetUnionOppositiveCase() {
        assertEquals(Relationship.UNION, Relationship.UNION.getOpposite());
    }

    @Test
    public void testGetMotherRelationshipCase() {
        testGetRelationshipFromStringCase(Constants.MOTHER, Relationship.PARENT);
    }

    @Test
    public void testGetFatherRelationshipCase() {
        testGetRelationshipFromStringCase(Constants.FATHER, Relationship.PARENT);
    }

    @Test
    public void testGetChildRelationshipCase() {
        testGetRelationshipFromStringCase(Constants.CHILD, Relationship.CHILD);
    }

    @Test
    public void testGetMarriedRelationshipCase() {
        testGetRelationshipFromStringCase(Constants.MARRIED, Relationship.MARRIED);
    }

    @Test
    public void testGetUnionRelationshipCase() {
        testGetRelationshipFromStringCase(Constants.UNION, Relationship.UNION);
    }

    @Test
    public void testGetNoneRelationshipCase() {
        testGetRelationshipFromStringCase("nothing", Relationship.NONE);
    }

    // Private
    private void testGetRelationshipFromStringCase(final String rel, final Relationship expected) {
        Relationship actual = Relationship.getRelationship(rel);
        assertEquals(expected, actual);
    }
}
