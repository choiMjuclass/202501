package graphicsEditor;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public GMainFrame() {
		super();
		
		// attributes
		this.setLocation(100, 200);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// components
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);

		this.setLayout(new BorderLayout());
		
		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
//		this.add(this.drawingPanel, BorderLayout.SOUTH);
		this.setContentPane(drawingPanel);
	}

	public void initialize() {		
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}
}
