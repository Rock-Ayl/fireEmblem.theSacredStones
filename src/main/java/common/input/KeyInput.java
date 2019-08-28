package common.input;

import start.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * created by Rock-Ayl on 2019-8-25
 * 键盘监听器,监听键盘输入
 */
public class KeyInput implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("输入:" + keyCode);
        /**
         * 上:38
         * 下:40
         * 左:37
         * 右:39
         * 回车:10
         * ESC:27
         * z:90
         * x:88
         * c:67
         * @param e
         */
        switch (keyCode) {
            case 37:
                Main.CharacterCursor.moveLeft();
                break;
            case 38:
                Main.CharacterCursor.moveUp();
                break;
            case 39:
                Main.CharacterCursor.moveRight();
                break;
            case 40:
                Main.CharacterCursor.moveDown();
                break;
            case 90:
                Main.CharacterCursor.start();
                break;
            case 88:
                Main.CharacterCursor.stop();
                break;
            case 67:
                Main.CharacterCursor.clear();
                break;
            case 65:
                Main.CharacterCursor.switchType(0);
                break;
            case 83:
                Main.CharacterCursor.switchType(1);
                break;
            case 68:
                Main.CharacterCursor.switchType(2);
                break;
            case 81:
                Main.CharacterCursor.switchXY(30,50);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
