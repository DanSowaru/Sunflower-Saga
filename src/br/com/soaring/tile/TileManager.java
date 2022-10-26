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

    int[][] mapTileCoordinates;

    public TileManager(GamePanel gamePanel) throws IOException {
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        this.mapTileCoordinates = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("../res/maps/large_map.txt");
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/grass1.png"));
            tiles[1] = new Tile();
            tiles[1].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/wall1.png"));
            tiles[2] = new Tile();
            tiles[2].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/water1.gif"));
            tiles[3] = new Tile();
            tiles[3].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/sand1.png"));
            tiles[4] = new Tile();
            tiles[4].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/tree_grass1.png"));
            tiles[5] = new Tile();
            tiles[5].tileImage = ImageIO.read(getClass().getResourceAsStream("../res/tiles/earth1.png"));
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

            while (column < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {

                String line = bufferedReader.readLine();

                while (column < gamePanel.maxWorldCol) {

                    String[] rowTiles = line.split(" "); // split the line around the given expression (" ");
                    int retrievedTile = Integer.parseInt(rowTiles[column]);
                    mapTileCoordinates[column][row] = retrievedTile;
                    column++;
                }
                if (column == gamePanel.maxWorldCol) {
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

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileUnit = mapTileCoordinates[worldCol][worldRow]; // fetching the tile unit of the line and column;
            int worldX = worldCol * gamePanel.tileSize; // the X coordinate in each tile draw;
            int worldY = worldRow * gamePanel.tileSize; // the Y coordinate in each tile draw;
            int screenX = worldX - gamePanel.player.worldPositionX + gamePanel.player.playerScreenPositionX; // used to verify if the current screen tile being rendered is in range of the character screen range;
            int screenY = worldY - gamePanel.player.worldPositionY + gamePanel.player.playerScreenPositionY; //

            if (isTileInRangeOfPlayer(worldX, worldY)) {
                graphics2D.drawImage(tiles[tileUnit].tileImage, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    // We verify if the tile is in range of the character screen range in all 4 directions starting from the character position; We expand one tile of range to render a margin of map outside of the program screen;
    private boolean isTileInRangeOfPlayer(int worldX, int worldY) {
        return
                worldX + gamePanel.tileSize > gamePanel.player.worldPositionX - gamePanel.player.playerScreenPositionX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldPositionX + gamePanel.player.playerScreenPositionX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldPositionY - gamePanel.player.playerScreenPositionY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldPositionY + gamePanel.player.playerScreenPositionY;
    }
}
