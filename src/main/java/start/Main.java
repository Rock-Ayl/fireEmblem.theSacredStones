package start;

import common.animation.Cursor;
import common.input.KeyInput;

import javax.swing.*;

/**
 * 准备开发
 */
public class Main {

    //光标
    public static Cursor userCursor;
    //界面
    public static JFrame frame;

    public static void main(String[] args) {

        //创建界面-边框及标题
        frame = new JFrame("火焰之纹章-圣光之魔石");
        frame.pack();
        //设置界面宽高
        frame.setSize(400, 300);
        //设置界面是否可见
        frame.setVisible(true);
        //设置用户单击窗口的关闭按钮时程序执行的操作(关系窗口)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置键盘监听器
        frame.addKeyListener(new KeyInput());

        //增加一个光标实例并启动
        userCursor = Cursor.VOID(frame, 200, 100, 100, 32, 32);

    }

}
