/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess.impl;

import com.mytree.business.dataaccess.UserRelationshipRepository;
import com.mytree.business.model.Relationship;
import com.mytree.business.model.UserRelationship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class UserRelationshipRepositoryImpl
        extends RepositoryBase<UserRelationship[][]>
        implements UserRelationshipRepository {

    public UserRelationshipRepositoryImpl(final UserRelationship[][] dataSource) {
        super(dataSource);
    }

    @Override
    public void save(final UserRelationship entity) {
        int of = entity.getRelationshipOf();
        int with = entity.getRelationshipWith();

        UserRelationship oppositeRelationship = new UserRelationship();
        oppositeRelationship.setRelationshipOf(with);
        oppositeRelationship.setRelationshipWith(of);
        oppositeRelationship.setFromDate(entity.getFromDate());
        oppositeRelationship.setToDate(entity.getToDate());
        oppositeRelationship.setRelationship(entity.getRelationship().getOpposite());
        oppositeRelationship.setCurrentRelationship(entity.isCurrentRelationship());

        getDataSource()[of][with] = entity;
        getDataSource()[with][of] = oppositeRelationship;
    }

    @Override
    public Collection<UserRelationship> getRealationships(final int id) {
        List<UserRelationship> result = new ArrayList<>();
        for (UserRelationship userRelationship : getDataSource()[id]) {
            if (userRelationship != null) {
                result.add(userRelationship);
            }
        }
        return result;
    }

    @Override
    public Collection<UserRelationship> getRealationships(final List<Relationship> relationships) {
        List<UserRelationship> result = new ArrayList<>();
        for (UserRelationship[] userRelationships : getDataSource()) {
            for (UserRelationship userRelationship : userRelationships) {
                if (userRelationship != null
                        && relationships.contains(userRelationship.getRelationship())) {
                    result.add(userRelationship);
                }
            }
        }
        return result;
    }
}
