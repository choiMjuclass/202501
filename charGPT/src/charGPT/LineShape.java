package charGPT;
import java.awt.Color;
import java.awt.Graphics;

class LineShape extends CShape {
    int x2, y2;
    
    public LineShape(int x, int y, int x2, int y2, Color color) {
        super(x, y, color);
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(x, y, x2, y2);
    }
}
