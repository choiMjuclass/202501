package graphicsEditor;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		// attributes
		this.setLocation(100, 200);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// components
		this.setLayout(new FlowLayout());

		this.menuBar = new GMenuBar();
		this.setJMenuBar(menuBar);		
		this.toolBar = new GToolBar();
		this.add(toolBar);		
		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel);		
	}

	public void initialize() {
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}

}
