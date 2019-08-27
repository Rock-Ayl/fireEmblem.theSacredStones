package start;

import common.animation.Cursor;
import common.input.KeyInput;

import javax.swing.*;

/**
 * 准备开发
 */
public class Main {

    //人物光标
    public static Cursor CharacterCursor;
    //界面面板+大小
    public static JFrame Frame;
    public static int Width = 400;
    public static int Height = 300;

    public static void main(String[] args) {

        //创建界面-边框及标题
        Frame = new JFrame("火焰之纹章-圣光之魔石");
        Frame.pack();
        //设置界面宽高
        Frame.setSize(Width, Height);
        //设置界面是否可见
        Frame.setVisible(true);
        //设置用户单击窗口的关闭按钮时程序执行的操作(关系窗口)
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置键盘监听器
        Frame.addKeyListener(new KeyInput());

        //增加一个光标实例并启动
        CharacterCursor = Cursor.VOID(Frame, 200, 100, 100, 32, 32);

    }

}
