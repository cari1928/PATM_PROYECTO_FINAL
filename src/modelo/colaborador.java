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

	/**
	 * Obtiene los colaboradores que están dentro de la fecha y horas de las
	 * funciones actuales
	 * 
	 * @return
	 */
	public List<colaborador> getListaCApp() {
		List<colaborador> arrC = null;
		colaborador objCo;
		try {
			arrC = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();

			String query = "SELECT c.colaborador_id, nombre, apellidos FROM funcion f "
					+ "INNER JOIN pelicula p ON p.pelicula_id = f.pelicula_id "
					+ "INNER JOIN reparto r ON r.pelicula_id = p.pelicula_id "
					+ "INNER JOIN colaborador c ON c.colaborador_id = r.colaborador_id "
					+ "WHERE now() between fecha and fecha_fin "
					+ "AND (hora > (now()::time) OR (now()::time) < (hora_fin - ('00:30:0'::time))) "
					+ "ORDER BY nombre";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objCo = new colaborador();
				objCo.colaborador_id = res.getInt(1);
				objCo.nombre = res.getString(2);
				objCo.apellidos = res.getString(3);

				arrC.add(objCo);
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
	 * Obtiene los colaboradores, sin restricción de tiempo
	 * 
	 * @return
	 */
	public List<colaborador> getListaC() {
		List<colaborador> arrC = null;
		colaborador objCo;
		try {
			arrC = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();

			String query = "SELECT * FROM colaborador ORDER BY nombre";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objCo = new colaborador();
				objCo.colaborador_id = res.getInt(1);
				objCo.nombre = res.getString(2);
				objCo.apellidos = res.getString(3);

				arrC.add(objCo);
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
	 * Obtiene un colaborador CON restricción de tiempo
	 * 
	 * @return
	 */
	public colaborador verColApp() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT c.colaborador_id, nombre, apellidos FROM funcion f "
					+ "INNER JOIN pelicula p ON p.pelicula_id = f.pelicula_id "
					+ "INNER JOIN reparto r ON r.pelicula_id = p.pelicula_id "
					+ "INNER JOIN colaborador c ON c.colaborador_id = r.colaborador_id "
					+ "WHERE now() between fecha and fecha_fin "
					+ "AND (hora > (now()::time) OR (now()::time) < (hora_fin - ('00:30:0'::time))) "
					+ "AND c.colaborador_id=" + this.colaborador_id;
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

	/**
	 * Obtiene un colaborador SIN restricción de tiempo
	 * 
	 * @return
	 */
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
