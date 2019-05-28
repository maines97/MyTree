/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mytree.utils;

public final class StringUtils {

    private StringUtils() {
    }

    public static String replaceLast(final String str, final String oldStr, final String newStr) {
        String result = str;
        if (str.contains(oldStr)) {
            int lenght = oldStr.length();
            int lastIndex = str.lastIndexOf(oldStr);
            StringBuilder resultBuilder = new StringBuilder(str.substring(0, lastIndex));
            resultBuilder.append(newStr);
            resultBuilder.append(str.substring(lastIndex + lenght));
            result = resultBuilder.toString();
        }
        return result;
    }

}
