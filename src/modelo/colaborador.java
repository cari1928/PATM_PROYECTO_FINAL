package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "colaborador")
public class colaborador {

	private int colaborador_id;
	private String nombre;
	private String apellidos;
	private String status;

	@XmlElement(required = true)
	public int getColaborador_id() {
		return colaborador_id;
	}

	@XmlElement(required = true)
	public String getNombre() {
		return nombre;
	}

	@XmlElement(required = true)
	public String getApellidos() {
		return apellidos;
	}

	@XmlElement(required = true)
	public String getStatus() {
		return status;
	}

	public void setColaborador_id(int colaborador_id) {
		this.colaborador_id = colaborador_id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public colaborador verColaborador() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM colaborador WHERE colaborador_id=" + this.colaborador_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.nombre = res.getString(2);
				this.apellidos = res.getString(3);
				this.status = "GET";
			}

			con.close();

		} catch (Exception e) {
			this.status = "ERROR-GET-COLABORADOR";
			e.printStackTrace();
		}

		return this;
	}

}
