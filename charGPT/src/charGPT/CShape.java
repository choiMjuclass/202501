package charGPT;

import java.awt.Color;
import java.awt.Graphics;

abstract class CShape {
    int x, y;
    Color color;
    
    public CShape(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public abstract void draw(Graphics g);
}
