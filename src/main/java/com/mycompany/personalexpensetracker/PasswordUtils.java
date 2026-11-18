package com.mycompany.personalexpensetracker;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // ✅ Hash a plain password with BCrypt
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    // ✅ Check password (supports BCrypt + legacy plain text)
    public static boolean checkPassword(String plainPassword, String storedPassword) {
        if (plainPassword == null || storedPassword == null) {
            return false;
        }

        try {
            // If stored password is BCrypt format
            if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")) {
                return BCrypt.checkpw(plainPassword, storedPassword);
            } else {
                // Fallback → treat as old plain text
                return plainPassword.equals(storedPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

