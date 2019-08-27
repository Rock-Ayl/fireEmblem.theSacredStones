package common.entity.tool.template;

/**
 * created by Rock-Ayl on 2019-8-23
 * 基础-道具,抽象,不可被实例化
 */
public abstract class Tool {

    //道具id
    int id;
    //道具名
    String name;
    //道具描述
    String Description;
    //数量
    int num;
    //价格
    int moneyl;
    //是否能被使用
    boolean use;
    //是否能被售卖
    boolean sale;
}
