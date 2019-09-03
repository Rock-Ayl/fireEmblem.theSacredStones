package start;

import common.util.SwingUtil;

import javax.swing.*;

/**
 * 测试界面
 * 包括demo：
 * 地图的测试
 */
public class MapTest {

    //界面面板+大小
    public static JFrame Frame;
    public static int Width = 400;
    public static int Height = 300;

    public static void main(String[] args) {

        //初始化一个界面
        Frame = SwingUtil.VOIDJFrame("火焰之纹章-圣光之魔石", Width, Height);

    }
}
