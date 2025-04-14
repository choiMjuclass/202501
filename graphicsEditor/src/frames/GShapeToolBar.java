package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import frames.GDrawingPanel.EShapeType;

public class GShapeToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	// components

	// associations
	private GDrawingPanel drawingPanel;
	
	public GShapeToolBar() {
		for (EShapeType eShpaeType: EShapeType.values()) {
			JRadioButton radioButton = new JRadioButton(eShpaeType.getName());
			ActionHandler actionHandler = new ActionHandler();
			radioButton.addActionListener(actionHandler);
			radioButton.setActionCommand(eShpaeType.toString());
			this.add(radioButton);			
		}
	}

	public void initialize() {
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String sShapeType = e.getActionCommand();
			EShapeType eShapeType = EShapeType.valueOf(sShapeType);
			drawingPanel.setEShapeType(eShapeType);
		}
		
	}
}
