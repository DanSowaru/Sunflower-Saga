package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    // Screen Settings
    final int originalTileSize = 16; // Tamanho da tela 16x16 tiles;
    final int scale = 3; // 16 pixels é pequeno em HD, então escalamos os sprites em 3x;
    final int tileSize = originalTileSize * scale;

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 px
    final int screenHeight = tileSize * maxScreenRow; // 576 px

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // seta o tamanho dessa classe;
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        // Se true, o drawing desse componente será feito em um buffer de painting offscreen.
        // Habilitar isso melhora a perfomance do jogo;
    }
}


