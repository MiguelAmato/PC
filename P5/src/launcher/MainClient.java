package launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import cliente.Client;
import cliente.ClientView;

public class MainClient {

	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("Escribe tu nombre:");
		Client client;
		File file = getFile();
		client = new Client(name,file);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				client.setView(new ClientView(client, name)); 
				
			}
		});	
		
		client.start();
	}
	
	public static File getFile() {
		File selectedFile = null;
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Choose a directory to load your file: ");
		jfc.setFileSelectionMode(jfc.DIRECTORIES_ONLY);
		//jfc.addChoosableFileFilter(new FileNameExtensionFilter("JSON document", "json"));
		//jfc.setAcceptAllFileFilterUsed(true);

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			selectedFile = jfc.getSelectedFile();
		
		}
		return selectedFile;
	}
}
