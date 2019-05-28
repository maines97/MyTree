/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess;

import com.mytree.business.model.Relationship;
import com.mytree.business.model.UserRelationship;
import java.util.Collection;
import java.util.List;

public interface UserRelationshipRepository
        extends Repository<UserRelationship> {

    Collection<UserRelationship> getRealationships(int id);
    
    Collection<UserRelationship> getRealationships(List<Relationship> relationships);

}
