/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.controller;

import com.htp.dao.UserDAO;
import com.htp.dao.impl.UserDAOImpl;
import com.htp.ui.DialogUtil;
import com.htp.util.PasswordUtil;
import com.htp.view.LoginView;
import java.util.Optional;

/**
 *
 * @author htphu
 */
public class LoginController {

    private final AppController app;
    private final LoginView view;
    private final UserDAO userDAO;

    public LoginController(AppController app) {
        this.app = app;
        view = new LoginView();
        userDAO = new UserDAOImpl();
        initEvents();
    }

    private void initEvents() {
        view.getBtnLogin().addActionListener(l -> handleLogin());
        view.getBtnExit().addActionListener(l -> System.exit(0));
    }

    public LoginView getView() {
        return view;
    }

    private void handleLogin() {
        String username = view.getTxtUsername().getText();
        String password = new String(view.getTxtPassword().getPassword());
        var optionalUser = userDAO.findById(username);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            //if (PasswordUtil.verifyPassword(password, user.getPassword())) {
            if (password.equals(user.getPassword())) {
                DialogUtil.showMessage(view, "Đăng nhập thành công");
                app.login();
                if (!view.getChkRemember().isSelected()) {
                    view.clear();
                }
                return;
            }
        }
        DialogUtil.showMessage(view, "Đăng nhập thất bại");
    }

}
