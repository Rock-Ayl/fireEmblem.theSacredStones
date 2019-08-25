package common.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * created by Rock-Ayl on 2019-8-25
 * 键盘监听器,监听键盘输入
 */
public class KeyInput implements KeyListener {

    /**
     * 上:38
     * 下:40
     * 左:37
     * 右:39
     *
     * @param e
     */

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("输入:" + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
