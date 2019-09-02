package common.animation;

import common.util.FileUtil;
import common.util.PropertyFileUtil;

import javax.swing.*;
import java.util.HashMap;

/**
 * created by Rock-Ayl on 2019-9-1
 * 一个人物战斗时候的实例demo
 * 动画帧存放在了battle.properties这个配置文件中
 */
public class BattleCharacter implements Runnable {

    //线程是否运行
    boolean IsRun;
    //是否清除线程
    boolean IsClear;
    //获取战斗帧组
    HashMap<Integer, Long> BattleTimeMap = new HashMap<>();
    //攻击图片组
    HashMap<Integer, ImageIcon> BattleImgMap;
    //人物放置的面板
    JFrame Frame;
    //人物对象
    JLabel Label;
    //人物攻击图片编号
    int BattleNum = 1;
    //人物图片最大编号
    int MaxBattleNum;
    //人物x轴坐标
    int x;
    //人物y轴坐标
    int Y;
    //人物图片总宽
    int W = 192;
    //人物图片总高
    int H = 192;
    //设定动画帧配置文件key
    String BattleTimeArrKey;
    //设定动画帧对应图片路径
    String BattleImgPath;

    //初始化动画的每一帧时间及图片组
    private void initBattleTime() {
        //初始化第一帧编号(图片)
        int thisNum = BattleNum;
        //进入配置获取帧组
        String[] battleTimes = PropertyFileUtil.BattleProperties.getProperty(BattleTimeArrKey).split(",");
        //匹配编号组装至map
        for (String battleTime : battleTimes) {
            BattleTimeMap.put(thisNum, Long.parseLong(battleTime));
            //编号++
            thisNum++;
        }
        //初始化最后一张图片
        MaxBattleNum = thisNum - 1;
        //初始化图片组
        BattleImgMap = new HashMap<>();
        for (int i = 1; i <= MaxBattleNum; i++) {
            //组装
            BattleImgMap.put(i, new ImageIcon(FileUtil.setNumForFile(BattleImgPath, i)));
        }
    }

    //获取人物图片
    private ImageIcon getImg() {
        //根据组件type获取图片
        ImageIcon img = BattleImgMap.get(BattleNum);
        if (img == null) {
            System.out.println("找不到图片" + BattleNum);
        }
        BattleNum++;
        if (BattleNum < 1 || BattleNum > MaxBattleNum) {
            BattleNum = 1;
        }
        return img;
    }

    //初始化人物线程对象
    public BattleCharacter(JFrame frame, int x, int y, String battleTimeArrKey, String battleImgPath) {
        //设定动画帧配置文件key
        BattleTimeArrKey = battleTimeArrKey;
        //设定动画帧对应图片路径
        BattleImgPath = battleImgPath;
        //初始化图片帧及初始化图片组
        initBattleTime();
        //其他参数
        this.IsRun = false;
        this.IsClear = false;
        this.Frame = frame;
        this.x = x;
        this.Y = y;
        this.Label = VOIDCharacter();
    }

    //初始化人物线程对象
    public static BattleCharacter VOID(JFrame frame, int x, int y, String battleTimeArrKey, String battleImgPath) {
        return new BattleCharacter(frame, x, y, battleTimeArrKey, battleImgPath).start();
    }

    //启动该实例线程
    public BattleCharacter start() {
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

    //创建一个新人物图片
    private JLabel VOIDCharacter() {
        //组件
        JLabel character = new JLabel(getImg());
        //设置组件 X Y 轴  宽高
        character.setBounds(x, Y, W, H);
        //返回人物组件
        return character;
    }

    @Override
    public void run() {
        try {
            //线程是否继续运行
            while (IsRun) {
                //记录当前编号(刷新会改变它)
                int num = BattleNum;
                //刷新
                Refresh();
                //当前人物帧刷新延迟
                Long thisNum = BattleTimeMap.get(num);
                if (thisNum == null) {
                    System.out.println(num - 1);
                }
                Thread.sleep(thisNum);
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
        Label = VOIDCharacter();
        //放入面板
        Frame.add(Label);
        //刷新面板
        Frame.repaint();
    }

}
