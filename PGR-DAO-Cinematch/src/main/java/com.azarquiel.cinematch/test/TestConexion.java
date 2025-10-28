package test;

import java.sql.Connection;
import java.sql.SQLException;

import conexion.Conexion;

public class TestConexion {
	
	public static void main(String[] args) throws Exception {
		Conexion conexion = new Conexion();
		Connection con = null;
		try {
			con=conexion.getConexion();
			System.out.println("conexión abierta correctamente");
			con.close();
			System.out.println("conexión cerrada correctamente");
		}catch (SQLException e) {
			System.out.println("Tratamiento del error");
			System.out.println("e.toString()");
		}
	}
}
