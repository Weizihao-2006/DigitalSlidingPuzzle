package com.wzh.ui;

import com.wzh.classes.NumLabel;
import com.wzh.util.DataTableUtil;
import com.wzh.util.MapUtil;
import com.wzh.util.NumLabelUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import static com.wzh.util.MapUtil.*;

public class GameJFrame extends JFrame {

    // 用于管理数据
    private int[][] data = new int[4][4];

    public GameJFrame() {
        this(GAME_WIDTH, GAME_HEIGHT);
    }

    public GameJFrame(int width, int height) {
        // 取消默认坐标
        this.setLayout(null);

        initFrame(width, height);
        initJMenuBar();
        // initData();
        // initPic();
        restartGame();

        // 最后再把界面设为可视化
        this.setVisible(true);
    }

    // 初始化界面
    private void initFrame(int width, int height) {
        this.setSize(width, height);
        this.setTitle("游戏界面");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    // 初始化菜单
    private void initJMenuBar() {
        JMenuBar bar = new JMenuBar();

        // 菜单项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutUsJMenu = new JMenu("关于我们");

        // 小项
        JMenuItem restartItem = new JMenuItem("重新开始");
        restartItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        JMenuItem reloginItem = new JMenuItem("重新登录");

        JMenuItem exitItem = new JMenuItem("退出游戏");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JMenuItem aboutUsItem = new JMenuItem("公众号");

        // 把小项添加到菜单项中
        functionJMenu.add(restartItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(exitItem);

        aboutUsJMenu.add(aboutUsItem);

        // 最后把菜单项添加到bar中
        bar.add(functionJMenu);
        bar.add(aboutUsJMenu);

        this.setJMenuBar(bar);
    }

    // 初始化数据
    private void initData() {
        int[] tempArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int index;

        // 打乱temp里的数据
        for (int i = 0; i < tempArr.length; i++) {
            Random random = new Random();
            index = random.nextInt(tempArr.length);

            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        // 把tempArr里的数据复制到data里
        index = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j] = tempArr[index];
                index++;
            }
        }

        // 测试代码
        // data = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 16, 15}};
    }

    // 初始化图片
    private void initPic() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                // 不加载最后一个数
                int num = data[i][j];
                if (num == 16) continue;

                NumLabel label = new NumLabel("" + num);
                // 设置透明度
                label.setOpaque(true);

                // 背景色
                label.setBackground(Color.GRAY);
                //前景色
                label.setForeground(Color.WHITE);


                label.setSize(PIC_SIZE, PIC_SIZE);
                Font font = new Font("Arial", Font.PLAIN, 30);
                label.setFont(font);
                label.setHorizontalAlignment(SwingConstants.CENTER);

                // 设置位置
                label.setLocation(MapUtil.ConvertMapPosToActPos(new Point(i, j)));
                label.setPoint(new Point(i, j));

                // 设置名字
                label.setName("Label" + num);

                // 设置监听器
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 移动方块
                        NumLabelUtil.MoveLabel(label, data);

                        // 移动完检查游戏是否结束
                        if (DataTableUtil.JudgeWin(data)) {
                            // 取消所有监听器
                            disableAllListeners();
                            // 放出胜利提醒
                            showWinPane();
                        }

                        // 更新画面
                        SwingUtilities.updateComponentTreeUI(GameJFrame.this);
                    }
                });

                this.getContentPane().add(label);
            }
        }
    }

    // 重新开始游戏
    private void restartGame() {
        // 正好可以把幕布也删掉
        this.getContentPane().removeAll();

        // 初始化数据
        initData();
        initPic();

        this.getContentPane().repaint();
    }

    // 取消所有方块的监听器
    private void disableAllListeners() {
        Component[] components = this.getContentPane().getComponents();
        for (Component comp : components) {
            if (comp instanceof NumLabel) {
                MouseListener[] listeners = comp.getMouseListeners();
                for (MouseListener lis : listeners) {
                    comp.removeMouseListener(lis);
                }
            }
        }
    }

    // 展示胜利动画
    private void showWinPane() {
        // 创建一个自定义绘制背景的子类
        JPanel mask = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                // 这里是实现半透明的关键
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 0, 0, 150)); // 黑色的 RGBA，150 是透明度(0-255)
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
                super.paintComponent(g);
            }
        };

        // 设置背景透明
        mask.setOpaque(false);
        mask.setBounds(0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
        mask.setLayout(new BorderLayout());

        // 添加文字提醒
        JLabel winText = new JLabel("YOU WIN!");
        winText.setForeground(Color.YELLOW);
        winText.setFont(new Font("微软雅黑", Font.BOLD, 50));
        winText.setHorizontalAlignment(SwingConstants.CENTER);
        mask.add(winText);

        // 加到ContentPane里
        this.getContentPane().add(mask);
        this.getContentPane().setComponentZOrder(mask, 0);

        // 刷新
        this.getLayeredPane().revalidate();
        this.getLayeredPane().repaint();
    }
}
