package view;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class JDFileChooser extends JDialog {

	private static final long serialVersionUID = 1L;
	private JFileChooser jFileChooser;

	public JDFileChooser(){
		setSize(900,400);
		setLocationRelativeTo(null);
		setModal(true);
		//lookAndFeel();
		initComponents();
	}

	public void lookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public void initComponents(){

		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
		jFileChooser = new JFileChooser("files");
		jFileChooser.setFileFilter(filter);
	}

	public String getPathFile() throws Exception{
		int selection  = jFileChooser.showOpenDialog(this);
		if (selection == JFileChooser.APPROVE_OPTION) {
			return jFileChooser.getSelectedFile().getPath();
		}else
			throw new Exception("Cancelo la seleccion");
	}

	public String showSaveFile() throws Exception {
		int selection = jFileChooser.showSaveDialog(this);
		if (selection == JFileChooser.APPROVE_OPTION) {
			return jFileChooser.getSelectedFile().getPath();
		}else
			throw new Exception("No se ah seleccionado ningun archivo");
	}
}
