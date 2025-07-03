package entidad;

import java.time.LocalDate;

public class Reporte {

    private LocalDate desde;
    private LocalDate hasta;
    private int prestamosAprobados;
    private int prestamosRechazados;
    private int cuotasPagadas;
    private double totalRecaudado;
    private int nuevosClientes;
    private double totalMovimientosIngresos;
    private double totalMovimientosEgresos;

    public Reporte() {}

    public Reporte(LocalDate desde, LocalDate hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public LocalDate getDesde() {
        return desde;
    }

    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }

    public LocalDate getHasta() {
        return hasta;
    }

    public void setHasta(LocalDate hasta) {
        this.hasta = hasta;
    }

    public int getPrestamosAprobados() {
        return prestamosAprobados;
    }

    public void setPrestamosAprobados(int prestamosAprobados) {
        this.prestamosAprobados = prestamosAprobados;
    }

    public int getPrestamosRechazados() {
        return prestamosRechazados;
    }

    public void setPrestamosRechazados(int prestamosRechazados) {
        this.prestamosRechazados = prestamosRechazados;
    }

    public int getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(int cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }

    public double getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(double totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    public int getNuevosClientes() {
        return nuevosClientes;
    }

    public void setNuevosClientes(int nuevosClientes) {
        this.nuevosClientes = nuevosClientes;
    }

    public double getTotalMovimientosIngresos() {
        return totalMovimientosIngresos;
    }

    public void setTotalMovimientosIngresos(double totalMovimientosIngresos) {
        this.totalMovimientosIngresos = totalMovimientosIngresos;
    }

    public double getTotalMovimientosEgresos() {
        return totalMovimientosEgresos;
    }

    public void setTotalMovimientosEgresos(double totalMovimientosEgresos) {
        this.totalMovimientosEgresos = totalMovimientosEgresos;
    }

    @Override
    public String toString() {
        return "Reporte del " + desde + " al " + hasta + ":\n" +
                "- Préstamos Aprobados: " + prestamosAprobados + "\n" +
                "- Préstamos Rechazados: " + prestamosRechazados + "\n" +
                "- Cuotas Pagadas: " + cuotasPagadas + "\n" +
                "- Total Recaudado: $" + totalRecaudado + "\n" +
                "- Nuevos Clientes: " + nuevosClientes + "\n" +
                "- Total Ingresos: $" + totalMovimientosIngresos + "\n" +
                "- Total Egresos: $" + totalMovimientosEgresos;
    }
}
