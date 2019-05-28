/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess.impl;

import com.mytree.business.dataaccess.UserRepository;
import com.mytree.business.model.User;
import java.util.Collection;
import java.util.List;

public final class UserRepositoryImpl
        extends RepositoryBase<List<User>>
        implements UserRepository {

    private int identity = 0;

    public UserRepositoryImpl(final List<User> dataSource) {
        super(dataSource);
    }

    @Override
    public void save(final User entity) {
        if (entity.getId() < 0) {
            entity.setId(identity);
            getDataSource().add(identity++, entity);
        } else {
            User user = getDataSource().get(entity.getId());
            user.setUsername(entity.getUsername());
            user.setFirstName(entity.getFirstName());
            user.setSecondName(entity.getSecondName());
            user.setFirstSurname(entity.getFirstSurname());
            user.setSecondSurname(entity.getSecondSurname());
            user.setPicturePath(entity.getPicturePath());
            user.setCountry(entity.getCountry());
            user.setBirthday(entity.getBirthday());
        }
    }

    @Override
    public Collection<User> getUsers() {
        return getDataSource();
    }

}
