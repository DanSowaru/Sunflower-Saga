package br.com.soaring.main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame(); // cria nova janela;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permite que a janela feche quando clicar no X
        window.setResizable(false);
        window.setTitle("Sunflower Saga 1.0");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // importamos e adicionamos à janela o gamePanel.

        window.pack(); // Faz a janela se adaptar ao tamanho configurado nos subcompenentes (no caso o GamePanel);

        window.setLocationRelativeTo(null); //Não especifica a posição da janela, centraliza.
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}
