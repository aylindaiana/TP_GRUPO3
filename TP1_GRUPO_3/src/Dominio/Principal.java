package Dominio;

public class Principal {
	public static void main(String[] args)
	{
		Empleado x = new Empleado();
		x.setNombre("Jose");
		x.setEdad(30);
		System.out.println(x.toString());
		
		Empleado p = new Empleado();
		p.setNombre("Pepe");
		p.setEdad(30);
		System.out.println(p.toString());
		
		Empleado a = new Empleado();
		a.setNombre("Ariel");
		a.setEdad(30);
		System.out.println(a.toString());
	}
}
