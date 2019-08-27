package start;

import common.Const;
import common.animation.Cursor;
import common.input.KeyInput;

import javax.swing.*;

/**
 * 准备开发
 */
public class Main {

    public static void main(String[] args) {


        //创建界面-边框及标题
        JFrame frame = new JFrame("火焰之纹章-圣光之魔石");
        frame.pack();
        //设置界面宽高
        frame.setSize(400, 300);
        //设置界面是否可见
        frame.setVisible(true);
        //设置用户单击窗口的关闭按钮时程序执行的操作(关系窗口)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置键盘监听器
        frame.addKeyListener(new KeyInput());


        //增加一个光标线程1
        Cursor cur = new Cursor(frame, 500, 0, 0, 32, 32);
        new Thread(cur).start();

        //增加一个光标线程2
        Cursor cur2 = new Cursor(frame, 1000, 30, 30, 32, 32);
        new Thread(cur2).start();

    }

}
