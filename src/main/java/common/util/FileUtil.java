package common.util;

import org.apache.commons.io.FilenameUtils;

/**
 * created by Rock-Ayl on 2019-8-26
 * 项目中很常用的文件工具类
 */
public class FileUtil {

    /**
     * 传入编号和文件名,生成带编号的文件名
     * eg:  /work/ayl.doc → /work/ayl_1.doc
     */
    public static String setNumForFile(String filePath, int num) {
        return getFileFullPath(filePath) + getFileBaseName(filePath) + "_" + num + "." + getFileExtension(filePath);
    }

    /**
     * 获取文件路径中去除目录和后缀后的文件名
     * eg:  /work/ayl.doc → ayl
     */
    public static String getFileBaseName(String filePath) {
        return FilenameUtils.getBaseName(filePath);
    }

    /**
     * 获取文件的后缀
     * eg:  /work/ayl.doc → doc
     */
    public static String getFileExtension(String fileName) {
        return FilenameUtils.getExtension(fileName);
    }

    /**
     * 获取文件名称，包含后缀
     * eg:  /work/ayl.doc → ayl.doc
     *
     * @return
     */
    public static String getFileName(String fileName) {
        return FilenameUtils.getName(fileName);
    }

    /**
     * 获取文件的完整目录
     * eg:  /work/ayl.doc → /work/
     *
     * @return
     */
    public static String getFileFullPath(String fileName) {
        return FilenameUtils.getFullPath(fileName);
    }

}
