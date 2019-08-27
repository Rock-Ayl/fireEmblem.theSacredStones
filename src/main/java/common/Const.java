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
