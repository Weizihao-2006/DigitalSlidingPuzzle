package com.wzh.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    private static final int REGISTER_WIDTH = 488;
    private static final int REGISTER_HEIGHT = 500;

    public RegisterJFrame() {
        this(REGISTER_WIDTH, REGISTER_HEIGHT);
    }

    public RegisterJFrame(int width, int height) {
        this.setSize(width, height);
        this.setTitle("注册界面");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
