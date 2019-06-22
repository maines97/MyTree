/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.model;

import java.io.Serializable;
import java.util.Date;

public final class UserRelationship implements Serializable {

    private Relationship relationship;
    private int relationshipOf;
    private int relationshipWith;
    private Date fromDate;
    private Date toDate;
    private boolean currentRelationship;

    public UserRelationship() {
        // CONSTRUCTOR
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(final Relationship relationship) {
        this.relationship = relationship;
    }

    public int getRelationshipOf() {
        return relationshipOf;
    }

    public void setRelationshipOf(final int relationshipOf) {
        this.relationshipOf = relationshipOf;
    }

    public int getRelationshipWith() {
        return relationshipWith;
    }

    public void setRelationshipWith(final int relationshipWith) {
        this.relationshipWith = relationshipWith;
    }

    public Date getFromDate() {
        return (Date) fromDate.clone();
    }

    public void setFromDate(final Date fromDate) {
        this.fromDate = (Date) fromDate.clone();
    }

    public Date getToDate() {
        return (Date) toDate.clone();
    }

    public void setToDate(final Date toDate) {
        this.toDate = (Date) toDate.clone();
    }

    public boolean isCurrentRelationship() {
        return currentRelationship;
    }

    public void setCurrentRelationship(final boolean currentRelationship) {
        this.currentRelationship = currentRelationship;
    }

}
