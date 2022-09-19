package br.com.soaring.tile;

import br.com.soaring.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;

    int mapTileNumber[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        this.mapTileNumber = new int[gamePanel.maxScreenRow][gamePanel.maxScreenCol];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/grass1.png"));
            tiles[1] = new Tile();
            tiles[1].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/wall1.png"));
            tiles[2] = new Tile();
            tiles[2].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/water1.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {

    }
    public void draw(Graphics2D graphics2D) {

        int col = 0;
        int row = 0;
        int X = 0;
        int Y = 0;

        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                graphics2D.drawImage(tiles[0].tileImage, X, Y, gamePanel.tileSize, gamePanel.tileSize, null);
                col++;
                X += gamePanel.tileSize;

                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    X = 0;
                    row++;
                    Y += gamePanel.tileSize;
                }
        }


    }
}
