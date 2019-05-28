/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.business.dataaccess;

import com.mytree.business.model.Attachment;
import java.util.Collection;

public interface AttachmentRepository extends Repository<Attachment> {
    
    Collection<Attachment> getAttachments();
    
}
