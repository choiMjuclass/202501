package graphicsEditor;

import java.awt.Graphics;
import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		super();
	}
	
	public void draw(Graphics graphics) {
		graphics.drawRect(10, 10, 50, 50);
	}

	public void initialize() {
		this.draw(this.getGraphics());
	}
}
