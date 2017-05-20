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

	private List<categoria_pelicula> categorias;
	private List<reparto> colaboradores;

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

	@XmlElement(required = true)
	public String getStatus() {
		return status;
	}

	@XmlElement(required = true)
	public List<categoria_pelicula> getCategorias() {
		return categorias;
	}

	@XmlElement(required = true)
	public List<reparto> getColaboradores() {
		return colaboradores;
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

	public void setCategorias(List<categoria_pelicula> categorias) {
		this.categorias = categorias;
	}

	public void setColaboradores(List<reparto> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Listado de películas, SIN restricción de tiempo
	 * 
	 * @return
	 */
	public List<pelicula> verListaP() {
		List<pelicula> arrP = null;
		pelicula objP;
		categoria_pelicula objCP;
		List<categoria_pelicula> listCP;
		reparto objR;
		List<reparto> listR;

		try {
			arrP = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM PELICULA ORDER BY titulo";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objP = new pelicula();
				objP.setPelicula_id(res.getInt(1));
				objP.setTitulo(res.getString(2));
				objP.setDescripcion(res.getString(3));
				objP.setF_lanzamiento(res.getString(4));
				objP.setLenguaje(res.getString(5));
				objP.setDuracion(res.getInt(6));
				objP.setPoster(res.getString(7));

				objCP = new categoria_pelicula();
				objCP.setPelicula_id(res.getInt(1));
				listCP = objCP.verListaCP();
				objP.setCategorias(listCP);

				objR = new reparto();
				objR.setPelicula_id(res.getInt(1));
				listR = objR.verListaR();
				objP.setColaboradores(listR);

				objP.setStatus("GET");
				arrP.add(objP);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrP;
	}

	/**
	 * Para la app. Listado de películas, SIN restricción de tiempo
	 * 
	 * @return
	 */
	public List<pelicula> verListaPApp() {
		List<pelicula> arrP = null;
		pelicula objP;
		categoria_pelicula objCP;
		List<categoria_pelicula> listCP;
		reparto objR;
		List<reparto> listR;

		try {
			arrP = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT DISTINCT pelicula.pelicula_id, titulo, descripcion, f_lanzamiento, lenguaje, duracion, poster "
					+ "FROM pelicula INNER JOIN funcion ON funcion.pelicula_id = pelicula.pelicula_id "
					+ "WHERE now() BETWEEN fecha AND fecha_fin AND (hora > (now()::time) OR (now()::time) < (hora_fin)) "
					+ "ORDER BY titulo";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objP = new pelicula();
				objP.setPelicula_id(res.getInt(1));
				objP.setTitulo(res.getString(2));
				objP.setDescripcion(res.getString(3));
				objP.setF_lanzamiento(res.getString(4));
				objP.setLenguaje(res.getString(5));
				objP.setDuracion(res.getInt(6));
				objP.setPoster(res.getString(7));

				objCP = new categoria_pelicula();
				objCP.setPelicula_id(res.getInt(1));
				listCP = objCP.verListaCP();
				objP.setCategorias(listCP);

				objR = new reparto();
				objR.setPelicula_id(res.getInt(1));
				listR = objR.verListaR();
				objP.setColaboradores(listR);

				objP.setStatus("GET");
				arrP.add(objP);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrP;
	}

	/**
	 * Obtiene una película SIN restricción de tiempo
	 * 
	 * @return
	 */
	public pelicula verPelicula() {
		List<categoria_pelicula> listCP;
		List<reparto> listR;
		categoria_pelicula objCP;
		reparto objR;

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

				objCP = new categoria_pelicula();
				objCP.setPelicula_id(this.pelicula_id);
				listCP = objCP.verListaCP();
				this.categorias = listCP;

				objR = new reparto();
				objR.setPelicula_id(this.getPelicula_id());
				listR = objR.verListaR();
				this.colaboradores = listR;

				this.status = "GET";
			}

			con.close();

		} catch (Exception e) {
			this.status = "ERROR-GET-PELICULA";
			e.printStackTrace();
		}

		return this;
	}

	/**
	 * Obtiene una película SIN restricción de tiempo
	 * 
	 * @return
	 */
	public pelicula verPeliculaApp() {
		List<categoria_pelicula> listCP;
		List<reparto> listR;
		categoria_pelicula objCP;
		reparto objR;

		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT DISTINCT pelicula.pelicula_id, titulo, descripcion, f_lanzamiento, lenguaje, duracion, poster "
					+ "FROM pelicula INNER JOIN funcion ON funcion.pelicula_id = pelicula.pelicula_id "
					+ "WHERE now() BETWEEN fecha AND fecha_fin AND (hora > (now()::time) OR (now()::time) < (hora_fin)) "
					+ "AND pelicula_id=" + this.pelicula_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.titulo = res.getString(2);
				this.descripcion = res.getString(3);
				this.f_lanzamiento = res.getString(4);
				this.lenguaje = res.getString(5);
				this.duracion = res.getInt(6);
				this.poster = res.getString(7);

				objCP = new categoria_pelicula();
				objCP.setPelicula_id(this.pelicula_id);
				listCP = objCP.verListaCP();
				this.categorias = listCP;

				objR = new reparto();
				objR.setPelicula_id(this.getPelicula_id());
				listR = objR.verListaR();
				this.colaboradores = listR;

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
