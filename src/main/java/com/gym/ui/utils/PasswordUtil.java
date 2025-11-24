package com.gym.ui.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

public class PasswordUtil {
    public static String hash(String raw) {
        try {
            var md = MessageDigest.getInstance("SHA-256");            // ask jdk for SHA-256 digest engine
            byte[] digest = md.digest(raw.getBytes(StandardCharsets.UTF_8));   // Convert the raw password string to bytes (UTF-8). Feed to SHA-256; you get a 32-byte hash.
            return HexFormat.of().formatHex(digest);                           // Turn those 32 bytes into a lowercase hex string like "a3b4...ff" for storage.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static boolean verify(String raw, String hash) {
        return hash(raw).equals(hash);
    }
}