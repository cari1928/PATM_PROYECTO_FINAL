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
@XmlRootElement(name = "categoria")
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

	/**
	 * Obtiene las categorías que están dentro de la fecha y horas de las
	 * funciones actuales
	 * 
	 * @return
	 */
	public List<categoria> getListaCApp() {
		List<categoria> arrC = null;
		categoria objCA;
		try {
			arrC = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();

			// si va a tener valores
			String query = "SELECT distinct c.categoria_id, categoria FROM funcion f "
					+ "INNER JOIN pelicula p on p.pelicula_id = f.pelicula_id "
					+ "INNER JOIN categoria_pelicula cp on cp.pelicula_id = p.pelicula_id "
					+ "INNER JOIN categoria c on c.categoria_id = cp.categoria_id "
					+ "WHERE now() between fecha and fecha_fin "
					+ "and (hora > (now()::time) or (now()::time) < (hora_fin - ('00:30:0'::time))) "
					+ "ORDER BY c.categoria_id";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objCA = new categoria();
				objCA.categoria_id = res.getInt(1);
				objCA.categoria = res.getString(2);

				arrC.add(objCA);
			}

			this.status = "GET";
			con.close();

		} catch (Exception e) {
			status = "ERROR-OBTENER-CATEGORIAS";
			e.printStackTrace();
		}
		return arrC;
	}

	/**
	 * Obtiene las categorías, sin restriccion de tiempo
	 * 
	 * @return
	 */
	public List<categoria> getListaC() {
		List<categoria> arrC = null;
		categoria objCA;
		try {
			arrC = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();

			// si va a tener valores
			String query = "SELECT * FROM categoria ORDER BY categoria_id";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objCA = new categoria();
				objCA.categoria_id = res.getInt(1);
				objCA.categoria = res.getString(2);

				arrC.add(objCA);
			}

			this.status = "GET";
			con.close();

		} catch (Exception e) {
			status = "ERROR-OBTENER-CATEGORIAS";
			e.printStackTrace();
		}
		return arrC;
	}

	/**
	 * Obtiene una categoría en base a categoria_id, CON restricción de
	 * actualidad
	 * 
	 * @return
	 */
	public categoria verCatApp() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT distinct c.categoria_id, categoria FROM funcion f "
					+ "INNER JOIN pelicula p ON p.pelicula_id = f.pelicula_id "
					+ "INNER JOIN categoria_pelicula cp ON cp.pelicula_id = p.pelicula_id "
					+ "INNER JOIN categoria c ON c.categoria_id = cp.categoria_id "
					+ "WHERE now() between fecha AND fecha_fin "
					+ "AND (hora > (now()::time) OR (now()::time) < (hora_fin - ('00:30:0'::time))) "
					+ "AND c.categoria_id=" + this.categoria_id + "ORDER BY c.categoria_id";
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.categoria = res.getString(2);
				this.status = "GET";
			} else {
				this.status = "NO-DISPONIBLE";
			}

			con.close();

		} catch (Exception e) {
			this.status = "ERROR-GET-CATEGORIA";
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * Obtiene una categoría en base a categoria_id, sin restricción de
	 * actualidad
	 * 
	 * @return
	 */
	public categoria verCategoria() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM categoria WHERE categoria_id=" + this.categoria_id;
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
