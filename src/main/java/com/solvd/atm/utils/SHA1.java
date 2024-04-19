package com.solvd.atm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {

    private static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static String encryptPin(String pin) {
        StringBuilder hexStringBuilder = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.reset();
            byte[] encryptedPin = md.digest(pin.getBytes("UTF-8"));
            for (byte b : encryptedPin) {
                hexStringBuilder.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOGGER.debug(e.getMessage());
        }
        return hexStringBuilder.toString();
    }
}
