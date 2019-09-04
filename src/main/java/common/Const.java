package common;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * created by Rock-Ayl on 2019-8-26
 * 常量
 */
public class Const {

    //项目名
    public final static String ProjectName = "fireEmblem.theSacredStones";
    //项目路径
    public final static String SystemProjectPath = getSystemProjectUrl();
    //适应系统的盘符
    public final static String SystemSeparator = File.separator;
    //资源路径
    public final static String ResourcesPath = SystemProjectPath + ProjectName + SystemSeparator + "src/main/resources/";

    //todo 光标用例原图
    public final static String CursorPath = FilenameUtils.separatorsToSystem(ResourcesPath + "characters/cursor/Cursor.png");

    //todo 圣骑士-地图/战斗资源路径
    public final static String PaladinResourcesPath = FilenameUtils.separatorsToSystem(ResourcesPath + "characters/paladin/");
    //todo 圣骑士-男-地图-停留
    public final static String Map_Paladin_M_Stay = FilenameUtils.separatorsToSystem(PaladinResourcesPath + "map_paladin_m_stay/Map_Paladin_M_Stay.png");
    //todo 圣骑士-男-地图-移动
    public final static String Map_Paladin_M_Move = FilenameUtils.separatorsToSystem(PaladinResourcesPath + "map_paladin_m_move/Map_Paladin_M_Move.png");
    //todo 圣骑士-女-战斗-枪-攻击
    public final static String Battle_Paladin_F_Lance_Act_Normal = FilenameUtils.separatorsToSystem(PaladinResourcesPath + "battle_paladin_f_act/lance/Normal/Battle_Paladin_F_Lance.png");
    //todo 圣骑士-女-战斗-枪-暴击
    public final static String Battle_Paladin_F_Lance_Act_Crit = FilenameUtils.separatorsToSystem(PaladinResourcesPath + "battle_paladin_f_act/lance/crit/Battle_Paladin_F_Lance.png");
    //todo 圣骑士-女-战斗-枪-闪避
    public final static String Battle_Paladin_F_Lance_Dodge = FilenameUtils.separatorsToSystem(PaladinResourcesPath + "battle_paladin_f_dodge/lance/Battle_Paladin_F_Dodge.png");
    //todo 圣骑士-女-战斗-枪-被击中
    public final static String Battle_Paladin_F_Lance_Injured = FilenameUtils.separatorsToSystem(PaladinResourcesPath + "battle_paladin_f_injured/lance/Battle_Paladin_F_Injured.png");
    //todo 圣骑士-女-战斗-无持有-闪避
    public final static String Battle_Paladin_F_Nothing_Dodge = FilenameUtils.separatorsToSystem(PaladinResourcesPath + "battle_paladin_f_dodge/nothing/Battle_Paladin_F_Dodge.png");
    //todo 圣骑士-女-战斗-无持有-被击中
    public final static String Battle_Paladin_F_Nothing_Injured = FilenameUtils.separatorsToSystem(PaladinResourcesPath + "battle_paladin_f_injured/nothing/Battle_Paladin_F_Injured.png");

    //todo 地图资源
    public final static String MapResourcesPath = FilenameUtils.separatorsToSystem(ResourcesPath + "map/");
    //todo 地图-外景
    public final static String Map_Laus = FilenameUtils.separatorsToSystem(MapResourcesPath + "laus/Laus.png");


    /**
     * 获取项目路径
     *
     * @return
     */
    private static String getSystemProjectUrl() {
        String thisPath = Class.class.getClass().getResource("/").getPath();
        return thisPath.substring(0, thisPath.indexOf(ProjectName));
    }

}
