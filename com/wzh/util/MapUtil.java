package com.wzh.util;

import java.awt.*;

public class MapUtil {
    public static final int PIC_SIZE = 105;
    public static final int GAP_SIZE = 5;
    public static final int GAME_WIDTH = 4*PIC_SIZE+5*GAP_SIZE;
    public static final int GAME_HEIGHT = 500;    

    private MapUtil() {
    }

    public static Point ConvertMapPosToActPos(Point p) {
        return new Point((PIC_SIZE + GAP_SIZE) * p.y, (PIC_SIZE + GAP_SIZE) * p.x);
    }

    public static Point ConvertMapPosToActPos(int x, int y) {
        return new Point((PIC_SIZE + GAP_SIZE) * x, (PIC_SIZE + GAP_SIZE) * y);
    }
}
