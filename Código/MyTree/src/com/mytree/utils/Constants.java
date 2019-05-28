/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.utils;

public final class Constants {

    private Constants() {
    }

    public static final Integer DEFAULT_MAX_USERS_COUNT = 1000;

    // Resources
    public static final String USER_PROFILE = "file:resources/images/user-profile.png";

    // Commons
    public static final String APP_TITLE = "MyTree";

    // Users
    public static final String EDIT_USER = "Editar usuario";
    public static final String NEW_USER = "Nuevo usuario";
    public static final String SELECT_USER = "Por favor seleccione un usuario.";
    public static final String WITHOUT_SELECTION = "Sin selección";
    public static final String WITHOUT_USER_SELECTED = "No ha seleccionado usuario";
    public static final String USERNAME_REQUIRED = "El usuario no puede estar vacio";
    public static final String USER_ERROR = "";
    public static final String ADD_USER_ERROR = "";
    public static final String USER_DETAILS = "Ficha de usuario";

    // Users Relationships
    public static final String EDIT_USER_RELATIONSHIP = "Editar relación";
    public static final String NEW_USER_RELATIONSHIP = "Nueva relación";
    public static final String FATHER = "Padre";
    public static final String MOTHER = "Madre";
    public static final String CHILD = "Hijo/a";
    public static final String MARRIED = "Matrimonio";
    public static final String UNION = "Unión";
    public static final String USER_RELATIONSHIP_ERROR = "";
    public static final String ADD_USER_RELATIONSHIP_ERROR = "";
    public static final String RELATIONSHIP_MUST_BE_DEFINED = "Se debe "
            + "completar la relación. Elije las personas y el tipo de "
            + "relación de las mismas.";

    // Attachments
    public static final String PEOPLE = "Personas";
    public static final String NEW_ATTACHMENT = "Nuevo adjunto";
    public static final String FAMILY = "Familia";
    public static final String ATTACHMENTS = "Adjuntos";
    public static final String NAME_REQUIRED = "El nombre no puede estar vacio";
    public static final String ATTACHMENT_ERROR = "";
    public static final String ADD_ATTACHMENT_ERROR = "";
    public static final String OWNERS_MUST_BE_SELECTED = "Se deben elegir los "
            + "dueños de este adjunto. Seleccione al menos un dueño.";
    
    // Errors
    public static final String DOCUMENT_ERROR = "";
    public static final String DOCUMENT_NOT_FOUND = "";

}
