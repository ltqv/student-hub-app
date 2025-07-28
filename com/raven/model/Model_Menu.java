package com.raven.model;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public Model_Menu() {
    }

    private String icon;
    private String name;
    private MenuType type;

   public ImageIcon toIcon() {
    String path = "/com/raven/icon/" + icon + ".png";
    URL location = getClass().getResource(path);
    if (location == null) {
        System.out.println("Không tìm thấy ảnh: " + path); // debug
        return null;
    }
    return new ImageIcon(location);
}


    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
