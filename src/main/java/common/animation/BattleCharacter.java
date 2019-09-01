package common.animation;

import javax.swing.*;
import java.util.HashMap;

/**
 * created by Rock-Ayl on 2019-9-1
 * 一个人物战斗时候的demo
 */
public class BattleCharacter {

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
    int battleNum;
    //人物闪避图片编号
    int dodgeNum;
    //攻击动画刷新时间 1000=1秒
    int battleTime = 200;
    //闪避动画刷新时间 1000=1秒
    int dodgeTime = 200;
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

}
