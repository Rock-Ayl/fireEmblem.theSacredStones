package common.input;

import start.AnimationTest;

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
                AnimationTest.CharacterCursor.moveLeft();
                AnimationTest.CharacterMapCharacter.moveLeft();
                break;
            //上
            case 38:
                AnimationTest.CharacterCursor.moveUp();
                AnimationTest.CharacterMapCharacter.moveUp();
                break;
            //右
            case 39:
                AnimationTest.CharacterCursor.moveRight();
                AnimationTest.CharacterMapCharacter.moveRight();
                break;
            //下
            case 40:
                AnimationTest.CharacterCursor.moveDown();
                AnimationTest.CharacterMapCharacter.moveDown();
                break;
            //z
            case 90:
                AnimationTest.CharacterCursor.start();
                break;
            //x
            case 88:
                AnimationTest.CharacterCursor.stop();
                break;
            //c
            case 67:
                AnimationTest.CharacterCursor.clear();
                break;
            //a
            case 65:
                AnimationTest.CharacterCursor.switchType(0);
                AnimationTest.CharacterMapCharacter.switchType(0);
                break;
            //s
            case 83:
                AnimationTest.CharacterCursor.switchType(1);
                AnimationTest.CharacterMapCharacter.switchType(1);
                break;
            //d
            case 68:
                AnimationTest.CharacterCursor.switchType(2);
                AnimationTest.CharacterMapCharacter.switchType(2);
                break;
            //q
            case 81:
                AnimationTest.CharacterCursor.switchXY(30, 50);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
