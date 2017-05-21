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

	/**
	 * Listado de categorías-películas, en base a una película, con restricción
	 * de tiempo
	 * 
	 * @return
	 */
	public List<categoria_pelicula> verListaCApp() {
		List<categoria_pelicula> arrCP = null;
		categoria_pelicula objCP;
		categoria objCa;

		try {
			arrCP = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT cp.categoria_id, cp.pelicula_id, cp.categoria_pelicula_id FROM funcion f "
					+ "INNER JOIN pelicula p ON p.pelicula_id = f.pelicula_id "
					+ "INNER JOIN categoria_pelicula cp ON cp.pelicula_id = p.pelicula_id "
					+ "WHERE NOW() BETWEEN fecha AND fecha_fin "
					+ "AND (hora > (NOW()::time) OR (now()::time) < (hora_fin - ('00:30:0'::time)))";
			ResultSet res = stmt.executeQuery(query);

			if (!res.next()) {
				objCP = new categoria_pelicula();
				objCP.status = "ERROR-SIN-VALORES";
				arrCP.add(objCP);
				return arrCP;
			}

			while (res.next()) {
				objCP = new categoria_pelicula();

				objCa = new categoria();
				objCa.setCategoria_id(res.getInt(1));
				objCa.verCategoria();
				objCP.categoria = objCa;

				objCP.pelicula_id = res.getInt(2);
				objCP.categoria_pelicula_id = res.getInt(3);

				arrCP.add(objCP);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrCP;
	}

	/**
	 * Listado de categorías-películas, en base a una película, sin restricción
	 * de tiempo
	 * 
	 * @return
	 */
	public List<categoria_pelicula> verListaCP() {
		List<categoria_pelicula> arrCP = null;
		categoria_pelicula objCP;
		categoria objCa;

		try {
			arrCP = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM categoria_pelicula WHERE pelicula_id=" + this.pelicula_id;
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objCP = new categoria_pelicula();

				objCa = new categoria();
				objCa.setCategoria_id(res.getInt(1));
				objCa.verCategoria();
				objCP.categoria = objCa;

				objCP.categoria_pelicula_id = res.getInt(3);

				arrCP.add(objCP);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrCP;
	}

	/**
	 * Característica específica obtenida mediante una película, sin restricción
	 * de tiempo
	 * 
	 * @return
	 */
	public categoria_pelicula verCategoriaPelicula() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM categoria_pelicula WHERE pelicula_id=" + this.pelicula_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				categoria objCa = new categoria();
				objCa.setCategoria_id(res.getInt(1));
				objCa.verCategoria();
				this.categoria = objCa;

				this.categoria_pelicula_id = res.getInt(3);
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
