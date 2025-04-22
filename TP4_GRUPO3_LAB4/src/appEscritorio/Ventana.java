package appEscritorio;

import java.awt.Rectangle;

import javax.swing.JFrame;

public class Ventana extends JFrame{
	private static final long serialVersionUID = 1L;

	public Ventana(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(new Rectangle(500, 500, 500, 500));
	}
	
	public void appActiva() {
		setVisible(true);
	}
}
