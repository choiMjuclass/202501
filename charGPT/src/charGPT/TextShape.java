package charGPT;

import java.awt.Color;
import java.awt.Graphics;

class TextShape extends CShape {
    String text;
    
    public TextShape(int x, int y, String text, Color color) {
        super(x, y, color);
        this.text = text;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawString(text, x, y);
    }
}
