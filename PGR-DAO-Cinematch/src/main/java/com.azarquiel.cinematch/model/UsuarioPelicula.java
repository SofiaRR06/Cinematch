package model;

import java.time.LocalDateTime;

public class UsuarioPelicula {
	
	private int idUsuarioPelicula;
	private String alias;
	private int idPelicula;
	private double valoracion;
	private LocalDateTime fechaRegistro;
	
	public UsuarioPelicula(int idUsuarioPelicula, String alias, int idPelicula, double valoracion,
			LocalDateTime fechaRegistro) {
		super();
		this.idUsuarioPelicula = idUsuarioPelicula;
		this.alias = alias;
		this.idPelicula = idPelicula;
		this.valoracion = valoracion;
		this.fechaRegistro = fechaRegistro;
	}
	

	public UsuarioPelicula() {
		super();
	}


	public int getIdUsuarioPelicula() {
		return idUsuarioPelicula;
	}

	public void setIdUsuarioPelicula(int idUsuarioPelicula) {
		this.idUsuarioPelicula = idUsuarioPelicula;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public double getValoracion() {
		return valoracion;
	}

	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	public String toString() {
		return "UsuarioPelicula [idUsuarioPelicula=" + idUsuarioPelicula + ", alias=" + alias + ", idPelicula="
				+ idPelicula + ", valoracion=" + valoracion + ", fechaRegistro=" + fechaRegistro + "]";
	}
	
	
	
	

}
