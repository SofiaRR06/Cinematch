package model;

import java.util.ArrayList;
import java.util.Optional;

import dao.PeliculaDAO;
import dao.UsuarioPeliculaDAO;

public class CineService {
	
	private PeliculaDAO peliculaDAO;
    private UsuarioPeliculaDAO usuarioPeliculaDAO;

    public CineService() {
    	peliculaDAO = new PeliculaDAO();
        usuarioPeliculaDAO = new UsuarioPeliculaDAO();
    }
    
    
	public void registrarPreferencia (UsuarioPelicula up) {
		try {
			usuarioPeliculaDAO.insert(up);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Optional<Pelicula> recomendarPelicula (String genero) {
		return peliculaDAO.findBestByGenero(genero);
	}
	
	public ArrayList<UsuarioPelicula> consultarHistorial (String alias){
		return usuarioPeliculaDAO.findByAlias(alias);
	}
	
	public void borrarHistorial (String alias) {
		usuarioPeliculaDAO.deleteByAlias(alias);
	}
	
	public ArrayList<Pelicula> topPeliculas(int limit){
		return peliculaDAO.findTopRated(limit);
	}
	
}
