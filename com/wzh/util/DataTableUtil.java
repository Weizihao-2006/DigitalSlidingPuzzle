package com.wzh.util;

import java.awt.*;

public class DataTableUtil {
    // 构造方法私有化
    private DataTableUtil() {
    }

    // 找到空位的坐标并返回
    public static Point FindEmptyPos(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] == data.length * data.length) return new Point(i, j);
            }
        }
        return null;
    }

    // 判断有没有成功
    public static boolean JudgeWin(int[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] != data.length * i + j + 1) return false;
            }
        }

        return true;
    }

    // 判断选中的坐标是否合理

    // 对data里的数据进行改动
    public static void ChangeData(Point ClickPoint, int[][] data) {
        Point emptyPos = FindEmptyPos(data);

        // 交换data[p.x][p.y]和data[emptyPos.x][emptyPos.y]
        if (emptyPos != null) {
            int temp = data[ClickPoint.x][ClickPoint.y];
            data[ClickPoint.x][ClickPoint.y] = data[emptyPos.x][emptyPos.y];
            data[emptyPos.x][emptyPos.y] = temp;
        }
    }
}
