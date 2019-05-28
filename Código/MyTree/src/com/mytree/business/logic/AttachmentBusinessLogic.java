/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.logic;

import com.mytree.business.model.Attachment;
import java.util.Collection;

public interface AttachmentBusinessLogic extends BusinessLogic<Attachment> {

    Collection<Attachment> getAttachments();
    
    Collection<Attachment> getAttachments(int userId);

}
