package com.wzh.util;

import com.wzh.classes.NumLabel;

import java.awt.*;

public class NumLabelUtil {
    // 构造函数私有化
    private NumLabelUtil() {
    }

    public static void MoveLabel(NumLabel label, int[][] data) {
        Point emptyPos = DataTableUtil.FindEmptyPos(data);

        // 对data的改动，调用util里的方法
        DataTableUtil.ChangeData(label.getPoint(), data);

        // 移动
        if (emptyPos != null) {
            // 物理移动
            label.setLocation(MapUtil.ConvertMapPosToActPos(emptyPos));
            // 更新label的point属性
            label.setPoint(emptyPos.x, emptyPos.y);
        }

    }
}
