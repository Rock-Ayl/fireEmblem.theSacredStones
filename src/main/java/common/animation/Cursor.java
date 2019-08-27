package common.animation;

import common.Const;
import common.util.FileUtil;

import javax.swing.*;

/**
 * created by Rock-Ayl on 2019-8-27
 * 一个光标实体
 */
public class Cursor implements Runnable {

    //光标放置的面板
    JFrame frame;
    //光标对象
    JLabel label;
    //光标图标编号
    int num = 1;
    //光标刷新时间
    int time;
    //光标x轴坐标
    int x;
    //光标y轴坐标
    int y;
    //光标宽
    int w;
    //光标高
    int h;

    //初始化光标线程对象
    public Cursor(JFrame frame, int time, int x, int y, int w, int h) {
        this.time = time;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.label = VOIDCursor();
        frame.add(this.label);

    }

    //创建一个光标
    private JLabel VOIDCursor() {
        //找到当前编号的光标图片
        ImageIcon img = new ImageIcon(FileUtil.setNumForFile(Const.CursorPath, num));
        //编号++
        num++;
        if (num >= 9) {
            num = 1;
        }
        //组件
        JLabel cursor = new JLabel(img);
        //设置组件 x y 轴  宽高
        cursor.setBounds(x, y, w, h);
        //返回光标
        return cursor;
    }

    @Override
    public void run() {
        try {
            while (true) {
                //光标刷新延迟
                Thread.sleep(time);
                //删除旧光标
                frame.remove(label);
                //新光爆
                label = VOIDCursor();
                //放入面板
                frame.add(label);
                //刷新面板
                frame.setVisible(true);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
