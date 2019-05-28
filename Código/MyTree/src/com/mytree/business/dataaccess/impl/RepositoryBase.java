/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess.impl;

public abstract class RepositoryBase<TDataSource> {

    private final TDataSource dataSource;

    public RepositoryBase(final TDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public final TDataSource getDataSource() {
        return dataSource;
    }

}
