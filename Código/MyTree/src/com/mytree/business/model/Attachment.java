/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.model;

import java.util.Date;
import java.util.List;

public final class Attachment {

    private static final int HASH = 5;
    private static final int HASH_RANDOM = 53;

    private int id;
    private boolean familyOwner;
    private List<Integer> owners;
    private String name;
    private String history;
    private String attachmentPath;
    private Date fromDate;
    private Date toDate;

    public Attachment() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public boolean isFamilyOwner() {
        return familyOwner;
    }

    public void setFamilyOwner(final boolean familyOwner) {
        this.familyOwner = familyOwner;
    }

    public List<Integer> getOwners() {
        return owners;
    }

    public void setOwners(final List<Integer> owners) {
        this.owners = owners;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(final String history) {
        this.history = history;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(final String attachmentPath) {
        this.attachmentPath = attachmentPath;
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

    @Override
    public boolean equals(final Object other) {
        if (other == null || !(other instanceof Attachment)) {
            return false;
        }

        Attachment otherAttachment = (Attachment) other;
        return id == otherAttachment.getId();
    }

    @Override
    public int hashCode() {
        int hash = HASH;
        hash = HASH_RANDOM * hash + this.id;
        return hash;
    }
}
