package ejercicio2;

public class MainEjercicio2 {

	public static void main(String[] args) {
		
        ProductoFresco productoFresco = new ProductoFresco("10/12/2025", 1001, "01/04/2025", "Argentina");
        ProductoRefrigerado productoRefrigerado = new ProductoRefrigerado("05/11/2025", 2001, 987654321);
        ProductoCongelado productoCongelado = new ProductoCongelado("30/09/2025", 3001, -18.0);
        
        System.out.println(productoFresco.toString());
        System.out.println(productoRefrigerado.toString());
        System.out.println(productoCongelado.toString());

	}

}
