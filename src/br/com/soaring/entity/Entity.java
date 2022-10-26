package br.com.soaring.entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int worldPositionX, worldPositionY, speed;

    public BufferedImage up1, up2, upIdle, down1, down2, downIdle, left1, left2, leftIdle, right1, right2, rightIdle; // BufferedImage describes an Image with an acessible buffer of image data, where we store our image files.

    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

}
