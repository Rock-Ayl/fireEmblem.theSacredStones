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
    boolean IsRun;
    //是否清除线程
    boolean IsClear;
    //光标图片组
    HashMap<Integer, ImageIcon> ImgMap;
    //光标放置的面板
    JFrame Frame;
    //光标对象
    JLabel Label;
    //光标图标编号
    int Num = 1;
    //光标刷新时间 1000=1秒
    int Time = 200;
    //光标x轴坐标
    int X;
    //光标y轴坐标
    int Y;
    //光标图宽
    int W = 32;
    //光标图高
    int H = 32;
    //光标移动距离
    int Mov = 10;
    //光标状态  0:无选中   1:敌方   2:我方
    int Type;

    //初始化图片组
    private void initImgMap() {
        ImgMap = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            //组装
            ImgMap.put(i, new ImageIcon(FileUtil.setNumForFile(Const.CursorPath, i)));
        }
    }

    //获取光标图片
    private ImageIcon getImg() {
        //根据光标type获取图片
        ImageIcon img;
        if (Type == 0) {
            Num++;
            if (Num >= 5 || Num < 1) {
                Num = 1;
            }
        } else if (Type == 1) {
            Num++;
            if (Num >= 9 || Num < 5) {
                Num = 5;
            }
        } else if (Type == 2) {
            Num = 9;
        } else {
            System.out.println("光标状态出错.");
        }
        img = ImgMap.get(Num);
        return img;
    }

    //初始化光标线程对象
    public Cursor(JFrame frame, int x, int y, int type) {
        //初始化图片组
        initImgMap();
        //其他参数
        this.IsRun = false;
        this.IsClear = false;
        this.Frame = frame;
        this.X = x;
        this.Y = y;
        this.Type = type;
        this.Label = VOIDCursor();
    }

    //初始化光标线程对象
    public static Cursor VOID(JFrame frame, int x, int y, int type) {
        return new Cursor(frame, x, y, type).start();
    }

    //启动该实例线程
    public Cursor start() {
        //如果处于停止状态
        if (IsRun == false) {
            //将组件组装至面板中
            Frame.add(Label);
            //开始运转线程
            IsRun = true;
            IsClear = false;
            //启动线程
            new Thread(this).start();
        }
        return this;
    }

    //暂停该实例线程
    public void stop() {
        //如果处于启动状态
        if (IsRun == true) {
            //停止线程
            IsRun = false;
            //面板删除组件
            Frame.remove(Label);
        }
    }

    //清除该实例线程
    public void clear() {
        //打开清除开关
        IsClear = true;
        //暂停
        stop();
        //保证线程关闭
        run();
    }

    //创建一个新光标
    private JLabel VOIDCursor() {
        //组件
        JLabel cursor = new JLabel(getImg());
        //设置组件 X Y 轴  宽高
        cursor.setBounds(X, Y, W, H);
        //返回光标
        return cursor;
    }

    @Override
    public void run() {
        try {
            //线程是否继续运行
            while (IsRun) {
                //光标刷新延迟
                Thread.sleep(Time);
                //刷新
                Refresh();
            }
            //是否清除
            if (IsClear) {
                Label.setVisible(false);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //刷新状态,加锁,防止多帧
    public synchronized void Refresh() {
        //删除旧光标
        Frame.remove(Label);
        //新光标
        Label = VOIDCursor();
        //放入面板
        Frame.add(Label);
        //刷新面板
        Frame.repaint();
    }

    //上移
    public void moveUp() {
        if (IsRun) {
            Y = Y - Mov;
            Refresh();
        }
    }

    //下移
    public void moveDown() {
        if (IsRun) {
            Y = Y + Mov;
            Refresh();
        }
    }

    //左移
    public void moveLeft() {
        if (IsRun) {
            X = X - Mov;
            Refresh();
        }
    }

    //右移
    public void moveRight() {
        if (IsRun) {
            X = X + Mov;
            Refresh();
        }
    }

    //切换光标状态
    public void switchType(int type) {
        this.Type = type;
    }

    //切换光标坐标
    public void switchXY(int x, int y) {
        this.X = x;
        this.Y = y;
    }

}
