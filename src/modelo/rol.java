package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "rol")
public class rol {

	private int rol_id;
	private String rol;
	private Boolean valido;

	conexion objCon;
	Connection con;

	public rol() {
		objCon = new conexion();
		con = objCon.getCon();
	}

	@XmlElement(required = true)
	public int getRolid() {
		return rol_id;
	}

	@XmlElement(required = true)
	public String getRol() {
		return rol;
	}

	@XmlElement(required = true)
	public Boolean getValido() {
		Statement stmt;
		valido = false;

		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM rol WHERE rol_id =" + rol_id);
			// Si existe un registro indica que el usuario existe
			if (rs.next()) {
				valido = true; // Al agregarle el valor al campo indicamos que
			} // valor se mostrará en la cadena salida
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return valido;
	}

	public void getARol() {
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM rol WHERE rol_id =" + rol_id);
			if (rs.next()) {
				rol_id = rs.getInt("rol_id");
				rol = rs.getString("rol");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void setIdRol(int rol_id) {
		this.rol_id = rol_id;
	}

}
