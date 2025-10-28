package test;

import java.util.ArrayList;

import dao.PeliculaDAO;
import model.Pelicula;

public class TestDaoPeliculas {
	public static void main(String[] args) {
		
	}
	
	public static ArrayList<Pelicula> findAllPeliculas(){
		
		PeliculaDAO peliculaDao = new PeliculaDAO();
		
		ArrayList<Pelicula> listadoPeliculas = peliculaDao.findAllPeliculas();
		
		return listadoPeliculas;
 		
	}
}
