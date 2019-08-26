package start;

import common.Const;
import common.input.KeyInput;

import javax.swing.*;

/**
 * 准备开发
 */
public class Main {

    public static void main(String[] args) {

        ImageIcon img = new ImageIcon(Const.CursorPath);
        //组件
        JLabel label = new JLabel(img);
        //设置水平对齐
        label.setHorizontalAlignment(SwingConstants.CENTER);
        //设置垂直对齐
        label.setVerticalAlignment(SwingConstants.CENTER);
        //设置组件 x y 轴  宽高
        label.setBounds(0, 0, 128, 128);

        //容器
        JPanel panel = new JPanel();
        //组件添加标签
        panel.add(label);
        //组件布局
        panel.setLayout(null);

        //创建界面-边框及标题
        JFrame frame = new JFrame("火焰之纹章-圣光之魔石");
        //界面添加内容面板
        frame.getContentPane().add(panel);
        frame.pack();
        //设置界面宽高
        frame.setSize(400, 300);
        //设置界面是否可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置键盘监听器
        frame.addKeyListener(new KeyInput());


    }

}
