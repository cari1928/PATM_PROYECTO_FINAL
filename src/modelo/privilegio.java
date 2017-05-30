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
@XmlRootElement(name = "privilegio")
public class privilegio {

	private int privilegio_id;
	private int rol_id;
	private int persona_id;

	private String status;
	private String mensaje;
	private String estado;

	@XmlElement(required = true)
	public int getPrivilegio_id() {
		return privilegio_id;
	}

	@XmlElement(required = true)
	public int getRol_id() {
		return rol_id;
	}

	@XmlElement(required = true)
	public int getPersona_id() {
		return persona_id;
	}

	@XmlElement(required = true)
	public String getStatus() {
		return status;
	}

	@XmlElement(required = true)
	public String getMensaje() {
		return mensaje;
	}

	@XmlElement(required = true)
	public String getEstado() {
		return estado;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPrivilegio_id(int privilegio_id) {
		this.privilegio_id = privilegio_id;
	}

	public void setRol_id(int rol_id) {
		this.rol_id = rol_id;
	}

	public void setPersona_id(int persona_id) {
		this.persona_id = persona_id;
	}

	/**
	 * Selecciona todos los privilegios
	 * 
	 * @return
	 */
	public List<privilegio> getListaP() {
		// jersey va a tomar cada objeto y retornará un json
		List<privilegio> arrP = null;
		privilegio objP;
		try {
			arrP = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();

			// si va a tener valores
			String query = "SELECT privilegio_id, rol_id, persona_id FROM privilegio ORDER BY privilegio_id";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objP = new privilegio();
				objP.privilegio_id = res.getInt(1);
				objP.rol_id = res.getInt(2);
				objP.persona_id = res.getInt(3);

				arrP.add(objP);
			}

			this.status = "GET";
			this.estado = "1";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-OBTENER-PRIVILEGIOS";
			this.estado = "1";
			this.mensaje = "ERROR-OBTENER-PRIVILEGIOS";
			e.printStackTrace();
		}
		return arrP;
	}

	/**
	 * Selecciona un privilegio en específico en base a una persona_id Usado por
	 * cineMaster
	 * 
	 * @return
	 */
	public List<privilegio> verPrivilegio() {
		List<privilegio> arrP = new ArrayList<>();
		privilegio objP;

		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			// Seleccionamos los privilegios
			String query = "SELECT privilegio_id, rol_id, persona_id FROM privilegio WHERE persona_id="
					+ this.persona_id;
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objP = new privilegio();
				objP.privilegio_id = res.getInt(1);
				objP.rol_id = res.getInt(2);
				objP.persona_id = res.getInt(3);
				objP.status = "GET";
				objP.estado = "1";

				arrP.add(objP);
			}

			con.close();

		} catch (Exception e) {
			objP = new privilegio();
			objP.status = "ERROR-OBTENER-PRIVILEGIO";
			objP.estado = "2";
			objP.mensaje = "ERROR-OBTENER-PRIVILEGIO";
			arrP.add(objP);
			e.printStackTrace();
		}

		return arrP;
	}

	/**
	 * Inserta un Privilegio
	 */
	public void insPrivilegio() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "INSERT INTO privilegio(rol_id, persona_id)" + " VALUES(" + this.rol_id + ", "
					+ this.persona_id + ")";
			stmt.executeUpdate(query);

			// toma el id de la persona recientemente insertada
			query = "SELECT MAX(privilegio_id) as privilegio_id FROM privilegio";
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.privilegio_id = res.getInt(1);
			}
			this.status = "POST";
			this.estado = "1";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-INSERTAR-PRIVILEGIO";
			this.estado = "2";
			this.mensaje = "ERROR-INSERTAR-PRIVILEGIO";
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza un Privilegio
	 */
	public void actPrivilegio() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "UPDATE privilegio SET rol_id=" + this.rol_id + ", persona_id=" + this.persona_id
					+ " WHERE privilegio_id=" + this.privilegio_id;
			stmt.executeUpdate(query);
			this.status = "PUT";
			this.estado = "1";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-ACTUALIZAR-PRIVILEGIO";
			this.estado = "2";
			this.mensaje = "ERROR-ACTUALIZAR-PRIVILEGIO";
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un Privilegio
	 */
	public void delPrivilegio() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "DELETE FROM privilegio where privilegio_id=" + this.privilegio_id;
			stmt.executeUpdate(query);
			this.status = "DELETE";
			this.estado = "1";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-ELIMINAR-PRIVILEGIO";
			this.estado = "2";
			this.mensaje = "ERROR-ELIMINAR-PRIVILEGIO";
			e.printStackTrace();
		}
	}

}
