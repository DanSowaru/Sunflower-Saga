package br.com.soaring.main;

import br.com.soaring.entity.Player;
import br.com.soaring.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 64; // Tamanho de cada "tile" que formará a tela 64x64 tiles;
    final int scale = 1; // usado pra escalar (zoom) na tela. Nesse momento não usado;
    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 12; // 16 se 16x3, 24 se 32px, 12 se 32x2
    public final int maxScreenRow = 9; // 12 se 16x3, 18 se 32px, 9 se 32x2
    public final int screenWidth = tileSize * maxScreenCol; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; // 576 px


    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileManager = new TileManager(this);

    KeyHandler keyHandler = new KeyHandler();

    Thread gameThread; // The game will run on this thread. This works in constant flow;
    // The game runs ins a concept of time flowing, frames per second;
    // To implement this Thread, we implements Runnable on class and use a run() method;

    public Player player = new Player(this, keyHandler);



    public GamePanel() throws IOException {
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

    // SLEEP METHOD
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; // Instead of drawing the screen every 1 000 000 000 nanosecond, we can do it every 0.016 seconds, if we divide that number by the FPS. This will slow down the processing and drawing.

        double nextDrawTime = System.nanoTime() + drawInterval; // we add a point in time to the next draw;
        // System.nanoTime() = Returns the current value of the running Java Virtual Machine high-resolution time source, in nanosseconds;

        int fpsCounter = 0;

//        while(gameThread != null);
        while (gameThread.isAlive()) {

            update(); // Update information (character position, etc);
            repaint(); // Draw the screen with the update information (is calling the paintComponent method);

            //After updating and repaint, now let's sleep:
            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // we mark on the clock the next drawing time
                remainingTime = remainingTime/1000000; // we convert nanoseconds to milliseconds, the sleep() uses it.

                if (remainingTime < 0) remainingTime = 0; // round to 0 just in case;

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


//    // DELTA METHOD
//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000 / FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
//
//        while (gameThread.isAlive()) {
//
//            currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            lastTime = currentTime;
//
//            if (delta >= 1) {
//                update();
//                repaint();
//                delta--;
//            }
//
//
//        }
//    }


    // To update live the information that will be processed i real-time and rendered in the screen (such as character position);
    public void update() {

        player.update();
    }


    // To render the updated information on screen. It passes the Graphics class, that has functions to draw objects on the screen;
    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics); // We overwrite the JPanel paintComponent

        Graphics2D graphics2D = (Graphics2D) graphics; // Then we convert the graphics to 2D;

        tileManager.draw(graphics2D); // this works on layers. We draw tiles first, players last.
        player.draw(graphics2D);

        graphics2D.dispose(); // dispose of this graphics context and release any system resources that it is using. A good practice to save memory;

    }
}


