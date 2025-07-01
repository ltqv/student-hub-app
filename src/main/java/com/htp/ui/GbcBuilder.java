/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.ui;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 *
 * @author htphu
 */
public class GbcBuilder {

    private final GridBagConstraints gbc;
    
    private GbcBuilder() {
        gbc = new GridBagConstraints();
    }
    
    public static GbcBuilder builder() {
        return new GbcBuilder();
    }

    public GbcBuilder at(int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        return this;
    }

    public GbcBuilder gridwidth(int w) {
        gbc.gridwidth = w;
        return this;
    }
    
    public GbcBuilder gridheight(int h) {
        gbc.gridheight = h;
        return this;
    }

    public GbcBuilder fill(int fill) {
        gbc.fill = fill;
        return this;
    }

    public GbcBuilder anchor(int anchor) {
        gbc.anchor = anchor;
        return this;
    }
    
    public GbcBuilder weight(double xy) {
        return weight(xy, xy);
    }

    public GbcBuilder weight(double x, double y) {
        gbc.weightx = x;
        gbc.weighty = y;
        return this;
    }

    public GbcBuilder insets(int all) {
        gbc.insets = new Insets(all, all, all, all);
        return this;
    }
    
    public GbcBuilder insets(int vertical, int horizontal) {
        gbc.insets = new Insets(vertical, horizontal, vertical, horizontal);
        return this;
    }

    public GbcBuilder insets(int top, int left, int bottom, int right) {
        gbc.insets = new Insets(top, left, bottom, right);
        return this;
    }

    public GridBagConstraints build() {
        return gbc;
    }
}
