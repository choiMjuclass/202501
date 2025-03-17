package graphicsEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		this.setBackground(Color.WHITE);
	}
	
	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setColor(Color.BLACK);
		graphics2D.drawRect(10, 10, 50, 50);
	}

	public void initialize() {
		this.draw(this.getGraphics());
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		this.draw(graphics);
	}
}
