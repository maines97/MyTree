/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic.impl;

import com.mytree.business.dataaccess.Repository;

public abstract class BusinessLogicBase<TRepository extends Repository> {

    private final TRepository repository;

    public BusinessLogicBase(final TRepository repository) {
        this.repository = repository;
    }

    public final TRepository getRepository() {
        return repository;
    }
    
}
