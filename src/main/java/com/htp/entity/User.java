/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.entity;

import com.htp.annotations.Column;
import com.htp.enums.Role;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author htphu
 */
@Data
public class User {
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name = "role_names_json")
    private List<Role> roles = new ArrayList<>();
}
