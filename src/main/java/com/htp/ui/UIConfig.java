/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author htphu
 */
public class UIConfig {

    public static void apply() {
        try {
            // Look and Feel
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
            
            // Cấu hình toàn cục
            applyCommonStyle();

        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(UIConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void applyCommonStyle() {
        // Font chung
        Font font = new Font("Courier New", Font.PLAIN, 13);
        UIManager.put("defaultFont", font);

        // Bo góc và viền focus               
        UIManager.put("Component.focusWidth", 1);          // Viền focus mỏng
        UIManager.put("Component.innerFocusWidth", 0);     // Không lấn vào trong

        // Padding cho TextField, PasswordField, v.v.
        Insets padding = new Insets(4, 10, 4, 10);
        UIManager.put("TextComponent.padding", padding);
    }
}
