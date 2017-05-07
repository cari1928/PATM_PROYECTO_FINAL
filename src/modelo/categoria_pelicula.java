package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "categoria_pelicula")
public class categoria_pelicula {

	private categoria categoria;
	private int pelicula_id;
	private int categoria_pelicula_id;
	private String status;

	@XmlElement(required = true)
	public categoria getCategoria_id() {
		return categoria;
	}

	@XmlElement(required = true)
	public int getPelicula_id() {
		return pelicula_id;
	}

	@XmlElement(required = true)
	public int getCategoria_pelicula_id() {
		return categoria_pelicula_id;
	}

	@XmlElement(required = true)
	public String getStatus() {
		return status;
	}

	public void setCategoria_id(categoria categoria_id) {
		this.categoria = categoria_id;
	}

	public void setPelicula_id(int pelicula_id) {
		this.pelicula_id = pelicula_id;
	}

	public void setCategoria_pelicula_id(int categoria_pelicula_id) {
		this.categoria_pelicula_id = categoria_pelicula_id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public categoria_pelicula verCategoriaPelicula() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM categoria_pelicula WHERE categoria_pelicula_id=" + this.categoria_pelicula_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.pelicula_id = res.getInt(2);

				categoria objCa = new categoria();
				objCa.setCategoria_id(res.getInt(1));
				objCa.getCategoria();
				this.categoria = objCa;
			}

			this.status = "GET";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-GET-CATEGORIA_PELICULA";
			e.printStackTrace();
		}
		return this;
	}

}
