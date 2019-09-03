package start;

import common.Const;
import common.animation.BattleCharacter;
import common.animation.Cursor;
import common.animation.MapCharacter;
import common.util.SwingUtil;

import javax.swing.*;

/**
 * 测试界面
 * 包括demo：
 * 光标
 * 地图人物行走
 * 战斗人物动画
 */
public class AnimationTest {

    //人物光标demo
    public static Cursor CharacterCursor;

    //人物地图demo
    public static MapCharacter CharacterMapCharacter;

    //人物战斗demo
    public static BattleCharacter CharacterBattleCharacter;

    //界面面板+大小
    public static JFrame Frame;
    public static int Width = 400;
    public static int Height = 300;

    public static void main(String[] args) {

        //初始化一个界面
        Frame = SwingUtil.VOIDJFrame("火焰之纹章-圣光之魔石", Width, Height);

        //增加一个光标实例并启动
        CharacterCursor = Cursor.VOID(Frame, 100, 100, 0);

        //增加一个地图人物实例并启动
        CharacterMapCharacter = MapCharacter.VOID(Frame, 0, 0, 1);

        //增加一个人物战斗图实例并启动
        CharacterBattleCharacter = BattleCharacter.VOID(Frame, 100, 0, "Battle_Paladin_F_Lance_Act_Normal_Time", Const.Battle_Paladin_F_Lance_Act_Normal);


    }

}
