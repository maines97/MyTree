/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.wiring;

import com.mytree.assembler.Assembler;
import com.mytree.business.logic.BusinessLogicLocator;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public final class WiringTest {

    public WiringTest() {
    }

    @Test
    public void testSomeMethod() {
        // Do assemble
        Assembler.getInstance().assemble();
        BusinessLogicLocator locator = BusinessLogicLocator.getInstance();

        // Assertions
        assertNotNull(locator.getUserBusinessLogic());
        assertNotNull(locator.getAttachmentBusinessLogic());
        assertNotNull(locator.getUserRelationshipBusinessLogic());
    }

}
