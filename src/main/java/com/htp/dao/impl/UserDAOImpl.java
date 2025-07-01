/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.dao.impl;

import com.htp.dao.UserDAO;
import com.htp.entity.User;
import com.htp.util.JdbcUtil;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author htphu
 */
public class UserDAOImpl implements UserDAO {

    @Override
    public Optional<User> create(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<User> findById(String id) {
        String sql = "SELECT * FROM Users WHERE username=?";
        return JdbcUtil.executeQueryOne(sql, User.class, id);
    }

    
    
}
