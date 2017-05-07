package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "pelicula")
public class pelicula {

	private int pelicula_id;
	private String titulo;
	private String descripcion;
	private String f_lanzamiento;
	private String lenguaje;
	private int duracion;
	private String poster;
	private String status;

	@XmlElement(required = true)
	public int getPelicula_id() {
		return pelicula_id;
	}

	@XmlElement(required = true)
	public String getTitulo() {
		return titulo;
	}

	@XmlElement(required = true)
	public String getDescripcion() {
		return descripcion;
	}

	@XmlElement(required = true)
	public String getF_lanzamiento() {
		return f_lanzamiento;
	}

	@XmlElement(required = true)
	public String getLenguaje() {
		return lenguaje;
	}

	@XmlElement(required = true)
	public int getDuracion() {
		return duracion;
	}

	@XmlElement(required = true)
	public String getPoster() {
		return poster;
	}

	public String getStatus() {
		return status;
	}

	public void setPelicula_id(int pelicula_id) {
		this.pelicula_id = pelicula_id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setF_lanzamiento(String f_lanzamiento) {
		this.f_lanzamiento = f_lanzamiento;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public pelicula verPelicula() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM pelicula WHERE pelicula_id=" + this.pelicula_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.titulo = res.getString(2);
				this.descripcion = res.getString(3);
				this.f_lanzamiento = res.getString(4);
				this.lenguaje = res.getString(5);
				this.duracion = res.getInt(6);
				this.poster = res.getString(7);
				this.status = "GET";
			}

			con.close();

		} catch (Exception e) {
			this.status = "ERROR-GET-PELICULA";
			e.printStackTrace();
		}
		return this;
	}

}
