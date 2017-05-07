package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class bitacora {

	private int persona_id;
	private String token;

	public void setPersona_id(int persona_id) {
		this.persona_id = persona_id;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void insAcceso() {
		try {
			conexion objC = new conexion();
			Connection conn = objC.getCon();
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO bitacora(persona_id, token) values(" + persona_id + ", '" + token + "')";
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean validaToken() {
		boolean ban = false;
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();
			ResultSet res;
			String query = "SELECT * FROM bitacora WHERE persona_id='" + persona_id + "' AND token='" + token
					+ "' AND NOW() BETWEEN f_ini and f_final";
			res = stmt.executeQuery(query);
			if (res.next()) {
				ban = true;
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ban;
	}

}
