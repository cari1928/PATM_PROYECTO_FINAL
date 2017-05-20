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
@XmlRootElement(name = "ciclo")
public class ciclo {

	private int PK_ID;
	private String nombre;
	private String abreviatura;

	public ciclo() {

	}

	public ciclo(int pK_ID, String nombre, String abreviatura) {
		super();
		PK_ID = pK_ID;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
	}

	@XmlElement(required = true)
	public int getPK_ID() {
		return PK_ID;
	}

	@XmlElement(required = true)
	public String getNombre() {
		return nombre;
	}

	@XmlElement(required = true)
	public String getAbreviatura() {
		return abreviatura;
	}

	public void setPK_ID(int pK_ID) {
		PK_ID = pK_ID;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public static List<ciclo> getAllCiclo() {
		List<ciclo> arrC = null;
		ciclo objCi;
		try {
			arrC = new ArrayList<>();
			conexion objC = new conexion();
			Connection con = objC.getCon();

			String query = "SELECT * FROM ciclo";
			Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				objCi = new ciclo();
				objCi.PK_ID = res.getInt(1);
				objCi.nombre = res.getString(2);
				objCi.abreviatura = res.getString(3);

				arrC.add(objCi);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arrC;
	}

	public ciclo verciclo() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "SELECT * FROM ciclo WHERE PK_ID=" + this.PK_ID;
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.nombre = res.getString(2);
				this.abreviatura = res.getString(3);
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}

	/**
	 * Inserta un Ciclo
	 */
	public void insCiclo() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "INSERT INTO ciclo(nombre, abreviatura)" + " VALUES('" + this.nombre + "', '"
					+ this.abreviatura + "' )";
			stmt.executeUpdate(query);

			query = "SELECT MAX(PK_ID) as PK_ID FROM ciclo";
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.PK_ID = res.getInt(1);
				this.nombre = null;
				this.abreviatura = null;
			}

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Actualiza un Ciclo
	 */
	public void actCiclo() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "UPDATE ciclo SET nombre='" + this.nombre + "', abreviatura='" + this.abreviatura
					+ "' WHERE PK_ID=" + this.PK_ID;
			stmt.executeUpdate(query);

			this.nombre = null;
			this.abreviatura = null;

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina un Ciclo
	 */
	public void delCiclo() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "DELETE FROM ciclo where PK_ID=" + this.PK_ID;
			stmt.executeUpdate(query);
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
