package com.wzh.classes;

import javax.swing.*;
import java.awt.*;

public class NumLabel extends JLabel {
    private Point point;

    public NumLabel(String s) {
        super(s);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setPoint(int x, int y) {
        this.point.x = x;
        this.point.y = y;
    }
}
