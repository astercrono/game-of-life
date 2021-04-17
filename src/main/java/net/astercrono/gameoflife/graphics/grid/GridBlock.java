package net.astercrono.gameoflife.graphics.grid;

import java.awt.Color;

public class GridBlock {
    private int size;
    private Color color = Color.BLUE;
    private int x;
    private int y;

    public GridBlock() {
    }

    public GridBlock(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
