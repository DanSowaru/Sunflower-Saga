package br.com.soaring.entity;

import br.com.soaring.main.GamePanel;
import br.com.soaring.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        positionX = 100;
        positionY = 100;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("../res/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../res/player/player_up_2.png"));
            upIdle = ImageIO.read(getClass().getResourceAsStream("../res/player/player_up_idle.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../res/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../res/player/player_down_2.png"));
            downIdle = ImageIO.read(getClass().getResourceAsStream("../res/player/player_down_idle.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../res/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../res/player/player_left_2.png"));
            leftIdle = ImageIO.read(getClass().getResourceAsStream("../res/player/player_left_idle.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../res/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../res/player/player_right_2.png"));
            rightIdle = ImageIO.read(getClass().getResourceAsStream("../res/player/player_right_idle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyHandler.keyPressed == true) {
            // counter to switch sprites to animation;
            spriteCounter++;
            if (spriteCounter > 8) {

                switch (spriteNum) {
                    case 1: spriteNum = 2;
                    break;
                    case 2: spriteNum = 3;
                    break;
                    case 3: spriteNum = 4;
                    break;
                    case 4: spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


        //In Java, the upper left corner is X:0 Y:0. X values increases to the RIGHT, Y values increases as they go DOWN;
        if (keyHandler.upPressed == true) {
            direction = "up";
            positionY -= speed;
        }
        if (keyHandler.downPressed == true) {
            direction = "down";
            positionY += speed;
        }
        if (keyHandler.leftPressed == true) {
            direction = "left";
            positionX -= speed;
        }
        if (keyHandler.rightPressed == true) {
            direction = "right";
            positionX += speed;
        }
    }

    public void draw(Graphics2D graphics2D) {

//        graphics2D.setColor(Color.white); // Sets a color for drawing objects;
//
//        graphics2D.fillRect(positionX, positionY, gamePanel.tileSize, gamePanel.tileSize); // draws a rectangle (position X, position Y, width, height);


        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = upIdle;
                if (spriteNum == 3) image = up2;
                if (spriteNum == 4) image = upIdle;
                break;
            case "down":
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = downIdle;
                if (spriteNum == 3) image = down2;
                if (spriteNum == 4) image = downIdle;
                break;
            case "left":
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = leftIdle;
                if (spriteNum == 3) image = left2;
                if (spriteNum == 4) image = leftIdle;
                break;
            case "right":
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = rightIdle;
                if (spriteNum == 3) image = right2;
                if (spriteNum == 4) image = rightIdle;
        }

        graphics2D.drawImage(image, positionX, positionY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}