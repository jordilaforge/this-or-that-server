
package com.jordilaforge.thisorthatserver.service;

import org.hashids.Hashids;

class CodeCreator {

    static String createCode(long surveyIndex) {
        String stringAlphabet = "abcdefghijklmnopqrstuvwxyz1234567890";
        int minHashLength = 4;
        String salt = "thisorthat";
        Hashids hashids = new Hashids(salt, minHashLength, stringAlphabet);
        return hashids.encode(surveyIndex);
    }
}
