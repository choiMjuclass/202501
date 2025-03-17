package graphicsEditor;

import java.awt.BorderLayout;
import java.awt.Container;

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

		Container contenetPane = this.getContentPane();		
		contenetPane.setLayout(new BorderLayout());

		
		this.toolBar = new GToolBar();
		contenetPane.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new GDrawingPanel();
		contenetPane.add(this.drawingPanel, BorderLayout.SOUTH);		
	}

	public void initialize() {		
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}

}
