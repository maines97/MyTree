/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess;

import com.mytree.business.model.User;
import java.util.Collection;

public interface UserRepository
        extends Repository<User> {

    Collection<User> getUsers();
    
}
