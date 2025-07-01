/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.controller;

/**
 *
 * @author htphu
 */
public class AppController {
    private LoginController loginController;
    private DashboardController dashboardController;
    
    public void start() {
        loginController = new LoginController(this);
        loginController.getView().setVisible(true);
    }
    
    public void login() {
        loginController.getView().setVisible(false);
        dashboardController = new DashboardController(this);
        dashboardController.getView().setVisible(true);
    }
}
