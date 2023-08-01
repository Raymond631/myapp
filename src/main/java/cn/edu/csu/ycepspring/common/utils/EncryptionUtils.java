package cn.edu.csu.ycepspring.common.utils;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class EncryptionUtils {
    private static final Argon2PasswordEncoder argon2PasswordEncoder = new Argon2PasswordEncoder();

    public static String encode(String raw) {
        return argon2PasswordEncoder.encode(raw);
    }

    public static boolean matches(String raw, String secret) {
        return argon2PasswordEncoder.matches(raw, secret);
    }
}
