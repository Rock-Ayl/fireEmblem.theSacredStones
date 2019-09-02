package common.animation;

import common.Const;
import common.util.FileUtil;

import javax.swing.*;
import java.util.HashMap;

/**
 * created by Rock-Ayl on 2019-8-29
 * 一个人物在Map上移动的demo
 */
public class MapCharacter implements Runnable {

    //线程是否运行
    boolean IsRun;
    //是否清除线程
    boolean IsClear;
    //人物移动图片组
    HashMap<Integer, ImageIcon> ImgMoveMap;
    //人物停留图片组
    HashMap<Integer, ImageIcon> ImgStayMap;
    //人物放置的面板
    JFrame Frame;
    //人物对象
    JLabel Label;
    //人物移动图标编号
    int MoveNum = 1;
    //人物停留图标编号
    int StayNum = 1;
    //人物移动时动画刷新时间 1000=1秒
    int MoveTime = 200;
    //人物停留时动画刷新时间 1000=1秒
    int StayTime = 250;
    //人物x轴坐标
    int X;
    //人物y轴坐标
    int Y;
    //人物移动图宽
    int MoveW = 48;
    //人物移动图高
    int MoveH = 40;
    //人物停留图宽
    int StayW = 64;
    //人物停留图高
    int StayH = 48;
    //当前角色状态  0:待机-未移动-未被选中  1:待机-未移动-被选中  2:待机-已移动  3:向上移动  4:向左移动  5:向下移动  6:向右移动
    int Type;
    //人物移动距离
    int Mov = 10;

    //初始化停留和移动的图片组
    private void initImgMap() {
        ImgMoveMap = new HashMap<>();
        for (int i = 1; i <= 16; i++) {
            //组装移动的图片
            ImgMoveMap.put(i, new ImageIcon(FileUtil.setNumForFile(Const.Map_Paladin_M_Move, i)));
        }
        ImgStayMap = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            //组装停留的图片
            ImgStayMap.put(i, new ImageIcon(FileUtil.setNumForFile(Const.Map_Paladin_M_Stay, i)));
        }
    }

    //todo 未完成的获取人物图片
    private ImageIcon getImg() {
        //获取当前的图片
        ImageIcon img = null;
        //获取当前的图片
        switch (Type) {
            case 0:
                img = ImgStayMap.get(StayNum);
                StayNum++;
                if (StayNum < 1 || StayNum > 3) {
                    StayNum = 1;
                }
                break;
            case 1:
                img = ImgStayMap.get(StayNum);
                StayNum++;
                if (StayNum < 7 || StayNum > 9) {
                    StayNum = 7;
                }
                break;
            case 2:
                img = ImgStayMap.get(StayNum);
                StayNum++;
                if (StayNum < 4 || StayNum > 6) {
                    StayNum = 4;
                }
                break;
            case 3:
                img = ImgMoveMap.get(MoveNum);
                MoveNum++;
                if (MoveNum < 13 || MoveNum > 16) {
                    MoveNum = 13;
                }
                break;
            case 4:
                img = ImgMoveMap.get(MoveNum);
                MoveNum++;
                if (MoveNum < 5 || MoveNum > 8) {
                    MoveNum = 5;
                }
                break;
            case 5:
                img = ImgMoveMap.get(MoveNum);
                MoveNum++;
                if (MoveNum < 1 || MoveNum > 4) {
                    MoveNum = 1;
                }
                break;
            case 6:
                img = ImgMoveMap.get(MoveNum);
                MoveNum++;
                if (MoveNum < 9 || MoveNum > 12) {
                    MoveNum = 9;
                }
                break;
            default:
                System.out.println("人物状态类型错误.");
        }
        return img;
    }

    //初始化人物线程对象
    public MapCharacter(JFrame frame, int x, int y, int type) {
        //初始化图片组
        initImgMap();
        //其他参数
        this.IsRun = false;
        this.IsClear = false;
        this.Frame = frame;
        this.X = x;
        this.Y = y;
        this.Type = type;
        this.Label = VOIDMapCharacter();
    }

    //初始化地图人物线程对象
    public static MapCharacter VOID(JFrame frame, int x, int y, int type) {
        return new MapCharacter(frame, x, y, type).start();
    }

    //启动该实例线程
    public MapCharacter start() {
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

    //创建一个新人物组件
    private JLabel VOIDMapCharacter() {
        //组件
        JLabel mapChar = new JLabel(getImg());
        //根据type设置组件 X Y 轴  宽高
        switch (Type) {
            case 3:
            case 4:
            case 5:
            case 6:
                mapChar.setBounds(X, Y, MoveW, MoveH);
                break;
            case 0:
            case 1:
            case 2:
                mapChar.setBounds(X, Y, StayW, StayH);
                break;
            default:
                System.out.println("人物状态类型错误.");
                break;
        }
        //返回组件
        return mapChar;
    }

    @Override
    public void run() {
        try {
            //线程是否继续运行
            while (IsRun) {
                //人物动画刷新延迟
                switch (Type) {
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        Thread.sleep(MoveTime);
                        break;
                    case 0:
                    case 1:
                    case 2:
                        Thread.sleep(StayTime);
                        break;
                    default:
                        System.out.println("人物状态类型错误.");
                        break;
                }
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
        //删除旧组件
        Frame.remove(Label);
        //新组件
        Label = VOIDMapCharacter();
        //放入面板
        Frame.add(Label);
        //刷新面板
        Frame.repaint();
    }

    //切换人物各种状态
    public void switchType(int type) {
        this.Type = type;
    }

    //切换人物坐标
    public void switchXY(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    //上移
    public void moveUp() {
        if (IsRun) {
            Y = Y - Mov;
            //切换到移动状态
            switchType(3);
            //刷新
            Refresh();
        }
    }

    //下移
    public void moveDown() {
        if (IsRun) {
            Y = Y + Mov;
            //切换到移动状态
            switchType(5);
            //刷新
            Refresh();
        }
    }

    //左移
    public void moveLeft() {
        if (IsRun) {
            X = X - Mov;
            //切换到移动状态
            switchType(4);
            //刷新
            Refresh();
        }
    }

    //右移
    public void moveRight() {
        if (IsRun) {
            X = X + Mov;
            //切换到移动状态
            switchType(6);
            //刷新
            Refresh();
        }
    }
}
