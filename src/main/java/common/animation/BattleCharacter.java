package common.animation;

import common.Const;
import common.util.FileUtil;
import common.util.PropertyFileUtil;

import javax.swing.*;
import java.util.HashMap;

/**
 * created by Rock-Ayl on 2019-9-1
 * 一个人物战斗时候的demo
 */
public class BattleCharacter implements Runnable {

    //线程是否运行
    boolean isRun;
    //是否清除线程
    boolean isClear;
    //攻击图片组
    HashMap<Integer, ImageIcon> battleImgMap;
    //闪避图片组
    HashMap<Integer, ImageIcon> dodgeImgMap;
    //人物放置的面板
    JFrame frame;
    //人物对象
    JLabel label;
    //人物攻击图片编号
    int battleNum = 1;
    //人物闪避图片编号
    int dodgeNum;
    //人物x轴坐标
    int x;
    //人物y轴坐标
    int y;
    //人物图片总宽
    int w = 192;
    //人物图片总高
    int h = 192;
    //人物攻击时移动距离
    int battleMove = 10;
    //人物装备状态   剑 枪 斧 弓 杖 理 光 暗
    int WeaponType;
    //人物动作状态   攻击,防御
    int actType;
    //获取战斗帧组
    static HashMap<Integer, Long> battleTimeMap = new HashMap<>();

    //初始化动画的每一帧时间
    private static void initBattleTime() {
        //初始化第一帧编号(图片)
        int thisNum = 1;
        //获取帧组
        String[] battleTimes = PropertyFileUtil.BattleProperties.getProperty("Battle_Paladin_F_Lance_Time").split(",");
        //匹配编号组装至map
        for (String battleTime : battleTimes) {
            battleTimeMap.put(thisNum, Long.parseLong(battleTime));
            //编号++
            thisNum++;
        }
    }

    //初始化图片组
    private void initImgMap() {
        battleImgMap = new HashMap<>();
        for (int i = 1; i <= 47; i++) {
            //组装
            battleImgMap.put(i, new ImageIcon(FileUtil.setNumForFile(Const.Battle_Paladin_F_Lance, i)));
        }
        dodgeImgMap = new HashMap<>();
        for (int i = 1; i <= 6; i++) {
            //组装
            dodgeImgMap.put(i, new ImageIcon(FileUtil.setNumForFile(Const.Battle_Paladin_F_Dodge, i)));
        }
    }

    //获取人物图片
    private ImageIcon getImg() {
        //根据光标type获取图片
        ImageIcon img = battleImgMap.get(battleNum);
        if (img == null) {
            System.out.println("找不到图片" + battleNum);
        }
        battleNum++;
        if (battleNum < 1 || battleNum > 47) {
            battleNum = 1;
        }
        return img;
    }

    //初始化光标线程对象
    public BattleCharacter(JFrame frame, int x, int y, int type) {
        //初始化图片组
        initImgMap();
        //其他参数
        this.isRun = false;
        this.isClear = false;
        this.frame = frame;
        this.x = x;
        this.y = y;
        this.label = VOIDCursor();
        initBattleTime();
    }

    //初始化光标线程对象
    public static BattleCharacter VOID(JFrame frame, int x, int y, int type) {
        return new BattleCharacter(frame, x, y, type).start();
    }

    //启动该实例线程
    public BattleCharacter start() {
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
        //设置组件 x y 轴  宽高
        cursor.setBounds(x, y, w, h);
        //返回光标
        return cursor;
    }

    @Override
    public void run() {
        try {
            //线程是否继续运行
            while (isRun) {
                //记录当前编号(刷新会改变它)
                int num = battleNum;
                //刷新
                Refresh();
                //当前人物帧刷新延迟
                Thread.sleep(battleTimeMap.get(num));
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


    //切换人物状态
    public void switchType(int type) {

    }

    //切换光标坐标
    public void switchXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
