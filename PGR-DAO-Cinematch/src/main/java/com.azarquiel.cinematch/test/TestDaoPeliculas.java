package test;

import java.util.ArrayList;
import java.util.Optional;

import dao.PeliculaDAO;
import model.Pelicula;

public class TestDaoPeliculas {
	public static void main(String[] args) {
		
		//mostrarListado(findAllPeliculas());
		
		//mostrarListado(findTopRated(10));
		
		//System.out.print(findBestByGenero("Acción"));
		
		System.out.print(findById(1));
	}
	
	private static <T> void mostrarListado(ArrayList<T> listado) {
		for (T elemento : listado) {
			System.out.println(elemento.toString());
		 }
	}
	
	public static ArrayList<Pelicula> findAllPeliculas(){
		
		PeliculaDAO peliculaDao = new PeliculaDAO();
		
		ArrayList<Pelicula> listadoPeliculas = peliculaDao.findAllPeliculas();
		
		return listadoPeliculas;
 		
	}
	
	public static ArrayList<Pelicula> findTopRated(int limit){
		
		PeliculaDAO peliculaDao = new PeliculaDAO();
		
		ArrayList<Pelicula> listadoPeliculas = peliculaDao.findTopRated(limit);
		
		return listadoPeliculas;
	}
	
	public static Optional<Pelicula> findBestByGenero(String genero){
		
		PeliculaDAO peliculaDao = new PeliculaDAO();
		
		Optional<Pelicula> pelicula = peliculaDao.findBestByGenero(genero);
		
		return pelicula;
	}
	
	public static Pelicula findById (int id) {
		PeliculaDAO peliculaDao = new PeliculaDAO();
		
		Pelicula pelicula = peliculaDao.findPeliculaById(id);
		
		return pelicula;
	}
}

