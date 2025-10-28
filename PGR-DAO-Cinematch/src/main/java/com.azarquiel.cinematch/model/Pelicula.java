package model;

public class Pelicula {
	
	private int idPelicula;
	private String titulo;
	private String genero;
	private double puntuacion_media;
	
	public Pelicula(int id, String titulo, String genero, double puntuacion_media) {
		super();
		this.idPelicula = id;
		this.titulo = titulo;
		this.genero = genero;
		this.puntuacion_media = puntuacion_media;
	}
	
	public Pelicula() {
		super();
	}

	public int getId() {
		return idPelicula;
	}

	public void setIdPelicula(int id) {
		this.idPelicula = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public double getPuntuacion_media() {
		return puntuacion_media;
	}

	public void setPuntuacion_media(double puntuacion_media) {
		this.puntuacion_media = puntuacion_media;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + idPelicula + ", titulo=" + titulo + ", genero=" + genero + ", puntuacion_media="
				+ puntuacion_media + "]";
	}
	
	
	

}
