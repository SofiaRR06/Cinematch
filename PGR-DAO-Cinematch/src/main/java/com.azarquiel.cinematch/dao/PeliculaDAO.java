package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import conexion.Conexion;
import model.Pelicula;

public class PeliculaDAO {
	
	//Controlador vacio
	public PeliculaDAO(){
		
	}
	
	private static Pelicula mapPelicula(ResultSet rs) {
		Pelicula nuevaPelicula = new Pelicula();
		
		try {
			nuevaPelicula.setIdPelicula(rs.getInt("ID"));
			nuevaPelicula.setTitulo(rs.getString("TITULO"));
			nuevaPelicula.setGenero(rs.getString("GENERO"));
			nuevaPelicula.setPuntuacion_media(rs.getDouble("PUNTUACION_MEDIA"));
			
		}catch (SQLException e) {
			System.out.println("Excepción generando Pelicula");
			e.printStackTrace();
		}
		 return nuevaPelicula;
	}
	
	
	//Buscar todas las peliculas
	public ArrayList<Pelicula> findAllPeliculas(){
		
		Conexion conexion=new Conexion();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		ArrayList<Pelicula> listadoPeliculas = new ArrayList<Pelicula>();
		
		try {
			con = conexion.getConexion();
			
			String query = "SELECT * FROM PELICULA";
			
			ps = con.prepareStatement(query);

			rs = ps.executeQuery();
			
			while(rs.next()) {
				 listadoPeliculas.add(mapPelicula(rs)); 
			 } 
			 
			} catch (SQLException e) {
				System.out.println("Tratamiento del error");
				System.out.println(e.toString());
				
			} catch (Exception e) {
				e.printStackTrace();
				
			} finally {
				try {
					if(rs!=null)
						rs.close();
					if(ps!=null)
						ps.close();
					if(con!=null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
			return listadoPeliculas;	
		}
	
	
		//Buscar peliculas por genero
		public ArrayList<Pelicula> findByGenero(String genero){
			
			Conexion conexion=new Conexion();
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			ArrayList<Pelicula> listadoPeliculas = new ArrayList<Pelicula>();
			
			try {
				con = conexion.getConexion();
				
				String query = "SELECT * FROM PELICULA WHERE GENERO LIKE ?";
				
				ps = con.prepareStatement(query);
				
				if(genero==null) {
	        		genero="%";
	        	} else {
	        		genero="%"+genero.toUpperCase()+"%";
	        	}
				
				ps.setObject(1, genero);

				rs = ps.executeQuery();
				
				while(rs.next()) {
					 listadoPeliculas.add(mapPelicula(rs)); 
				 } 
				 
				} catch (SQLException e) {
					System.out.println("Tratamiento del error");
					System.out.println(e.toString());
					
				} catch (Exception e) {
					e.printStackTrace();
					
				} finally {
					try {
						if(rs!=null)
							rs.close();
						if(ps!=null)
							ps.close();
						if(con!=null)
							con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
				return listadoPeliculas;	
			}
		
		
		//Buscar la mejor pelicula por género
		public Optional<Pelicula> findBestByGenero(String genero){

			Conexion conexion=new Conexion();
			Connection con=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			
			Pelicula pelicula = null;
			
			try {
				con = conexion.getConexion();
				
				String query = "SELECT * FROM PELICULA WHERE GENERO LIKE ? ORDER BY PUNTUACION_MEDIA DESC LIMIT 1";
				
				ps = con.prepareStatement(query);

				if(genero==null) {
	        		genero="%";
	        	} else {
	        		genero="%"+genero.toUpperCase()+"%";
	        	}
				
				ps.setObject(1, genero);


				rs = ps.executeQuery();
				
				if(rs.next()) {
					 pelicula = (mapPelicula(rs)); 
					 return Optional.of(pelicula);
				 } 
				 
				} catch (SQLException e) {
					System.out.println("Tratamiento del error");
					System.out.println(e.toString());
					
				} catch (Exception e) {
					e.printStackTrace();
					
				} finally {
					try {
						if(rs!=null)
							rs.close();
						if(ps!=null)
							ps.close();
						if(con!=null)
							con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
				return Optional.empty();	
		}
		
		
		public ArrayList<Pelicula> findTopRated(int limit){
					
					Conexion conexion=new Conexion();
					Connection con=null;
					PreparedStatement ps=null;
					ResultSet rs=null;
					
					ArrayList<Pelicula> listadoPeliculas = new ArrayList<Pelicula>();
					
					try {
						con = conexion.getConexion();
						
						String query = "SELECT * FROM PELICULA ORDER BY PUNTUACION_MEDIA DESC LIMIT=?";
						
						ps = con.prepareStatement(query);
						
						ps.setInt(1, limit);
		
						rs = ps.executeQuery();
						
						while(rs.next()) {
							 listadoPeliculas.add(mapPelicula(rs)); 
						 } 
						 
						} catch (SQLException e) {
							System.out.println("Tratamiento del error");
							System.out.println(e.toString());
							
						} catch (Exception e) {
							e.printStackTrace();
							
						} finally {
							try {
								if(rs!=null)
									rs.close();
								if(ps!=null)
									ps.close();
								if(con!=null)
									con.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}	
						return listadoPeliculas;	
					}
				
		
		


}
