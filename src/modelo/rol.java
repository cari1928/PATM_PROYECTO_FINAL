package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "rol")
public class rol {

	private int rol_id;
	private String rol;
	private String status;

	@XmlElement(required = true)
	public int getRol_id() {
		return rol_id;
	}

	@XmlElement(required = true)
	public String getRol() {
		return rol;
	}

	@XmlElement(required = true)
	public String getStatus() {
		return status;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Selecciona todos los roles
	 * 
	 * @return
	 */
	public List<rol> getListaR() {
		// jersey va a tomar cada objeto y retornará un json
		List<rol> arrR = null;
		rol objE;
		try {
			arrR = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();

			// si va a tener valores
			String query = "SELECT * FROM rol ORDER BY rol";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objE = new rol();
				objE.rol_id = res.getInt(1);
				objE.rol = res.getString(2);

				arrR.add(objE);
			}

			this.status = "GET";
			con.close();

		} catch (Exception e) {
			status = "ERROR-OBTENER-ROLES";
			e.printStackTrace();
		}
		return arrR;
	}

	/**
	 * Selecciona un rol en específico
	 * 
	 * @return
	 */
	public rol verRol() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			// Seleccionamos los roles
			String query = "SELECT * FROM rol WHERE rol_id=" + this.rol_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.rol = res.getString(2);
				this.status = "GET";
			}

			con.close();

		} catch (Exception e) {
			status = "ERROR-OBTENER-ROL";
			e.printStackTrace();
		}

		return this;
	}

	/**
	 * Inserta un Rol
	 */
	public void insRol() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "INSERT INTO rol(rol)" + " values('" + this.rol + "')";
			stmt.executeUpdate(query);
			this.status = "POST";
			con.close();
		} catch (Exception e) {
			this.status = "ERROR-INSERTAR-ROL";
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza un Rol
	 */
	public void actRol() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "UPDATE rol SET rol='" + this.rol + "' WHERE rol_id=" + this.rol_id;
			stmt.executeUpdate(query);
			this.status = "PUT";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-ACTUALIZAR-ROL";
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un rol
	 */
	public void delRol() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "DELETE FROM rol where rol_id=" + this.rol_id;
			stmt.executeUpdate(query);
			this.status = "DELETE";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-ELIMINAR-ROL";
			e.printStackTrace();
		}
	}

}
