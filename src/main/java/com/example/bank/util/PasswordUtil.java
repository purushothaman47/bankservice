
package com.example.bank.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class PasswordUtil {

    private static final Logger log =
            LoggerFactory.getLogger(PasswordUtil.class);

    public static String hash(String value) {
        try {
            log.debug("Password hashing started");

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] b = md.digest(value.getBytes());

            StringBuilder sb = new StringBuilder();

            for (byte x : b) {

                sb.append(String.format("%02x", x));

            }
            log.debug("Password hashing completed");

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
