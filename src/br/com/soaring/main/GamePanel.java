package br.com.soaring.main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16; // Tamanho da tela 16x16 tiles;
    final int scale = 3; // 16 pixels é pequeno em HD, então escalamos os sprites em 3x;
    final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 px
    final int screenHeight = tileSize * maxScreenRow; // 576 px

    int FPS = 60;

    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread; // The game will run on this thread. This works in constant flow;
    // The game runs ins a concept of time flowing, frames per second;
    // To implement this Thread, we implements Runnable on class and use a run() method;

    //Set player's default position
    int playerPositionX = 100;
    int playerPositionY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // seta o tamanho dessa classe;
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Se true, o drawing desse componente será feito em um buffer de painting offscreen. Habilitar isso melhora a perfomance do jogo;
        this.addKeyListener(keyHandler);
        this.setFocusable(true); //With this, this GamePanel can be "focused" to receive key input.
    }

    public void startGameThread() {
        gameThread = new Thread(this); //Passamos uma Thread com o GamePanel dentro
        gameThread.start();
    }

    @Override
    public void run() {

//        while(gameThread != null);
        while (gameThread.isAlive()) {

            long currentTime = System.nanoTime(); // Returns the current value of the running Java Virtual Machine high-resolution time source, in nanosseconds;

            update(); // Update information (character position, etc);
            repaint(); // Draw the screen with the update information (is calling the paintComponent method);
        }
    }


    // To update live the information that will be processed i real-time and rendered in the screen (such as character position);
    public void update() {

        //In Java, the upper left corner is X:0 Y:0. X values increases to the RIGHT, Y values increases as they go DOWN;
        if (keyHandler.upPressed == true) {
            playerPositionY -= playerSpeed;
        } else if (keyHandler.downPressed == true) {
            playerPositionY += playerSpeed;
        } else if (keyHandler.leftPressed == true) {
            playerPositionX -= playerSpeed;
        } else if (keyHandler.rightPressed == true) {
            playerPositionX += playerSpeed;
        }
    }


    // To render the updated information on screen. It passes the Graphics class, that has functions to draw objects on the screen;
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics); // We overwrite the JPanel paintComponent

        Graphics2D graphics2D = (Graphics2D) graphics; // Then we convert the graphics to 2D;

        graphics2D.setColor(Color.white); // Sets a color for drawing objects;

        graphics2D.fillRect(playerPositionX, playerPositionY, tileSize, tileSize); // draws a rectangle (position X, position Y, width, height);

        graphics2D.dispose(); // dispose of this graphics context and release any system resources that it is using. A good practice to save memory;

    }
}


