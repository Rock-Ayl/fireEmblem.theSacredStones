package start;

import common.Const;
import common.util.ImgUtil;

/**
 * 剪裁图片,用于行走图,战斗图等规则的图片切割成单个小图
 */
public class CutImg {

    public static void main(String[] args) {
        /**
         * @parameter
         * cutW:每一块小图的宽
         * cutH:每一块小图的高
         * imgPath:小图路径
         */
        ImgUtil.cutImag(32, 32, Const.Map_Laus);
    }
}
