/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.app;

import com.htp.controller.AppController;
import com.htp.ui.UIConfig;

/**
 *
 * @author htphu
 */
public class Main {
    public static void main(String[] args) {
        UIConfig.apply();
        new AppController().start();
    }
}
