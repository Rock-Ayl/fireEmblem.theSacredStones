package common.util;

import common.Const;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * created by Rock-Ayl on 2019-8-26
 * 项目中很常用的图片工具类
 */
public class ImgUtil {

    /**
     * 剪裁图片,用于行走图,战斗图等规则的图片切割成单个小图
     * eg:
     * 128*128 的图片
     * 切割成16个
     * 32*32 的小图
     * <p>
     * 使用方法:cutImag(32, 32, Const.CursorPath)
     *
     * @param cutW    切割的小图宽
     * @param cutH    切割的小图高
     * @param imgPath 原图路径
     */
    public static void cutImag(int cutW, int cutH, String imgPath) {
        try {
            //读取图片
            BufferedImage image = ImageIO.read(new File(imgPath));
            //获取文件宽高
            int width = image.getWidth();
            int height = image.getHeight();
            //从编号为1开始生成
            int num = 1;
            for (int y = 0; y < height; y = y + cutH) {
                for (int x = 0; x < width; x = x + cutW) {
                    // 截取图片 x轴 y轴 宽 高
                    Rectangle rect = new Rectangle(x, y, cutW, cutH);
                    //获取截取后的图片
                    BufferedImage areaImage = image.getSubimage(rect.x, rect.y, rect.width, rect.height);
                    // 新建一个带透明色,宽高为32*32的Image
                    BufferedImage buffImg = new BufferedImage(cutW, cutH, BufferedImage.TYPE_INT_ARGB);
                    //将图片放入透明img上
                    buffImg.getGraphics().drawImage(areaImage.getScaledInstance(cutW, cutH, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
                    //生成对应编号的文件名
                    String fileName = FileUtil.setNumForFile(imgPath, num);
                    //写入带着编号的子文件
                    ImageIO.write(buffImg, FileUtil.getFileExtension(fileName), new File(fileName));
                    //编号+1
                    num++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
