/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.controller;

import com.htp.view.DashboardView;

/**
 *
 * @author htphu
 */
public class DashboardController {
    private final AppController app;
    private final DashboardView view;
    

    public DashboardController(AppController app) {
        this.app = app;
        view = new DashboardView();
    }

    public DashboardView getView() {
        return view;
    }
    
}
