package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Herramientas.Encriptacion;

//las dos instrucciones especifican la raiz de json
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "persona")
public class persona {

	private int persona_id;
	private String nombre;
	private String apellidos;
	private String email;
	private String username;
	private String pass;
	private int edad;
	private String tarjeta;

	private String status;
	private String token;
	private Encriptacion objE;

	public persona() {
		objE = new Encriptacion();
	}

	@XmlElement(required = true)
	public int getPersona_id() {
		return persona_id;
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
	public String getEmail() {
		return email;
	}

	@XmlElement(required = true)
	public String getUsername() {
		return username;
	}

	@XmlElement(required = true)
	public String getPass() {
		return pass;
	}

	@XmlElement(required = true)
	public int getEdad() {
		return edad;
	}

	@XmlElement(required = true)
	public String getTarjeta() {
		return tarjeta;
	}

	@XmlElement(required = true)
	public String getStatus() {
		return status;
	}

	@XmlElement(required = true)
	public String getToken() {
		return token;
	}

	public void setPersona_id(int persona_id) {
		this.persona_id = persona_id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Selecciona un cliente en específico
	 * 
	 * @return
	 */
	public persona verPersona() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			// Seleccionamos las personas con un rol de cliente
			String query = "SELECT * FROM persona WHERE persona_id=" + this.persona_id
					+ " and persona_id in (select persona_id from privilegio where rol_id=1)";
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.nombre = res.getString(2);
				this.apellidos = res.getString(3);
				this.email = res.getString(4);
				this.username = res.getString(5);
				this.pass = res.getString(6);
				this.edad = res.getInt(7);
				this.tarjeta = res.getString(8);
				this.status = "GET";
			}

			con.close();

		} catch (Exception e) {
			status = "ERROR AL OBTENER EL CLIENTE";
			e.printStackTrace();
		}

		return this;
	}

	/**
	 * Insertar Cliente
	 */
	public void insPersona() {
		try {
			Encriptacion objE = new Encriptacion();
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "INSERT INTO persona(nombre, apellidos, email, username, pass, edad, tarjeta) values" + "('"
					+ nombre + "', '" + apellidos + "', '" + email + "', '" + username + "', '"
					+ objE.encriptaDato("MD5", pass) + "', " + edad + ", '" + tarjeta + "')";
			stmt.executeUpdate(query);

			// toma el id de la persona recientemente insertada
			query = "SELECT MAX(persona_id) as persona_id FROM persona";
			stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				this.persona_id = res.getInt(1);

				// rol_id=1 porque es el rol de Cliente
				// res nos da persona_id
				query = "INSERT INTO privilegio(rol_id, persona_id) values" + "(1, " + this.persona_id + ")";
				stmt.executeUpdate(query);
			}

			con.close();
			this.status = "POST";

		} catch (Exception e) {
			status = "ERROR AL INSERTAR CLIENTE";
			e.printStackTrace();
		}
	}

	public void actPersona() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();
			
			if(this.pass.length() != 32) {
				this.pass = objE.encriptaDato("MD5", this.pass);
			}

			String query = "UPDATE persona set nombre='" + this.nombre + "', apellidos='" + this.apellidos
					+ "', email='" + this.email + "', username='" + this.username + "', pass='"
					+ this.pass + "', edad=" + this.edad + ", tarjeta='" + this.tarjeta
					+ "' where persona_id=" + this.persona_id;
			stmt.executeUpdate(query);
			this.status = "PUT";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-ACTUALIZAR-PERSONA";
			e.printStackTrace();
		}
	}

	/**
	 * Para el logueo - bitácora
	 */
	public void validaUsuario() {
		try {
			conexion objC = new conexion();
			Connection conn = objC.getCon();
			Statement stmt = conn.createStatement();
			//pass = objE.encriptaDato("MD5", pass); // encripta pass
			
			String query = "SELECT * FROM persona WHERE username='" + username + "' and pass='" + pass + "'";
			ResultSet res = stmt.executeQuery(query);

			if (res.next()) {
				// se actualiza el valor de token
				generarToken("MD5", username, pass);
				bitacora objB = new bitacora();
				objB.setPersona_id(res.getInt(1));
				objB.setToken(token);
				objB.insAcceso();

				this.persona_id = res.getInt(1);
				this.status = "bitacora";

			} else {
				this.token = "no_valido";
				status = "ERROR AL VALIDAR USUARIO";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Para la bitácora
	 * 
	 * @param type
	 * @param user
	 * @param password
	 */
	private void generarToken(String type, String user, String password) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date fecha = new Date();
		String cadena = dateFormat.format(fecha) + user + password;
		token = objE.encriptaDato(type, cadena);
	}

}
