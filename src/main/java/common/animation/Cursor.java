package common.animation;

import common.Const;
import common.util.FileUtil;

import javax.swing.*;
import java.util.HashMap;

/**
 * created by Rock-Ayl on 2019-8-27
 * 一个光标实体demo
 */
public class Cursor implements Runnable {

    //线程是否运行
    boolean isRun;
    //光标图片组
    HashMap<Integer, ImageIcon> imgMap;
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

    //初始化图片组
    private void initImgMap() {
        imgMap = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            //组装
            imgMap.put(i, new ImageIcon(FileUtil.setNumForFile(Const.CursorPath, i)));
        }
    }

    //获取光标图片
    private ImageIcon getImg() {
        //获取图片
        ImageIcon img = imgMap.get(num);
        //编号++
        num++;
        if (num >= 9) {
            num = 1;
        }
        return img;
    }

    //初始化光标线程对象
    public Cursor(JFrame frame, int time, int x, int y, int w, int h) {
        //初始化图片组
        initImgMap();
        //其他参数
        this.isRun = true;
        this.time = time;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.label = VOIDCursor();
    }

    //初始化光标线程对象
    public static Cursor VOID(JFrame frame, int time, int x, int y, int w, int h) {
        return new Cursor(frame, time, x, y, w, h).start();
    }

    //启动该实例线程
    public Cursor start() {
        //将组件组装至面板中
        frame.add(this.label);
        //启动线程
        new Thread(this).start();
        return this;
    }

    //启动该实例线程
    public void stop() {
        //停止线程
        isRun = false;
        //面板删除组件
        frame.remove(label);
    }

    //创建一个新光标
    private JLabel VOIDCursor() {
        //组件
        JLabel cursor = new JLabel(getImg());
        //设置组件 x y 轴  宽高
        cursor.setBounds(x, y, w, h);
        //返回光标
        return cursor;
    }

    @Override
    public void run() {
        try {
            while (isRun) {
                //光标刷新延迟
                Thread.sleep(time);
                //刷新
                Refresh();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //刷新状态
    public void Refresh() {
        //删除旧光标
        frame.remove(label);
        //新光标
        label = VOIDCursor();
        //放入面板
        frame.add(label);
        //刷新面板
        frame.repaint();
    }

    //上移
    public void moveUp() {
        y = y - 10;
        if (isRun) {
            Refresh();
        }
    }

    //下移
    public void moveDown() {
        y = y + 10;
        if (isRun) {
            Refresh();
        }
    }

    //左移
    public void moveLeft() {
        x = x - 10;
        if (isRun) {
            Refresh();
        }
    }

    //右移
    public void moveRight() {
        x = x + 10;
        if (isRun) {
            Refresh();
        }
    }

}
