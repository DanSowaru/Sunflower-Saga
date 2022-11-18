

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

### 6 Collision Detection
* Added true value to Tile.collision in TileManager.getTileImage();
* Created a Rectangle called solidArea in Entity;
* Created a boolean collision in Entity;
* Intantiated a rectangle in Player constructor. The rectangle is drawn inside the player draw area and is smaller than the player;
* Created a CollisionChecker class. Receives a GamePanel in constructor;
* Created CollisionChecker.checkTile(Entity) to check collision with other entities;
* Instantiated a CollisionChecker in GamePanel;
* add false to PlayerEntity.collision in Player.update();
* imediate after, call the CollisionChecker.checkCollision(this.player);
* Added 4 coordinates in checkTile() usinge the rectangle values to find their borders;
* Created two cols and rows tight after, then two test tiles;
* created a switch that test the btwo tiles and apply the collision when true;
* Created a IF collision==false after the collision check in Player and moved the movement code (world += speed) to there;
