package com.bland.tools.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author cqk
 * @since 2022/7/23
 */
public class StringImageUtils {

    /**
     * 默认文本
     */
    private final static String DEFAULT_TEXT = "bland";

    /**
     * 默认字体
     */
    private final static Font DEFAULT_FONT = new Font("microsoft-yahei", Font.PLAIN, 20);

    public static BufferedImage createBufferedImage(String text, int width, int height) {
        return createBufferedImage(text, DEFAULT_FONT, width, height, 0);
    }

    public static BufferedImage createBufferedImage(String text, int width, int height, int shear) {
        return createBufferedImage(text, DEFAULT_FONT, width, height, shear);
    }

    /**
     * 通过字符串生成透明背景的图片
     *
     * @param text   图片显示的文本
     * @param font   文本字体
     * @param width  宽
     * @param height 高
     * @param shear  倾斜角度
     * @return 图片
     */
    public static BufferedImage createBufferedImage(String text, Font font, int width, int height, int shear) {
        if (text == null) {
            text = DEFAULT_TEXT;
        }
        String fontColor = "#979797";

        String[] textArray = text.split("\n");
        if (font == null) {
            font = DEFAULT_FONT;
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 背景透明 开始
        Graphics2D graphics = image.createGraphics();
        image = graphics.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        graphics.dispose();
        // 背景透明 结束
        graphics = image.createGraphics();
        graphics.setColor(new Color(Integer.parseInt(fontColor.substring(1), 16)));// 设定画笔颜色
        graphics.setFont(font);// 设置画笔字体
        if (shear != 0) {
            double shx = BigDecimal.valueOf(shear).divide(BigDecimal.valueOf(90), 3, RoundingMode.HALF_UP).doubleValue();
            double shy = shx * -2.5;
//            graphics.shear(0.1, -0.26);// 设定倾斜度
            graphics.shear(shx, shy);// 设定倾斜度
        }

        // 设置字体平滑
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int y = 50;
        for (int i = 0; i < textArray.length; i++) {
            graphics.drawString(textArray[i], 0, y);// 画出字符串
            y = y + font.getSize();
        }

        graphics.dispose();// 释放画笔
        return image;

    }

}
