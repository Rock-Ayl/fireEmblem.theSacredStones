package common.util;

import common.input.KeyInput;

import javax.swing.*;

/**
 * created by Rock-Ayl on 2019-9-3
 * 项目中swing的工具类
 */
public class SwingUtil {

    //创建一个默认基础的界面
    public static JFrame VOIDJFrame(String title, int width, int height) {
        //创建界面-边框及标题
        JFrame frame = new JFrame(title);
        frame.pack();
        //设置界面宽高
        frame.setSize(width, height);
        //设置界面是否可见
        frame.setVisible(true);
        //设置用户单击窗口的关闭按钮时程序执行的操作(关系窗口)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置键盘监听器
        frame.addKeyListener(new KeyInput());
        //返回它
        return frame;
    }
}
