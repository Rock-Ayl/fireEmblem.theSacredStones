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
    boolean isRun;
    //是否清除线程
    boolean isClear;
    //人物移动图片组
    HashMap<Integer, ImageIcon> imgMoveMap;
    //人物停留图片组
    HashMap<Integer, ImageIcon> imgStayMap;
    //人物放置的面板
    JFrame frame;
    //人物对象
    JLabel label;
    //人物移动图标编号
    int moveNum;
    //人物停留图标编号
    int stayNum;
    //人物移动时动画刷新时间 1000=1秒
    int moveTime = 200;
    //人物停留时动画刷新时间 1000=1秒
    int stayTime = 200;
    //人物x轴坐标
    int x;
    //人物y轴坐标
    int y;
    //人物移动图宽
    int moveW = 48;
    //人物移动图高
    int moveH = 40;
    //人物停留图宽
    int stayW = 64;
    //人物停留图高
    int stayH = 48;
    //当前角色状态 0:停留  1:移动
    int type;
    //人物移动距离
    int mov = 10;

    //初始化停留和移动的图片组
    private void initImgMap() {
        imgMoveMap = new HashMap<>();
        for (int i = 1; i <= 16; i++) {
            //组装移动的图片
            imgMoveMap.put(i, new ImageIcon(FileUtil.setNumForFile(Const.Map_Paladin_M_Move, i)));
        }
        imgStayMap = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            //组装停留的图片
            imgStayMap.put(i, new ImageIcon(FileUtil.setNumForFile(Const.Map_Paladin_M_Stay, i)));
        }
    }

    //todo 未完成的获取人物图片
    private ImageIcon getImg() {
        //获取当前的图片
        ImageIcon img = null;
        //获取当前的图片
        if (type == 0) {
            img = imgStayMap.get(stayNum);
            stayNum++;
            if (stayNum < 1 || stayNum > 9) {
                stayNum = 1;
            }
        } else if (type == 1) {
            img = imgMoveMap.get(moveNum);
            moveNum++;
            if (moveNum < 1 || moveNum > 16) {
                moveNum = 1;
            }
        } else {
            System.out.println("人物状态类型错误.");
        }
        return img;
    }

    //初始化人物线程对象
    public MapCharacter(JFrame frame, int x, int y, int type) {
        //初始化图片组
        initImgMap();
        //其他参数
        this.isRun = false;
        this.isClear = false;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.type = type;
        this.label = VOIDMapCharacter();
    }

    //初始化光标线程对象
    public static MapCharacter VOID(JFrame frame, int x, int y, int type) {
        return new MapCharacter(frame, x, y, type).start();
    }

    //启动该实例线程
    public MapCharacter start() {
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

    //创建一个新人物组件
    private JLabel VOIDMapCharacter() {
        //组件
        JLabel mapChar = new JLabel(getImg());
        //设置组件 x y 轴  宽高
        if (type == 0) {
            mapChar.setBounds(x, y, stayW, stayH);
        } else if (type == 1) {
            mapChar.setBounds(x, y, moveW, moveH);
        } else {
            System.out.println("人物状态类型错误.");
        }
        //返回组件
        return mapChar;
    }

    @Override
    public void run() {
        try {
            //线程是否继续运行
            while (isRun) {
                //人物动画刷新延迟
                if (type == 0) {
                    Thread.sleep(stayTime);
                } else if (type == 1) {
                    Thread.sleep(moveTime);
                } else {
                    System.out.println("人物状态类型错误.");
                }
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
        label = VOIDMapCharacter();
        //放入面板
        frame.add(label);
        //刷新面板
        frame.repaint();
    }

    //切换人物停留/移动状态
    public void switchType(int type) {
        this.type = type;
    }

    //切换人物坐标
    public void switchXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //上移
    public void moveUp() {
        if (isRun) {
            y = y - mov;
            //切换到移动状态
            switchType(1);
            //刷新
            Refresh();
            //切换到停留状态
            switchType(0);
        }
    }

    //下移
    public void moveDown() {
        if (isRun) {
            y = y + mov;
            //切换到移动状态
            switchType(1);
            //刷新
            Refresh();
            //切换到停留状态
            switchType(0);
        }
    }

    //左移
    public void moveLeft() {
        if (isRun) {
            x = x - mov;
            //切换到移动状态
            switchType(1);
            //刷新
            Refresh();
            //切换到停留状态
            switchType(0);
        }
    }

    //右移
    public void moveRight() {
        if (isRun) {
            x = x + mov;
            //切换到移动状态
            switchType(1);
            //刷新
            Refresh();
            //切换到停留状态
            switchType(0);
        }
    }
}
