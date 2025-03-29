package Ejercicio1;

public class Principal {
	public static void main(String[] args)
	{
		// Crear 5 empleados (algunos con constructor vacío y otros con parámetros)
        Empleado emp1 = new Empleado(); // Constructor vacío
        emp1.setNombre("Marta");
        emp1.setEdad(28);
        
        Empleado emp2 = new Empleado("Carlos", 35); // Constructor con parámetros
        
        Empleado emp3 = new Empleado(); // Constructor vacío
        emp3.setNombre("Laura");
        emp3.setEdad(32);
        
        Empleado emp4 = new Empleado("Juan", 40); // Constructor con parámetros
        
        Empleado emp5 = new Empleado(); // Constructor vacío
        emp5.setNombre("Ana");
        emp5.setEdad(27);

        // Mostrar información de los empleados usando el método toString()
        System.out.println(emp1.toString());
        System.out.println(emp2.toString());
        System.out.println(emp3.toString());
        System.out.println(emp4.toString());
        System.out.println(emp5.toString());

        // Mostrar la información que devuelve el método devuelveProximoId()
        System.out.println("\nEl próximo ID disponible será: " + Empleado.devuelveProximoID());
	}
}
