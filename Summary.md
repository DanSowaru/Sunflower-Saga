

### 5 World and Camera
* Added sand, tree and earth tile;
* Imported them in TileManager.getTileImage;
* Created a 50x50 map;
* Refactor X and Y in Entity to WorldX and WorldY;
* Changed position of player from 100x100 to gamePanel.tileSize*23 x *21;
* Created screenX and ScreenY in Player. Attributed the half length of width and height (the middle of the screen) as values in Player constructor;
* Passed screenX and screenY in drawImage;
* added (- (gamePanel.tileSize/2)) in screen position because it wasn't actually center;
* Created World Settings in GamePanel (maxWorldCol and Row, worldWidth and Height);
* changed parameters of TileManager.mapTileCoordinates to [gp.maxWorldCol][gp.maxWorldRow];
* Also changed the range of the of the loadMap() loops to themaxWorldCol and maxWorldWidth;
* Delete X and Y from TileManager.draw();
* Rename col and row to worldCol and worldRow;
* Created worldX and worldY in TileManager.draw(). It receives the col and row index, times the tile size;
* Created screenX and Y that receives worldXY - gamePanel.player.worldXY + gamePanel.player.screenXY;
* Created an IF inside the WHILE in TileManager.draw() to limit the map rendering to the screen range, to maximize performance;