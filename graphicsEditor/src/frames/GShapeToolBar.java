package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import global.GConstants.EShapeTool;

public class GShapeToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;


	// associations
	private GDrawingPanel drawingPanel;
	
	public GShapeToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		for (EShapeTool eShpaeType: EShapeTool.values()) {
			JRadioButton radioButton = new JRadioButton(eShpaeType.getName());
			ActionHandler actionHandler = new ActionHandler();
			radioButton.addActionListener(actionHandler);
			radioButton.setActionCommand(eShpaeType.toString());
			
			buttonGroup.add(radioButton);
			this.add(radioButton);			
		}
	}

	public void initialize() {
		JRadioButton button = (JRadioButton) this.getComponent(EShapeTool.eSelect.ordinal());
		button.doClick();
	}

	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;	
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String sShapeType = e.getActionCommand();
			EShapeTool eShapeType = EShapeTool.valueOf(sShapeType);
			drawingPanel.setEShapeTool(eShapeType);
		}		
	}
}
