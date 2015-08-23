package com.jack;

import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 *
 * @author jack
 */
public class ColorfulItem extends CustomItem {

    private String label;
    private String text;
    private String keys;
    private boolean keyTest = false;
    private int bgColor = 0xffffff;
    private int foreColor = 0x000000;
    private Font font;
    Vector pressedKeys;
    // Is the button currently down
    private boolean down = false;
    // How many times has the button been clicked
    private int clicks = 0;

    public ColorfulItem(String label, String text) {
        super(label);
        this.label = label;
        this.text = text;
        this.keys = "";
        font = Font.getDefaultFont();
        pressedKeys = new Vector();
    }

    protected int getMinContentWidth() {
        return (font.stringWidth(label) + 4);
    }

    protected int getMinContentHeight() {
        return (font.getHeight() + 2);
    }

    protected int getPrefContentWidth(int height) {
        return getMinContentWidth();
    }

    protected int getPrefContentHeight(int width) {
        return getMinContentHeight();
    }

    protected void paint(Graphics g, int w, int h) {
        // Fill the background
        g.setColor(bgColor);
        g.fillRect(0, 0, w, h);

        g.setColor(foreColor);
        if (keyTest) {
            g.drawString(keys, w / 2, h, Graphics.HCENTER | Graphics.BOTTOM);
        } else {
            g.drawString(text, w / 2, h, Graphics.HCENTER | Graphics.BOTTOM);
        }

    }

    // Tells if the button was clicked
    public boolean isClicked() {
        if (clicks > 0) {
            clicks--;
            return true;
        }
        return false;
    }

    // Functions that set the button to down or up states
    public void setDown() {
        down = true;
        repaint();
        notifyStateChanged();
    }

    public void setUp() {
        down = false;
        clicks++;
        repaint();
        notifyStateChanged();
    }

    /*
     * uppercase for game action
     */
    protected void keyPressed(int keyCode) {
        if (!keyTest) {
            if (getGameAction(keyCode) == Canvas.FIRE) {
                setDown();
            }
            return;
        }
        char tempChar = Character.toLowerCase((char) keyCode);
        int gameAction = getGameAction(keyCode);
        Character ch = new Character(tempChar);

        System.out.println("" + ch + " : " + keyCode + " : action->  " + gameAction);
        System.out.println("isDigit " + Character.isDigit(ch.charValue())
                + " ::isLowerCase  " + Character.isLowerCase(ch.charValue())
                + " :: isUpperCase  " + Character.isUpperCase(ch.charValue()));

        if (Character.isDigit(ch.charValue()) || Character.isLowerCase(ch.charValue())) {
            if (!pressedKeys.contains(ch)) {
                pressedKeys.addElement(ch);
                keys = keys + ":" + ch;
            }
        }

        if (gameAction > 0 && !pressedKeys.contains(ch)) {
            int gameKeyIntValue = 'A' + gameAction;
            Character gameKey = new Character((char) gameKeyIntValue);
            System.out.println("formated value: " + gameKey);
            if (!pressedKeys.contains(gameKey)) {
                pressedKeys.addElement(gameKey);
                keys = keys + ":" + gameKey;
            }
        }

        repaint();
    }

    // When FIRE key is released, the button becomes released (up state)
    protected void keyReleased(int keyCode) {
        if (getGameAction(keyCode) == Canvas.FIRE) {
            setUp();
        }
    }
    // The same for sensor screens

    protected void pointerPressed(int x, int y) {
        setDown();
    }

    protected void pointerReleased(int x, int y) {
        setUp();
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the bgColor
     */
    public int getBgColor() {
        return bgColor;
    }

    /**
     * @param bgColor the bgColor to set
     */
    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * @return the foreColor
     */
    public int getForeColor() {
        return foreColor;
    }

    /**
     * @param foreColor the foreColor to set
     */
    public void setForeColor(int foreColor) {
        this.foreColor = foreColor;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the keys
     */
    public String getKeys() {
        return keys;
    }

    /**
     * @param keys the keys to set
     */
    public void setKeys(String keys, char sepChar) {
        this.keys = keys;
        pressedKeys.removeAllElements();
        char[] chars = keys.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Character ch = new Character(chars[i]);
            if (chars[i] != sepChar && !pressedKeys.contains(ch)) {
                pressedKeys.addElement(ch);
            }
        }
    }

    public void remove() {
        if (pressedKeys != null && pressedKeys.size() > 0) {
            pressedKeys.removeElementAt(pressedKeys.size() - 1);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < pressedKeys.size(); i++) {
                sb.append(":").append(pressedKeys.elementAt(i));
            }
            keys = sb.toString();
            repaint();
        }
    }

    /**
     * @return the keyTest
     */
    public boolean isKeyTest() {
        return keyTest;
    }

    /**
     * @param keyTest the keyTest to set
     */
    public void setKeyTest(boolean keyTest) {
        this.keyTest = keyTest;
    }

    /**
     * @return the down
     */
    public boolean isDown() {
        return down;
    }

    /**
     * @param down the down to set
     */
    public void setDown(boolean down) {
        this.down = down;
    }
}
