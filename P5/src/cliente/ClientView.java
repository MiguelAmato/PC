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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import mensajes.Conectar;
import mensajes.Descargar;
import mensajes.Listar;
import mensajes.EmpezarConexionP2P;


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
	
	private static final String DESCONECTAR = "desconectar";
	private static final String VOLVER = "volver";
	
	JPanel mainPanel, centerPanel, bottomPanel, menuPanel, listaPanel, descargaPanel, desconectarPanel, volverPanel;
	
	JLabel menuLabel, tituloListaLabel, tituloDescargaPanel, menuListaLabel, menuDescargaLabel;
	
	JButton listaButton, descargaButton, volverButton, desconectarButton;
	
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
		bottomPanel = new JPanel(new CardLayout());
		
		menuPanelConfiguration();
				
		descargaPanelConfiguration();
		
		desconectarConfiguration();
		
		volverConfiguration();
		
		centerPanel.add(menuPanel, MENU);
		//centerPanel.add(listaPanel, LISTA);
		centerPanel.add(descargaPanel, DESCARGA);
		
		bottomPanel.add(desconectarPanel, DESCONECTAR);
		bottomPanel.add(volverPanel, VOLVER);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);

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
				CardLayout cl2 = (CardLayout)(bottomPanel.getLayout());
				cl2.show(bottomPanel, VOLVER);
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
				CardLayout cl = (CardLayout)(bottomPanel.getLayout());
				cl.show(bottomPanel, VOLVER);
				client.enviarMensaje(new Descargar());
			}
		});
		
		
		descargaPanelAux.add(descargaButton);
		
		menuPanel.add(menuLabelPanel);
		menuPanel.add(listaPanelAux);
		menuPanel.add(descargaPanelAux);
		
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
	
	private void volverConfiguration() {
		volverPanel = new JPanel(new FlowLayout());
		volverPanel.setBackground(Color.WHITE);
		volverButton = new JButton("Volver");
		
		volverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl1 = (CardLayout)(centerPanel.getLayout());
				cl1.show(centerPanel, MENU);
				CardLayout cl2 = (CardLayout)(bottomPanel.getLayout());
				cl2.show(bottomPanel, DESCONECTAR);
			}
		});
		
		volverPanel.add(volverButton);
	}

	public void mostrarPanelLista(Set<String> listaConectados) {
		listaPanel = new JPanel(new BorderLayout());
		listaPanel.setBackground(Color.WHITE);
		listaPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		JScrollPane scroll = new JScrollPane();
		
		JTextArea listaText = new JTextArea("Lista de clientes conectados:\n");
		for (String s : listaConectados) {
			listaText.append(s + "\n");
		}
		listaText.setEditable(false);
		listaPanel.add(listaText);
		
		scroll.setViewportView(listaText);
		listaPanel.add(scroll);

		centerPanel.add(listaPanel, LISTA);
		
		CardLayout cl = (CardLayout)(centerPanel.getLayout());
		cl.show(centerPanel, LISTA);
	}

	public void mostrarPanelListaArchivos(Set<String> listaArchivos) {
		descargaPanel = new JPanel(new BorderLayout());
		descargaPanel.setBackground(Color.WHITE);
		
		JLabel cabecera = new JLabel("Archivos disponibles para descargar:");
		
		JPanel listaArchivosPanel = new JPanel();
		listaArchivosPanel.setLayout(new BoxLayout(listaArchivosPanel, BoxLayout.Y_AXIS));
		
		if (listaArchivos.isEmpty()) {
			listaArchivosPanel.add(new JLabel("No hay archivos disponibles"));
		}
		else {
			int cont = 0;
			for(String file : listaArchivos) {
				if(!client.getInfo().getListaArchivos().contains(file)) {
					JPanel filePanel = new JPanel(new BorderLayout());
					filePanel.setBorder(new EmptyBorder(5,5,5,5));
					JButton fileButton = new JButton(file);
					fileButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							client.enviarMensaje(new EmpezarConexionP2P(file, client.getInfo()));
							CardLayout cl1 = (CardLayout)(centerPanel.getLayout());
							cl1.show(centerPanel, MENU);
							CardLayout cl2 = (CardLayout)(bottomPanel.getLayout());
							cl2.show(bottomPanel, DESCONECTAR);
						}
					});
					filePanel.setPreferredSize(new Dimension(255, 50));
					filePanel.add(fileButton);
					listaArchivosPanel.add(filePanel);
					cont++;
				}
			}
			
			while(cont < 5) {
				JPanel panelAux = new JPanel();
				panelAux.setPreferredSize(new Dimension(255, 50));
				panelAux.setVisible(true);
				listaArchivosPanel.add(panelAux);
				cont++;
			}
		}
		
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(listaArchivosPanel);
		
		descargaPanel.add(cabecera, BorderLayout.NORTH);
		descargaPanel.add(scroll, BorderLayout.CENTER);
		
		centerPanel.add(descargaPanel, DESCARGA);
		
		CardLayout cl = (CardLayout)(centerPanel.getLayout());
		cl.show(centerPanel, DESCARGA);
		
	}

	public void falloDescarga() {
		JOptionPane.showConfirmDialog(new JPanel(),"Fallo en descarga: Archivo no disponible");
		CardLayout cl1 = (CardLayout)(centerPanel.getLayout());
		cl1.show(centerPanel, MENU);
		CardLayout cl2 = (CardLayout)(bottomPanel.getLayout());
		cl2.show(bottomPanel, DESCONECTAR);
	}
	
	public void repetirNombre() {
		String name = JOptionPane.showInputDialog("Usuario ya conectado, escribe tu nombre:");
		client.probarNombre(name);
		this.setTitle(name);
	}

}
