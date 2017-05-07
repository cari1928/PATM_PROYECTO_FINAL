package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "empleado")
public class categoria {

	private int categoria_id;
	private String categoria;
	private String status;

	@XmlElement(required = true)
	public int getCategoria_id() {
		return categoria_id;
	}

	@XmlElement(required = true)
	public String getCategoria() {
		return categoria;
	}

	@XmlElement(required = true)
	public String getStatus() {
		return status;
	}

	public void setCategoria_id(int categoria_id) {
		this.categoria_id = categoria_id;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public categoria verCategoria() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM categoria WHERE id=" + this.categoria_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.categoria = res.getString(2);
				this.status = "GET";
			}

			con.close();

		} catch (Exception e) {
			this.status = "ERROR-GET-CATEGORIA";
			e.printStackTrace();
		}
		return this;
	}

}
