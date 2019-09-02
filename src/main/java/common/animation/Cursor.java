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
    //是否清除线程
    boolean isClear;
    //光标图片组
    HashMap<Integer, ImageIcon> imgMap;
    //光标放置的面板
    JFrame frame;
    //光标对象
    JLabel label;
    //光标图标编号
    int num=1;
    //光标刷新时间 1000=1秒
    int time = 200;
    //光标x轴坐标
    int x;
    //光标y轴坐标
    int y;
    //光标图宽
    int w = 32;
    //光标图高
    int h = 32;
    //光标移动距离
    int mov = 10;
    //光标状态  0:无选中   1:敌方   2:我方
    int type;

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
        //根据光标type获取图片
        ImageIcon img;
        if (type == 0) {
            num++;
            if (num >= 5 || num < 1) {
                num = 1;
            }
        } else if (type == 1) {
            num++;
            if (num >= 9 || num < 5) {
                num = 5;
            }
        } else if (type == 2) {
            num = 9;
        } else {
            System.out.println("光标状态出错.");
        }
        img = imgMap.get(num);
        return img;
    }

    //初始化光标线程对象
    public Cursor(JFrame frame, int x, int y, int type) {
        //初始化图片组
        initImgMap();
        //其他参数
        this.isRun = false;
        this.isClear = false;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.type = type;
        this.label = VOIDCursor();
    }

    //初始化光标线程对象
    public static Cursor VOID(JFrame frame, int x, int y, int type) {
        return new Cursor(frame, x, y, type).start();
    }

    //启动该实例线程
    public Cursor start() {
        //如果处于停止状态
        if (isRun == false) {
            //将组件组装至面板中
            frame.add(label);
            //开始运转线程
            isRun = true;
            isClear = false;
            //启动线程
            new Thread(this).start();
        }
        return this;
    }

    //暂停该实例线程
    public void stop() {
        //如果处于启动状态
        if (isRun == true) {
            //停止线程
            isRun = false;
            //面板删除组件
            frame.remove(label);
        }
    }

    //清除该实例线程
    public void clear() {
        //打开清除开关
        isClear = true;
        //暂停
        stop();
        //保证线程关闭
        run();
    }

    //创建一个新光标
    private JLabel VOIDCursor() {
        //组件
        JLabel cursor = new JLabel(getImg());
        //设置组件 x Y 轴  宽高
        cursor.setBounds(x, y, w, h);
        //返回光标
        return cursor;
    }

    @Override
    public void run() {
        try {
            //线程是否继续运行
            while (isRun) {
                //光标刷新延迟
                Thread.sleep(time);
                //刷新
                Refresh();
            }
            //是否清除
            if (isClear) {
                label.setVisible(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //刷新状态,加锁,防止多帧
    public synchronized void Refresh() {
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
        if (isRun) {
            y = y - mov;
            Refresh();
        }
    }

    //下移
    public void moveDown() {
        if (isRun) {
            y = y + mov;
            Refresh();
        }
    }

    //左移
    public void moveLeft() {
        if (isRun) {
            x = x - mov;
            Refresh();
        }
    }

    //右移
    public void moveRight() {
        if (isRun) {
            x = x + mov;
            Refresh();
        }
    }

    //切换光标状态
    public void switchType(int type) {
        this.type = type;
    }

    //切换光标坐标
    public void switchXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
