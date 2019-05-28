/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic;

import com.mytree.business.model.Relationship;
import com.mytree.business.model.UserRelationship;
import java.util.Collection;
import java.util.List;

public interface UserRelationshipBusinessLogic
        extends BusinessLogic<UserRelationship> {

    Collection<UserRelationship> getUserRelationships(int id, List<Relationship> relationships);

    Collection<UserRelationship> getRelationships(List<Relationship> relationships);

}
