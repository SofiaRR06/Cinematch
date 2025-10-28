package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import conexion.Conexion;
import model.Pelicula;
import model.UsuarioPelicula;

public class UsuarioPeliculaDAO {
	
	public UsuarioPeliculaDAO() {
		
	}
	
	private static UsuarioPelicula mapUsuarioPelicula(ResultSet rs) {
		UsuarioPelicula nuevoUsuarioPelicula = new UsuarioPelicula();
		
		try {
			nuevoUsuarioPelicula.setIdUsuarioPelicula(rs.getInt("IDUSUARIOPELICULA"));
			nuevoUsuarioPelicula.setAlias(rs.getString("ALIAS"));
			nuevoUsuarioPelicula.setIdPelicula(rs.getInt("IDPELICULA"));
			nuevoUsuarioPelicula.setValoracion(rs.getDouble("VALORACION"));
			nuevoUsuarioPelicula.setFechaRegistro(((Timestamp)rs.getObject("FECHALIMITEDEVOLUCION")).toLocalDateTime());
			
			
		}catch (SQLException e) {
			System.out.println("Excepción generando Pelicula");
			e.printStackTrace();
		}
		 return nuevoUsuarioPelicula;
	}
	
	public void insert(UsuarioPelicula up) throws Exception {
		Conexion conexion = new Conexion();
		Connection con=null;
		PreparedStatement ps=null;
		int insercionCorrecta=0;
		
		try {
			con = conexion.getConexion();			
						
			String query="INSERT INTO USUARIOPELICULA (IDUSUARIOPELICULA, ALIAS, IDPELICULA, VALORACION, FECHAREGISTRO) VALUES (?,?,?,?,?)";
			
			ps = con.prepareStatement(query);
			
			ps.setInt(1, up.getIdUsuarioPelicula());
			ps.setObject(2, up.getAlias());
			ps.setInt(3, up.getIdPelicula());
			ps.setDouble(4, up.getValoracion());
			ps.setTimestamp(5, java.sql.Timestamp.valueOf(up.getFechaRegistro()));
			
			insercionCorrecta=ps.executeUpdate();
			
			con.commit();
	
		} catch (SQLException e) {
			con.rollback();
			throw e;
		} catch (Exception e) {
			throw e;
		}finally {
			try {
					if(ps!=null)
						ps.close();
					if(con!=null)
						con.close();
				} catch (SQLException e) {
					throw e;
				
				}		
		
		}

	}
	
	
	public ArrayList<UsuarioPelicula> findByAlias(String alias){
		Conexion conexion=new Conexion();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		ArrayList<UsuarioPelicula> listadoUsuarioPelicula = new ArrayList <UsuarioPelicula>();
		
		try {
			con = conexion.getConexion();
			
			String query = "SELECT * FROM USUARIOPELICULA WHERE ALIAS LIKE ?";
			
			ps = con.prepareStatement(query);

			if(alias==null) {
				alias="%";
        	} else {
        		alias="%"+alias.toUpperCase()+"%";
        	}
			
			ps.setObject(1, alias);


			rs = ps.executeQuery();
			
			if(rs.next()) {
				 listadoUsuarioPelicula.add(mapUsuarioPelicula(rs)); 
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
			return listadoUsuarioPelicula;	
	}
	
	
	public int deleteByAlias (String alias) {
		Conexion conexion=new Conexion();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		int borradoCorrecto = 0;

		try {
			con = conexion.getConexion();
			
			String query = "DELETE FROM USUARIOPELICULA WHERE ALIAS LIKE ?";
			
			ps = con.prepareStatement(query);

			if(alias==null) {
				alias="%";
        	} else {
        		alias="%"+alias.toUpperCase()+"%";
        	}
			
			ps.setObject(1, alias);


			borradoCorrecto = ps.executeUpdate();
			 
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
			return borradoCorrecto;	
		
	}
	
}
