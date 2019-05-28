/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess.impl;

import com.mytree.business.model.Relationship;
import com.mytree.business.model.UserRelationship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public final class UserRelationshipRepositoryImplTest {

    private UserRelationship[][] dataSource;
    private UserRelationshipRepositoryImpl repository;

    public UserRelationshipRepositoryImplTest() {
    }

    @Before
    public void setUp() {
        dataSource = new UserRelationship[2][2];
        repository = new UserRelationshipRepositoryImpl(dataSource);
    }

    @Test
    public void testSaveRelationshipCase() {
        UserRelationship fake = getFakeUserRelationship(0, 1, Relationship.CHILD);
        repository.save(fake);
        assertNotNull(dataSource[0][1]);
        assertNotNull(dataSource[1][0]);
        assertNull(dataSource[0][0]);
        assertNull(dataSource[1][1]);
    }

    @Test
    public void testGetRelationshipForUserIdCase() {

        // Setup
        UserRelationship expected = getFakeUserRelationship(0, 1, Relationship.UNION);
        dataSource[0][1] = expected;
        dataSource[1][0] = getFakeUserRelationship(1, 0, Relationship.UNION);

        // Do test
        Collection<UserRelationship> result = repository.getRealationships(0);

        // Assertions
        assertEquals(1, result.size());
        assertEquals(expected, result.toArray()[0]);
    }

    @Test
    public void testGetRelationshipByRelationshipCase() {

        // Setup
        UserRelationship expected = getFakeUserRelationship(1, 0, Relationship.PARENT);
        dataSource[0][1] = getFakeUserRelationship(0, 1, Relationship.CHILD);
        dataSource[1][0] = expected;

        // Do test
        List<Relationship> relationships = new ArrayList<>();
        relationships.add(Relationship.PARENT);
        Collection<UserRelationship> result = repository.getRealationships(relationships);

        // Assertions
        assertEquals(1, result.size());
        assertEquals(expected, result.toArray()[0]);
    }

    // Private
    private UserRelationship getFakeUserRelationship(final int of, final int with,
            final Relationship relationship) {
        UserRelationship fake = new UserRelationship();
        fake.setRelationshipOf(0);
        fake.setRelationshipWith(1);
        fake.setRelationship(relationship);
        fake.setFromDate(new Date());
        fake.setToDate(new Date());
        return fake;
    }

}
