/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author htphu
 */
public class PasswordUtil {
    private static final int SALT_ROUNDS = 12;
    
    // Băm mật khẩu
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(SALT_ROUNDS));
    }
    
    // Kiểm tra mật khẩu nhập vào với mật khẩu đã băm
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
