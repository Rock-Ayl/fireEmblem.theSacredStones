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
        switch (keyCode) {
            //回车
            case 10:
                break;
            //ESC
            case 27:
                break;
            //左
            case 37:
                Main.CharacterCursor.moveLeft();
                break;
            //上
            case 38:
                Main.CharacterCursor.moveUp();
                break;
            //右
            case 39:
                Main.CharacterCursor.moveRight();
                break;
            //下
            case 40:
                Main.CharacterCursor.moveDown();
                break;
            //z
            case 90:
                Main.CharacterCursor.start();
                break;
            //x
            case 88:
                Main.CharacterCursor.stop();
                break;
            //c
            case 67:
                Main.CharacterCursor.clear();
                break;
            //a
            case 65:
                Main.CharacterCursor.switchType(0);
                break;
            //s
            case 83:
                Main.CharacterCursor.switchType(1);
                break;
            //d
            case 68:
                Main.CharacterCursor.switchType(2);
                break;
            //q
            case 81:
                Main.CharacterCursor.switchXY(30, 50);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
