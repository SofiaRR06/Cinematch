package model;

public class Pelicula {
	
	private int idPelicula;
	private String titulo;
	private String genero;
	private double puntuacionMedia;
	
	public Pelicula(int id, String titulo, String genero, double puntuacion_media) {
		super();
		this.idPelicula = id;
		this.titulo = titulo;
		this.genero = genero;
		this.puntuacionMedia = puntuacion_media;
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

	public double getPuntuacionMedia() {
		return puntuacionMedia;
	}

	public void setPuntuacionMedia(double puntuacion_media) {
		this.puntuacionMedia = puntuacion_media;
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + idPelicula + ", titulo=" + titulo + ", genero=" + genero + ", puntuacion_media="
				+ puntuacionMedia + "]";
	}
	
	
	

}
