/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess.impl;

import com.mytree.business.model.User;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

public final class UserRepositoryImplTest {

    private List<User> dataSourceMock;
    private UserRepositoryImpl repository;

    public UserRepositoryImplTest() {
    }

    @Before
    public void setUp() {
        dataSourceMock = Mockito.mock(List.class);
        repository = new UserRepositoryImpl(dataSourceMock);
    }

    @Test
    public void testSaveNewUserCase() {
        User fakeUser = new User();
        repository.save(fakeUser);
        Mockito.verify(dataSourceMock).add(0, fakeUser);
    }

    @Test
    public void testSaveEditedUserCase() {
        User fakeUser = new User();
        fakeUser.setId(1);
        when(dataSourceMock.get(1)).thenReturn(new User());
        repository.save(fakeUser);
        Mockito.verify(dataSourceMock).get(1);
    }

    @Test
    public void testGetUsersCase() {
        assertEquals(dataSourceMock, repository.getUsers());
    }

}
