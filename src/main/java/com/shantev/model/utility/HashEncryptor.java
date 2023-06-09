package com.shantev.model.utility;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashEncryptor {
    public static String DEFAULT_SALT = "abc1";
    public static String getPasswordHash(String password, String salt) {
        String saltedPassword = password + salt;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] SHA256Hash = md.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        BigInteger SHA256HashInteger = new BigInteger(1, SHA256Hash);
        String SHA256HashHexString = SHA256HashInteger.toString(16);
        return SHA256HashHexString;
    }
}