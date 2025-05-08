package appPeliculas;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.util.TreeSet;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel;
	private JMenuBar menuBarPeliculas;
	private JMenu mnMenuPeliculas;
	private JMenuItem mntmMenuPeliculasAgregar;
	private JMenuItem mntmMenuPeliculasListar;
	private static DefaultListModel<Peliculas> listModel;
	private static TreeSet<Peliculas> peliculasOrdenadas;

	public Ventana(){
		listModel = new DefaultListModel<>();
		peliculasOrdenadas = new TreeSet<Peliculas>();
		setTitle("Programa peliculas");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(new Rectangle(350, 150, 800, 500));
		getContentPane().setLayout(new BorderLayout());
		
		menuBarPeliculas = new JMenuBar();
		setJMenuBar(menuBarPeliculas);
		
		mnMenuPeliculas = new JMenu("Peliculas");
		menuBarPeliculas.add(mnMenuPeliculas);
		
		mntmMenuPeliculasAgregar = new JMenuItem("Agregar");
		mntmMenuPeliculasAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.removeAll();
				PanelAgregarPeliculas panel = new PanelAgregarPeliculas(peliculasOrdenadas);
				panel.setDefaultListModel(listModel);
				contentPanel.add(panel);
				contentPanel.revalidate();
				contentPanel.repaint();
				
			}
		});
		mnMenuPeliculas.add(mntmMenuPeliculasAgregar);
		
		mntmMenuPeliculasListar = new JMenuItem("Listar");
		mntmMenuPeliculasListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanel.removeAll();
				PanelListarPeliculas panel = new PanelListarPeliculas(listModel);
				
				contentPanel.add(panel);
				contentPanel.revalidate();
				contentPanel.repaint();
			}
		});
		mnMenuPeliculas.add(mntmMenuPeliculasListar);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}
	
	public void appActiva() {
		setVisible(true);
	}
}
