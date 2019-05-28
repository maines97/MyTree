/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic.impl;

import com.mytree.business.dataaccess.UserRepository;
import com.mytree.business.logic.UserRelationshipBusinessLogic;
import com.mytree.business.model.User;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public final class UserBusinessLogicImplTest {

    private static final int USER_ID = 3;

    private UserRepository repositoryMock;
    private UserRelationshipBusinessLogic userRelationshipLogicMock;
    private UserBusinessLogicImpl logic;

    public UserBusinessLogicImplTest() {
    }

    @Before
    public void setUp() {
        repositoryMock = Mockito.mock(UserRepository.class);
        userRelationshipLogicMock = Mockito.mock(UserRelationshipBusinessLogic.class);
        logic = new UserBusinessLogicImpl(repositoryMock, userRelationshipLogicMock);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveUserCase() {
        User fakeUser = new User();
        logic.save(fakeUser);
        verify(repositoryMock).save(fakeUser);
    }

    @Test
    public void testGetUserCase() {
        User expected = new User();
        expected.setId(USER_ID);

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(expected);

        when(repositoryMock.getUsers()).thenReturn(users);

        assertEquals(expected, logic.getUser(USER_ID));
    }

    @Test
    public void testGetUsersLazyCase() {
        User expected = new User();
        expected.setId(USER_ID);

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(expected);

        when(repositoryMock.getUsers()).thenReturn(users);

        assertEquals(2, logic.getUsers(true).size());
        verify(userRelationshipLogicMock, never())
                .getUserRelationships(Mockito.anyInt(), Mockito.anyList());
    }

    @Test
    public void testGetUsersFullCase() {

        // Fake objects
        User fakeUser = new User();
        fakeUser.setId(USER_ID);

        List<User> users = new ArrayList<>();
        users.add(fakeUser);

        // Stub getUsers and add captor to getRealtionships
        when(userRelationshipLogicMock.getUserRelationships(Mockito.anyInt(), Mockito.anyList()))
                .thenReturn(new ArrayList<>());
        when(repositoryMock.getUsers()).thenReturn(users);

        // Assertions
        assertEquals(users, logic.getUsers(false));
        final int expectedTimes = 3;
        verify(userRelationshipLogicMock, times(expectedTimes))
                .getUserRelationships(Mockito.anyInt(), Mockito.anyList());
    }

}
