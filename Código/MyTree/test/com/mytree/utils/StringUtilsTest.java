/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.utils;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public final class StringUtilsTest {
    
    public StringUtilsTest() {
    }

    @Test
    public void testReplaceLastSuccessCase() {
        String inital = "aabbabbaa";
        String expected = "aabbaccccaa";
        assertEquals(expected, StringUtils.replaceLast(inital, "bb", "cccc")); 
    }
    
    @Test
    public void testReplaceLastDoNothingCase() {
        String inital = "aabbabbaa";
        assertEquals(inital, StringUtils.replaceLast(inital, "bbb", "cccc")); 
    }
    
}
