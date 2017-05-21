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
@XmlRootElement(name = "reparto")
public class reparto {

	private int reparto_id;
	private int pelicula_id;
	private colaborador colaborador;
	private String puesto;
	private String status;

	@XmlElement(required = true)
	public int getReparto_id() {
		return reparto_id;
	}

	@XmlElement(required = true)
	public int getPelicula_id() {
		return pelicula_id;
	}

	@XmlElement(required = true)
	public colaborador getColaborador() {
		return colaborador;
	}

	@XmlElement(required = true)
	public String getPuesto() {
		return puesto;
	}

	@XmlElement(required = true)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setReparto_id(int reparto_id) {
		this.reparto_id = reparto_id;
	}

	public void setPelicula_id(int pelicula_id) {
		this.pelicula_id = pelicula_id;
	}

	public void setColaborador(colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	/**
	 * Listado de repartos CON restricción de tiempo
	 * 
	 * @return
	 */
	public List<reparto> verListaRApp() {
		List<reparto> arrR = new ArrayList<>();
		reparto objR;
		colaborador objC;

		try {
			conexion objCon = new conexion();
			Connection con = objCon.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT DISTINCT r.colaborador_id, r.pelicula_id, r.puesto, r.reparto_id FROM funcion f "
					+ "INNER JOIN pelicula p ON p.pelicula_id = f.pelicula_id "
					+ "INNER JOIN reparto r ON r.pelicula_id = p.pelicula_id "
					+ "WHERE NOW() BETWEEN fecha AND fecha_fin "
					+ "AND (hora > (NOW()::time) OR (now()::time) < (hora_fin - ('00:30:0'::time)))";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objR = new reparto();

				objC = new colaborador();
				objC.setColaborador_id(res.getInt(1));
				objC.verColaborador();
				objR.colaborador = objC;

				objR.pelicula_id = res.getInt(2);
				objR.puesto = res.getString(3);
				objR.reparto_id = res.getInt(4);
				objR.status = "GET";

				arrR.add(objR);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			objR = new reparto();
			objR.status = "ERROR-GET-REPARTO";
			arrR.add(objR);
		}

		return arrR;
	}

	/**
	 * Listado de repartos sin restricción de tiempo
	 * 
	 * @return
	 */
	public List<reparto> verListaR() {
		List<reparto> arrR = null;
		reparto objR;
		colaborador objC;

		try {
			arrR = new ArrayList<>();
			conexion objCon = new conexion();
			Connection con = objCon.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM reparto WHERE pelicula_id=" + this.pelicula_id;
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objR = new reparto();

				objC = new colaborador();
				objC.setColaborador_id(res.getInt(1));
				objC.verColaborador();
				objR.colaborador = objC;

				objR.puesto = res.getString(3);
				objR.reparto_id = res.getInt(4);
				objR.status = "GET";

				arrR.add(objR);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrR;
	}

	/**
	 * Obtiene un reparto en específico en base a una película, con restricción
	 * de tiempo
	 * 
	 * @return
	 */
	public reparto verRepartoApp() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM funcion f INNER JOIN pelicula p ON p.pelicula_id = f.pelicula_id "
					+ "INNER JOIN reparto r ON r.pelicula_id = p.pelicula_id "
					+ "WHERE now() between fecha and fecha_fin "
					+ "AND (hora > (now()::time) or (now()::time) < (hora_fin - ('00:30:0'::time))) "
					+ "AND pelicula_id=" + this.pelicula_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				colaborador objCo = new colaborador();
				objCo.setColaborador_id(res.getInt(1));
				objCo.verColaborador();
				this.colaborador = objCo;

				this.puesto = res.getString(3);
				this.reparto_id = res.getInt(4);
				this.status = "GET";
			}

			con.close();

		} catch (Exception e) {
			this.status = "ERROR-GET-REPARTO";
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * Obtiene un reparto en específico en base a una película, sin restricción
	 * de tiempo
	 * 
	 * @return
	 */
	public reparto verReparto() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM reparto WHERE pelicula_id=" + this.pelicula_id;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				colaborador objCo = new colaborador();
				objCo.setColaborador_id(res.getInt(1));
				objCo.verColaborador();
				this.colaborador = objCo;

				this.puesto = res.getString(3);
				this.reparto_id = res.getInt(4);
				this.status = "GET";
			}

			con.close();

		} catch (Exception e) {
			this.status = "ERROR-GET-REPARTO";
			e.printStackTrace();
		}
		return this;
	}

}
