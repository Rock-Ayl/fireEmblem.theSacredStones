package common.input;

import start.MainTest;

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
                MainTest.CharacterCursor.moveLeft();
                MainTest.CharacterMapCharacter.moveLeft();
                break;
            //上
            case 38:
                MainTest.CharacterCursor.moveUp();
                MainTest.CharacterMapCharacter.moveUp();
                break;
            //右
            case 39:
                MainTest.CharacterCursor.moveRight();
                MainTest.CharacterMapCharacter.moveRight();
                break;
            //下
            case 40:
                MainTest.CharacterCursor.moveDown();
                MainTest.CharacterMapCharacter.moveDown();
                break;
            //z
            case 90:
                MainTest.CharacterCursor.start();
                break;
            //x
            case 88:
                MainTest.CharacterCursor.stop();
                break;
            //c
            case 67:
                MainTest.CharacterCursor.clear();
                break;
            //a
            case 65:
                MainTest.CharacterCursor.switchType(0);
                MainTest.CharacterMapCharacter.switchType(0);
                break;
            //s
            case 83:
                MainTest.CharacterCursor.switchType(1);
                MainTest.CharacterMapCharacter.switchType(1);
                break;
            //d
            case 68:
                MainTest.CharacterCursor.switchType(2);
                MainTest.CharacterMapCharacter.switchType(2);
                break;
            //q
            case 81:
                MainTest.CharacterCursor.switchXY(30, 50);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
