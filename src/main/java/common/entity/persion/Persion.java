package common.entity.persion;

/**
 * created by Rock-Ayl on 2019-8-23
 * 基础-人,可被实例化
 */
public class Persion {

    //人物id
    int id;
    //人物名
    String name;
    //角色描述
    String Description;
    //角色职业
    String occupation;
    //性别 男/女
    String sex;
    //等级  1-20
    int lv;
    //经验值 0-100
    int exp;

    //力量-物理攻击力
    int str;
    //魔力-魔法攻击力
    int mag;
    //技术-命中率
    int skill;
    //速度-回避率+连续攻击
    int spd;
    //幸运-包含各种事情的概率
    int luck;
    //守备-物理防御
    int def;
    //魔防-魔法防御
    int res;
    //移动-一回合移动距离(视地形)
    int move;
    //体格-人物的重量
    int con;
    //救出-人物最大承重
    int aid;
    //同行对象-救出的人
    String trv;
    //人物属性 (01炎 02雷 03风 04冰 05冰 06光 07理)
    String affin;
    //状态(一般,中毒,昏睡)
    String cond;

    //对各种武器的熟练度-武器熟练度(无、E级、D级、C级、B级、A级、S级)

    //图形对象-包括走路图，待机图，立绘，战斗图等图组

    //支援角色对象

}
