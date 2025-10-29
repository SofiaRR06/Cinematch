package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UsuarioPelicula {
	
	private int idUsuarioPelicula;
	private String alias;
	private Pelicula pelicula;
	private int idPelicula;
	private double valoracion;
	private LocalDateTime fechaRegistro;
	
	
	
	public UsuarioPelicula(int idUsuarioPelicula, String alias, Pelicula pelicula, int idPelicula, double valoracion,
			LocalDateTime fechaRegistro) {
		super();
		this.idUsuarioPelicula = idUsuarioPelicula;
		this.alias = alias;
		this.pelicula = pelicula;
		this.idPelicula = idPelicula;
		this.valoracion = valoracion;
		this.fechaRegistro = fechaRegistro;
	}


	public Pelicula getPelicula() {
		return pelicula;
	}


	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
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
	
	public String getFechaRegistroStr() {
	    if (fechaRegistro != null) {
	        return fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	    }
	    return "";
	}

	@Override
	public String toString() {
		return "UsuarioPelicula [idUsuarioPelicula=" + idUsuarioPelicula + ", alias=" + alias + ", pelicula=" + pelicula
				+ ", idPelicula=" + idPelicula + ", valoracion=" + valoracion + ", fechaRegistro=" + fechaRegistro
				+ "]";
	}

	
	

}
