/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.student_hub;

import com.htp.entity.User;
import com.htp.util.JdbcUtils;
import java.util.List;

/**
 *
 * @author htphu
 */
public class Main {
    public static void main(String[] args) {
        String query = "SELECT * FROM Users";
        List<User> users = JdbcUtils.executeQueryList(query, User.class);
        System.out.println(users.size());
        for (var user : users) {
            System.out.println(user.getUsername());
            for (var role : user.getRoles()) {
                System.out.println(role.name());
            }
        }
    }
}
