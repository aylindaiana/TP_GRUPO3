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
    private int CuentasCajaAhorro;
    private int CuentasCuentaCorriente;
    private int totalTransferencias;
    private int totalAltasCuenta;
    private int totalPagosPrestamo;
    private int totalAltasPrestamo;

    // getters y setters


    public Reporte() {}

    public int getTotalTransferencias() {
		return totalTransferencias;
	}

	public void setTotalTransferencias(int totalTransferencias) {
		this.totalTransferencias = totalTransferencias;
	}

	public int getTotalAltasCuenta() {
		return totalAltasCuenta;
	}

	public void setTotalAltasCuenta(int totalAltasCuenta) {
		this.totalAltasCuenta = totalAltasCuenta;
	}

	public int getTotalPagosPrestamo() {
		return totalPagosPrestamo;
	}

	public void setTotalPagosPrestamo(int totalPagosPrestamo) {
		this.totalPagosPrestamo = totalPagosPrestamo;
	}

	public int getTotalAltasPrestamo() {
		return totalAltasPrestamo;
	}

	public void setTotalAltasPrestamo(int totalAltasPrestamo) {
		this.totalAltasPrestamo = totalAltasPrestamo;
	}

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
    public int getCuentasCajaAhorro() {
        return CuentasCajaAhorro;
    }

    public void setCuentasCajaAhorro(int CuentasCajaAhorro) {
        this.CuentasCajaAhorro = CuentasCajaAhorro;
    }
    
    public int getCuentasCuentaCorriente() {
        return CuentasCuentaCorriente;
    }

    public void setCuentasCuentaCorriente(int CuentasCuentaCorriente) {
        this.CuentasCuentaCorriente = CuentasCuentaCorriente;
    }

    @Override
	public String toString() {
		return "Reporte [desde=" + desde + ", hasta=" + hasta + ", prestamosAprobados=" + prestamosAprobados
				+ ", prestamosRechazados=" + prestamosRechazados + ", cuotasPagadas=" + cuotasPagadas
				+ ", totalRecaudado=" + totalRecaudado + ", nuevosClientes=" + nuevosClientes
				+ ", totalMovimientosIngresos=" + totalMovimientosIngresos + ", totalMovimientosEgresos="
				+ totalMovimientosEgresos + ", CuentasCajaAhorro=" + CuentasCajaAhorro + ", CuentasCuentaCorriente="
				+ CuentasCuentaCorriente + ", totalTransferencias=" + totalTransferencias + ", totalAltasCuenta="
				+ totalAltasCuenta + ", totalPagosPrestamo=" + totalPagosPrestamo + ", totalAltasPrestamo="
				+ totalAltasPrestamo + "]";
	}
}
