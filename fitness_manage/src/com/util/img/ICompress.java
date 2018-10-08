package com.util.img;

import java.awt.image.BufferedImage;

/**
 * 压缩接口
 * @author fhr
 *
 */
public interface ICompress {
    boolean needCompress(BufferedImage bufferedImage,String exe) ;
    double getScale(BufferedImage bufferedImage) ;
}
