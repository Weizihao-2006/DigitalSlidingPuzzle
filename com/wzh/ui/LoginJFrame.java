package com.wzh.ui;

import javax.swing.*;

public class LoginJFrame extends JFrame {
    private static final int LOGIN_WIDTH = 488;
    private static final int LOGIN_HEIGHT = 430;

    public LoginJFrame() {
        this(LOGIN_WIDTH, LOGIN_HEIGHT);
    }

    public LoginJFrame(int width, int height) {
        this.setSize(width, height);
        this.setTitle("登录界面");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
