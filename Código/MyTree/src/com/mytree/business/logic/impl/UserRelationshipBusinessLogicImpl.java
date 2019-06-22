/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic.impl;

import com.mytree.business.dataaccess.UserRelationshipRepository;
import com.mytree.business.logic.UserRelationshipBusinessLogic;
import com.mytree.business.model.Relationship;
import com.mytree.business.model.UserRelationship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class UserRelationshipBusinessLogicImpl
        extends BusinessLogicBase<UserRelationshipRepository>
        implements UserRelationshipBusinessLogic {

    public UserRelationshipBusinessLogicImpl(final UserRelationshipRepository repository) {
        super(repository);
    }

    @Override
    public void save(final UserRelationship entity) {
        getRepository().save(entity);
    }

    @Override
    public Collection<UserRelationship> getUserRelationships(final int id, 
            final List<Relationship> relationships) {
        
        Collection<UserRelationship> userRealationships = getRepository().getRealationships(id);
        List<UserRelationship> result = new ArrayList<>();
        userRealationships.forEach(userRelationship -> {
            if (relationships.contains(userRelationship.getRelationship())) {
                result.add(userRelationship);
            }
        });
        return result;
    }

    @Override
    public Collection<UserRelationship> getRelationships(final List<Relationship> relationships) {
        return getRepository().getRealationships(relationships);
    }

}
