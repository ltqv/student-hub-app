/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.htp.view;

import com.htp.ui.GbcBuilder;
import com.htp.ui.LabelFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author htphu
 */
public class LoginView extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chkRemember;
    private JButton btnLogin, btnExit;

    public LoginView() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(400, 200));
        setResizable(false);

        initComponents();
        setupLayout();
    }

    private void initComponents() {
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        chkRemember = new JCheckBox();
        btnLogin = new JButton("Đăng nhập");
        btnExit = new JButton("Thoát");
    }

    private void setupLayout() {
        this.setLayout(new BorderLayout(10, 10));

        // Title
        this.add(LabelFactory.createTitle("Đăng nhập"), BorderLayout.NORTH);

        // Form
        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.add(LabelFactory.createField("Tên đăng nhập"),
                GbcBuilder.builder()
                        .at(0, 0)
                        .anchor(GridBagConstraints.WEST)
                        .insets(5)
                        .build());
        pnlForm.add(LabelFactory.createField("Mật khẩu"),
                GbcBuilder.builder()
                        .at(0, 1)
                        .anchor(GridBagConstraints.WEST)
                        .insets(5)
                        .build());
        pnlForm.add(txtUsername,
                GbcBuilder.builder()
                        .at(1, 0)
                        .fill(GridBagConstraints.HORIZONTAL)
                        .weight(1.0)
                        .gridwidth(2)
                        .insets(5)
                        .build());
        pnlForm.add(txtPassword,
                GbcBuilder.builder()
                        .at(1, 1)
                        .fill(GridBagConstraints.HORIZONTAL)
                        .weight(1.0)
                        .gridwidth(2)
                        .insets(5)
                        .build());
        pnlForm.add(chkRemember,
                GbcBuilder.builder()
                        .at(1, 2)
                        .anchor(GridBagConstraints.WEST)
                        .insets(0, 10)
                        .build());
        pnlForm.add(LabelFactory.create("Ghi nhớ đăng nhập"),
                GbcBuilder.builder()
                        .at(2, 2)
                        .anchor(GridBagConstraints.WEST)
                        .build());
        this.add(pnlForm, BorderLayout.CENTER);

        // Action
        JPanel pnlAction = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        pnlAction.add(btnLogin);
        pnlAction.add(btnExit);
        this.add(pnlAction, BorderLayout.SOUTH);
    }
    
    public void clear() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnExit() {
        return btnExit;
    }

    public JCheckBox getChkRemember() {
        return chkRemember;
    }

}
