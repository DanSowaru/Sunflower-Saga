package br.com.soaring.tile;

import br.com.soaring.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;

    int mapTileCoordinates[][];

    public TileManager(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        this.mapTileCoordinates = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        getTileImage();
        loadMap("../res/maps/sample.txt");
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

    public void loadMap(String filePath) throws IOException {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int column = 0;
            int row = 0;

            while (column < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

                String line = bufferedReader.readLine();

                while (column < gamePanel.maxScreenCol) {

                    String rowTiles[] = line.split(" "); // split the line around the given expression (" ");
                    int retrievedTile = Integer.parseInt(rowTiles[column]);
                    mapTileCoordinates[column][row] = retrievedTile;
                    column++;
                }
                if (column == gamePanel.maxScreenCol) {
                    column = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D graphics2D) {

        int column = 0;
        int row = 0;
        int X = 0;
        int Y = 0;

        while (column < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

            int tileUnit = mapTileCoordinates[column][row];

            graphics2D.drawImage(tiles[tileUnit].tileImage, X, Y, gamePanel.tileSize, gamePanel.tileSize, null);
            column++;
            X += gamePanel.tileSize;

            if (column == gamePanel.maxScreenCol) {
                column = 0;
                X = 0;
                row++;
                Y += gamePanel.tileSize;
            }
        }


    }
}
