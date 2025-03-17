package charGPT;

import java.awt.Color;
import java.awt.Graphics;

class CircleShape extends CShape {
    int radius;
    
    public CircleShape(int x, int y, int radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}