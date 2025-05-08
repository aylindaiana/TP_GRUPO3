package appPeliculas;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Font;

public class PanelListarPeliculas extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelListarPeliculas(DefaultListModel<Peliculas> model) {
		setLayout(null);
		
		JLabel lblPeliculas = new JLabel("Peliculas: ");
		lblPeliculas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPeliculas.setBounds(26, 120, 68, 14);
		add(lblPeliculas);
		
		JList<Peliculas> listPeliculas = new JList();
		listPeliculas.setFont(new Font("Tahoma", Font.BOLD, 14));
		listPeliculas.setBounds(115, 24, 296, 215);
		listPeliculas.setModel(model);
		add(listPeliculas);
	}

}
