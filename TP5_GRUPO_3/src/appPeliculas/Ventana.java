package appPeliculas;

import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBarPeliculas;
	private JMenu mnMenuPeliculas;
	private JMenuItem mntmMenuPeliculasAgregar;
	private JMenuItem mntmMenuPeliculasListar;

	public Ventana(){
		setTitle("Programa películas");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(new Rectangle(350, 150, 800, 500));
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		menuBarPeliculas = new JMenuBar();
		setJMenuBar(menuBarPeliculas);
		
		mnMenuPeliculas = new JMenu("Peliculas");
		menuBarPeliculas.add(mnMenuPeliculas);
		
		mntmMenuPeliculasAgregar = new JMenuItem("Agregar");
		mnMenuPeliculas.add(mntmMenuPeliculasAgregar);
		
		mntmMenuPeliculasListar = new JMenuItem("Listar");
		mnMenuPeliculas.add(mntmMenuPeliculasListar);
	}
	
	public void appActiva() {
		setVisible(true);
	}
}
