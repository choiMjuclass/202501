package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import frames.GDrawingPanel;
import global.GConstants.EFileMenuItem;
import shapes.GShape;

public class GFileMenu extends JMenu {
	private static final long serialVersionUID = 1L;
	
	private File dir; 
	private File file; 
	
	private GDrawingPanel drawingPanel;
	
	public GFileMenu() {
		super("File");
		
		ActionHandler actionHandler = new ActionHandler();
		for (EFileMenuItem eMenuItem: EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItem.addActionListener(actionHandler);
			menuItem.setActionCommand(eMenuItem.name());
			this.add(menuItem);
		}
	}
	public void associate(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;		
	}
	public void initialize() {
		this.dir = new File("c:\\Users\\chois\\Source\\java\\classess\\classes-202501");
		this.file = null;
	}
	
	// methods
	public void newPanel() {
		System.out.println("newPanel");
		if (!this.close()) {
			// new
			this.drawingPanel.initialize();
		}
	}
	public void open() {
		System.out.println("open");
		try {
			FileInputStream fileInputStream = new FileInputStream(this.file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
			
			this.drawingPanel.setShapes(objectInputStream.readObject());
			objectInputStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void save() {
		if (this.file == null) {
			if (!this.saveAs()) {
				try {
					FileOutputStream fileOutputStream = new FileOutputStream(this.file);
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
					objectOutputStream.writeObject(this.drawingPanel.getShapes());
					objectOutputStream.close();
					this.drawingPanel.setBUpdated(false);
				} catch (IOException e) {
					e.printStackTrace();
				}		

			}
		}
	}
	public boolean saveAs() {
		boolean bCancel = false;		
		JFileChooser chooser = new JFileChooser(this.dir);
		chooser.setSelectedFile(this.file);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Data", "gvs");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showSaveDialog(this.drawingPanel);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.dir = chooser.getCurrentDirectory();
			this.file = chooser.getSelectedFile();
		} else {
			bCancel = true;
		}		
		return bCancel;	
	}
	
	public void print() {
		System.out.println("print");
	}
	
	public boolean close() {
		boolean bCancel = false;
		if (this.drawingPanel.isUpdated()) {
			int reply = JOptionPane.NO_OPTION;
			reply = JOptionPane.showConfirmDialog(this.drawingPanel, "변경내용을 저장 할까요?");
			if (reply == JOptionPane.CANCEL_OPTION) {	
				bCancel = true;
			} else if (reply == JOptionPane.OK_OPTION) {
//				bCancel = this.save();
				this.save();					
			}
		}
		return bCancel;
	}
	public void quit() {
		if (!this.close()) {
			// quit
			System.exit(0);
		}
	}
	
	private void invokeMethod(String methodName) {
		try {
			this.getClass().getMethod(methodName).invoke(this);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException 
				| IllegalArgumentException | InvocationTargetException exception) {
			exception.printStackTrace();
		}		
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			EFileMenuItem eFileMenuItem = EFileMenuItem.valueOf(event.getActionCommand());
			invokeMethod(eFileMenuItem.getMethodName());
		}		
	}

}
