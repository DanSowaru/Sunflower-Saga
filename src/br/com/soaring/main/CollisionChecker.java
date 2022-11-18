package br.com.soaring.main;

import br.com.soaring.entity.Entity;

public class CollisionChecker {

    public final GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        //fetching the four borders coordinates of the rectangle drawn inside the character tile;
        int entityLeftWorldX = entity.worldPositionX + entity.solidArea.x;
        int entityRightWorldX = entity.worldPositionX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldPositionY + entity.solidArea.y;
        int entityDownWorldY = entity.worldPositionY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityDownRow = entityDownWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileCoordinates[entityLeftCol][entityTopRow]; // get the top left tile
                tileNum2 = gamePanel.tileManager.mapTileCoordinates[entityRightCol][entityTopRow]; // get the top right tile
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collision = true;
                }
                break;

            case "down":
                entityDownRow = (entityDownWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileCoordinates[entityLeftCol][entityDownRow];
                tileNum2 = gamePanel.tileManager.mapTileCoordinates[entityRightCol][entityDownRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collision = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileCoordinates[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileCoordinates[entityLeftCol][entityDownRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collision = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileCoordinates[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileCoordinates[entityRightCol][entityDownRow];
                if (gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) {
                    entity.collision = true;
                }
                break;
        }



    }
}
