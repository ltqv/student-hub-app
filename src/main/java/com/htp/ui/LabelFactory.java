/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author htphu
 */
public class LabelFactory {
    // Label
    public static JLabel create(String text) {
        return new JLabel(text);
    }
    // Label Field
    public static JLabel createField(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Courier New", Font.PLAIN, 14));
        label.setForeground(Color.DARK_GRAY);
        return label;
    }
    
    // Label Title
    public static JLabel createTitle(String text) {
        JLabel label = new JLabel(text.toUpperCase());
        label.setFont(new Font("Courier New", Font.BOLD, 20));
        label.setForeground(new Color(30, 30, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }
}
