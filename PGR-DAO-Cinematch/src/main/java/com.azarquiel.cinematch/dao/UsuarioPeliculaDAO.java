package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
			nuevoUsuarioPelicula.setIdUsuarioPelicula(rs.getInt("ID"));
			nuevoUsuarioPelicula.setAlias(rs.getString("ALIAS"));
			
			int idPelicula = rs.getInt("PELICULA_ID");
			nuevoUsuarioPelicula.setIdPelicula(idPelicula);
			Pelicula pelicula = null;
			try {
				pelicula = PeliculaDAO.findPeliculaById(idPelicula);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			nuevoUsuarioPelicula.setPelicula(pelicula);
			
			nuevoUsuarioPelicula.setValoracion(rs.getDouble("VALORACION"));
			
			LocalDateTime ldt = rs.getObject("FECHA_REGISTRO", LocalDateTime.class);
			nuevoUsuarioPelicula.setFechaRegistro(ldt);
			
			
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
						
			String query="INSERT INTO USUARIO_PELICULA (ID, ALIAS, PELICULA_ID, VALORACION, FECHA_REGISTRO)\r\n"
					+ "VALUES (seq_usuario_pelicula.NEXTVAL, ?, ?, ?, ?)";
			
			ps = con.prepareStatement(query);
			
			ps.setObject(1, up.getAlias());
			ps.setInt(2, up.getIdPelicula());
			ps.setDouble(3, up.getValoracion());
			ps.setTimestamp(4, java.sql.Timestamp.valueOf(up.getFechaRegistro()));
			
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
			
			String query = "SELECT * FROM USUARIO_PELICULA WHERE UPPER(ALIAS) LIKE ?";
			
			ps = con.prepareStatement(query);

			if(alias==null) {
				alias="%";
        	} else {
        		alias="%"+alias.toUpperCase()+"%";
        	}
			
			ps.setObject(1, alias);


			rs = ps.executeQuery();
			
			while(rs.next()) {
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
			
			String query = "DELETE FROM USUARIO_PELICULA WHERE LOWER(ALIAS) LIKE ?";
			
			ps = con.prepareStatement(query);

			if(alias==null) {
				alias="%";
        	} else {
        		alias="%"+alias.toLowerCase()+"%";
        	}
			
			ps.setObject(1, alias);


			borradoCorrecto = ps.executeUpdate();
			
			con.commit();
			 
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
