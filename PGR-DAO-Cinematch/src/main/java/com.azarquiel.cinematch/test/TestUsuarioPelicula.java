package test;

import java.util.ArrayList;

import dao.PeliculaDAO;
import dao.UsuarioPeliculaDAO;
import model.Pelicula;
import model.UsuarioPelicula;

public class TestUsuarioPelicula {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		mostrarListado(findByAlias("ana"));
	}
	private static <T> void mostrarListado(ArrayList<T> listado) {
		for (T elemento : listado) {
			System.out.println(elemento.toString());
		 }
	}
	
	public static ArrayList<UsuarioPelicula> findByAlias (String alias){
		
		UsuarioPeliculaDAO usuarioPeliculaDao = new UsuarioPeliculaDAO();
		
		ArrayList<UsuarioPelicula> listadoUsuarioPelicula = usuarioPeliculaDao.findByAlias(alias);
		
		return listadoUsuarioPelicula;
	}
	
	
}
