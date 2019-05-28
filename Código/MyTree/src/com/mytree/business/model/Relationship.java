/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.model;

import com.mytree.utils.Constants;

public enum Relationship {
    NONE,
    PARENT,
    CHILD,
    MARRIED,
    UNION;

    public Relationship getOpposite() {
        Relationship opposite;
        switch (this) {
            case PARENT:
                opposite = CHILD;
                break;
            case CHILD:
                opposite = PARENT;
                break;
            default:
                opposite = this;
        }
        return opposite;
    }

    public static Relationship getRelationship(final String relationship) {
        Relationship result = NONE;
        switch (relationship) {
            case Constants.FATHER:
            case Constants.MOTHER:
                result = PARENT;
                break;
            case Constants.CHILD:
                result = CHILD;
                break;
            case Constants.MARRIED:
                result = MARRIED;
                break;
            case Constants.UNION:
                result = UNION;
                break;
            default:
                break;
        }
        return result;
    }
}
