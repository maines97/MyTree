/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic.impl;

import com.mytree.business.dataaccess.UserRepository;
import com.mytree.business.logic.UserBusinessLogic;
import com.mytree.business.logic.UserRelationshipBusinessLogic;
import com.mytree.business.model.Relationship;
import com.mytree.business.model.User;
import com.mytree.business.model.UserRelationship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class UserBusinessLogicImpl
        extends BusinessLogicBase<UserRepository>
        implements UserBusinessLogic {

    private final UserRelationshipBusinessLogic userRelationshipBusinessLogic;

    public UserBusinessLogicImpl(final UserRepository repository,
            final UserRelationshipBusinessLogic userRelationshipBusinessLogic) {
        super(repository);
        this.userRelationshipBusinessLogic = userRelationshipBusinessLogic;
    }

    @Override
    public void save(final User user) {
        getRepository().save(user);
    }

    @Override
    public Collection<User> getUsers(final boolean lazy) {
        Collection<User> users = getRepository().getUsers();
        if (!lazy) {
            users.forEach(user -> {
                user.setParents(getParents(user.getId()));
                user.setSpouses(getSpouses(user.getId()));
                user.setChildren(getChildren(user.getId()));
            });
        }
        return users;
    }

    @Override
    public User getUser(final int id) {
        for (User user : getUsers(true)) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

    private Collection<UserRelationship> getParents(final int id) {
        List<Relationship> relationships = new ArrayList<>();
        relationships.add(Relationship.PARENT);
        return userRelationshipBusinessLogic.getUserRelationships(id, relationships);
    }

    private Collection<UserRelationship> getChildren(final int id) {
        List<Relationship> relationships = new ArrayList<>();
        relationships.add(Relationship.CHILD);
        return userRelationshipBusinessLogic.getUserRelationships(id, relationships);
    }

    private Collection<UserRelationship> getSpouses(final int id) {
        List<Relationship> relationships = new ArrayList<>();
        relationships.add(Relationship.MARRIED);
        relationships.add(Relationship.UNION);
        return userRelationshipBusinessLogic.getUserRelationships(id, relationships);
    }

}
