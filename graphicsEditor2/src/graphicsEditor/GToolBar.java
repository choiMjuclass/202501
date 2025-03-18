package graphicsEditor;

import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private JRadioButton rectangleButton;
	
	public GToolBar() {
		super();
		System.out.println("GToolBar::GToolBar");
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.rectangleButton = new JRadioButton("rectangle");
		this.add(this.rectangleButton);
	}

	public void initialize() {
		System.out.println("GToolBar::initialize");		
	}
	
	public void paint(Graphics graphics) {
		System.out.println("GToolBar::paint");
		super.paint(graphics);
	}	
	
	protected void paintComponent(Graphics graphics) {
		System.out.println("GToolBar::paintComponent");
		super.paintComponent(graphics);
	}
}
