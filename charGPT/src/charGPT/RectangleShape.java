package charGPT;

import java.awt.Color;
import java.awt.Graphics;

class RectangleShape extends CShape {
    int width, height;
    
    public RectangleShape(int x, int y, int width, int height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
