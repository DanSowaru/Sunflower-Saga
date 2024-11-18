This summary is based on RyiSnow's java game fundamentals.

# 1 The Mechanics

## Main Class
* Main Package, Main Class;
* In Main Class, at Main Method, create a window with:

      JFrame window = new JFrame();

* Then, we add the functionality to close the window by pressing X with:
  
      window.setDefaultClosingOperation(JFrame.EXIT_ON_CLOSE);

* We block resizing the window with:

      window.setResizable(false);

* Add name to the window:

      window.setName("Name of your game");

* Do not specify the location of the window, and will be rendered at the center of the screen:

      window.setLocationRelativeTo(null);

* Make it visible:

      window.setVisible(true);

## GamePanel Class

* GamePanel extends JPanel
* We define some game screen settings here


* We set the gameTile Size and a scaler multiplier to make it look decent in higher resolutions:

      final int originalTileSize = 64;
      final int scale = 3;
      final int tileSize = originalTileSize * scale;

* To define the **size of screen**, we define the number of **columns** and **rows** of these tiles. In this example we use a 4x3 ratio:

      final int maxScreenCol = 16;
      final int maxScreenRow = 12;

      final int screenWidth = tileSize * maxScreenCol;
      final int screenHeight = tileSize * maxScreenRow;

* We set a constructor to JPanel and add a preffered size to the JPanel:

      setPreferredSize(new Dimension(width, height);

      

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
