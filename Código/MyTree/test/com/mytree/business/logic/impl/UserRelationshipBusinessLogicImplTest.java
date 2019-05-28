/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic.impl;

import com.mytree.business.dataaccess.UserRelationshipRepository;
import com.mytree.business.model.Relationship;
import com.mytree.business.model.UserRelationship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class UserRelationshipBusinessLogicImplTest {

    private UserRelationshipRepository repositoryMock;
    private UserRelationshipBusinessLogicImpl logic;

    public UserRelationshipBusinessLogicImplTest() {
    }

    @Before
    public void setUp() {
        repositoryMock = Mockito.mock(UserRelationshipRepository.class);
        logic = new UserRelationshipBusinessLogicImpl(repositoryMock);
    }

    @Test
    public void testSaveUserRelationshipCase() {
        UserRelationship fakeUserRelationship = new UserRelationship();
        logic.save(fakeUserRelationship);
        verify(repositoryMock).save(fakeUserRelationship);
    }

    @Test
    public void testGetRelationshipsByUserIdUserOwnerCase() {
        
        // Setup
        List<Relationship> relationships = new ArrayList<>();
        relationships.add(Relationship.CHILD);

        UserRelationship fakeUserRelationship = new UserRelationship();
        fakeUserRelationship.setRelationshipOf(0);
        fakeUserRelationship.setRelationshipWith(1);
        fakeUserRelationship.setRelationship(Relationship.UNION);
        UserRelationship anotherFakeUserRelationship = new UserRelationship();
        anotherFakeUserRelationship.setRelationshipOf(0);
        anotherFakeUserRelationship.setRelationshipWith(2);
        anotherFakeUserRelationship.setRelationship(Relationship.CHILD);

        Collection<UserRelationship> expected = new ArrayList<>();
        expected.add(fakeUserRelationship);
        expected.add(anotherFakeUserRelationship);

        when(repositoryMock.getRealationships(0)).thenReturn(expected);
        
        // Do test
        Collection<UserRelationship> result = logic.getUserRelationships(0, relationships);
        
        // Assertions
        assertEquals(1, result.size());
        assertEquals(anotherFakeUserRelationship, result.toArray()[0]);
    }

    @Test
    public void testGetRelationshipCase() {
        List<Relationship> relationships = new ArrayList<>();
        Collection<UserRelationship> expected = new ArrayList<>();
        when(repositoryMock.getRealationships(relationships)).thenReturn(expected);
        assertEquals(expected, logic.getRelationships(relationships));
    }

}
