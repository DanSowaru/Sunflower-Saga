package br.com.soaring.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, keyPressed;


    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // Returns the integer keyCode associated with the key in this event.

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            keyPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            keyPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            keyPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            keyPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode(); // Returns the integer keyCode associated with the key in this event.

        if (code == KeyEvent.VK_W) {
            upPressed = false;
            keyPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
            keyPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
            keyPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
            keyPressed = false;
        }

    }
}

/*

Key Listener is the listener interface for receiving keyboard events.

keyCode List:
8 - Backspace
9 - Tab

 */
