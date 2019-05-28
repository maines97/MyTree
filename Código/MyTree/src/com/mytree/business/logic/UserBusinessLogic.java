/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic;

import com.mytree.business.model.User;
import java.util.Collection;

public interface UserBusinessLogic extends BusinessLogic<User> {
    
    Collection<User> getUsers(boolean lazyLoad);

    User getUser(int id);
    
}
