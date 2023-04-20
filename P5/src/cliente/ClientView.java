package cliente;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import mensajes.Descargar;
import mensajes.Listar;


public class ClientView extends JFrame  {
	
	// ---------------- INTERFAZ -------------------

	private static final long serialVersionUID = 1L;
	
	private final int WIDTH = 300;
	private final int HEIGHT = 400;
	
	JToolBar mensajePanel;
	JTextField mensaje;
	JButton enviar;
	
	private static final String MENU = "menu";
	private static final String LISTA = "lista";
	private static final String DESCARGA = "descarga";
	
	
	JPanel mainPanel, centerPanel, menuPanel, listaPanel, descargaPanel, desconectarPanel;
	
	JLabel menuLabel, tituloListaLabel, tituloDescargaPanel, menuListaLabel, menuDescargaLabel;
	
	JButton listaButton, descargaButton, volverListaButton, desconectarButton;
	
	JScrollPane listaScrollPane, descargaScrollPane;
	
	JTextArea listaText;
	
	// ----------------- LOGICA ----------------------
	
	
	Client client;
	
	
	public ClientView(Client client, String name) {
		super(name);
		this.client = client;
		initGUI();
	}
	
	/*  

	 */

	private void initGUI() {
		
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		
		centerPanel = new JPanel(new CardLayout());
		
		menuPanelConfiguration();
		
		listaPanelConfiguration();
		
		descargaPanelConfiguration();
		
		desconectarConfiguration();
		
		centerPanel.add(menuPanel, MENU);
		centerPanel.add(listaPanel, LISTA);
		centerPanel.add(descargaPanel, DESCARGA);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(desconectarPanel, BorderLayout.SOUTH);

		setBackground(Color.WHITE);
		this.setContentPane(mainPanel);
		this.pack();
		Dimension s = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(WIDTH, HEIGHT);
		setLocation(s.width/2 - WIDTH /2, s.height/2 - HEIGHT /2);
		this.setVisible(true);
	}

	private void menuPanelConfiguration() {
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.WHITE);
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
		
		JPanel menuLabelPanel = new JPanel();
		menuLabelPanel.setBackground(Color.WHITE);
		menuLabel = new JLabel("Seleccione la acción a realizar:");
		menuLabelPanel.add(menuLabel);
		
		JPanel listaPanelAux = new JPanel(new BorderLayout());
		listaPanelAux.setBorder(new EmptyBorder(20,20,20,20));
		listaPanelAux.setBackground(Color.WHITE);
		
		listaButton = new JButton("Lista de Clientes Conectados");
		listaButton.setBackground(Color.LIGHT_GRAY);
		
		listaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				client.enviarMensaje(new Listar());
			}
		});
		
	
		listaPanelAux.add(listaButton);
		
		JPanel descargaPanelAux = new JPanel(new BorderLayout());
		descargaPanelAux.setBorder(new EmptyBorder(20,20,20,20));
		descargaPanelAux.setBackground(Color.WHITE);
		
		descargaButton = new JButton("Descargar Archivos");
		descargaButton.setBackground(Color.LIGHT_GRAY);
		
		descargaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(centerPanel.getLayout());
				cl.show(centerPanel, DESCARGA);
				client.enviarMensaje(new Descargar());
			}
		});
		
		
		descargaPanelAux.add(descargaButton);
		
		menuPanel.add(menuLabelPanel);
		menuPanel.add(listaPanelAux);
		menuPanel.add(descargaPanelAux);
		
	}

	private void listaPanelConfiguration() {
		listaPanel = new JPanel(new BorderLayout());
		listaPanel.setBackground(Color.WHITE);
		listaPanel.setBorder(new EmptyBorder(10,10,10,10));
	}

	private void descargaPanelConfiguration() {
		descargaPanel = new JPanel();
		descargaPanel.setLayout(new BoxLayout(descargaPanel, BoxLayout.Y_AXIS));
	}
	
	private void desconectarConfiguration() {
		desconectarPanel = new JPanel(new FlowLayout());
		desconectarPanel.setBackground(Color.WHITE);
		desconectarButton = new JButton("Desconectar");
		
		desconectarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.desconectar();
			}
		});
		
		desconectarPanel.add(desconectarButton);
	}

	public void mostrarPanelLista(Set<String> listaConectados) {
		JScrollPane scroll = new JScrollPane();
		
		listaText = new JTextArea("Lista de clientes conectados:\n");
		for (String s : listaConectados) {
			listaText.append(s + "\n");
		}
		listaText.setEditable(false);
		listaPanel.add(listaText);
		
		scroll.setViewportView(listaText);
		listaPanel.add(scroll);
		
		CardLayout cl = (CardLayout)(centerPanel.getLayout());
		cl.show(centerPanel, LISTA);
	}


}
