package appPeliculas;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel;
	private JMenuBar menuBarPeliculas;
	private JMenu mnMenuPeliculas;
	private JMenuItem mntmMenuPeliculasAgregar;
	private JMenuItem mntmMenuPeliculasListar;

	public Ventana(){
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
				PanelAgregarPeliculas panel = new PanelAgregarPeliculas();
				contentPanel.add(panel);
				contentPanel.revalidate();
				contentPanel.repaint();
				
			}
		});
		mnMenuPeliculas.add(mntmMenuPeliculasAgregar);
		
		mntmMenuPeliculasListar = new JMenuItem("Listar");
		mnMenuPeliculas.add(mntmMenuPeliculasListar);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}
	
	public void appActiva() {
		setVisible(true);
	}
}
