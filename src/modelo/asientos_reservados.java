package modelo;

import java.sql.Connection;
import java.sql.Statement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "asientos_reservados")
public class asientos_reservados {

	private int cliente_id;
	private int asiento_id;
	private int sala_id;
	private String status;

	@XmlElement(required = true)
	public int getCliente_id() {
		return cliente_id;
	}

	@XmlElement(required = true)
	public int getAsiento_id() {
		return asiento_id;
	}

	@XmlElement(required = true)
	public int getSala_id() {
		return sala_id;
	}

	public String getStatus() {
		return status;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}

	public void setAsiento_id(int asiento_id) {
		this.asiento_id = asiento_id;
	}

	public void setSala_id(int sala_id) {
		this.sala_id = sala_id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void insAsientosReservados() {
		try {
			conexion objC = new conexion();
			Connection con = objC.getCon();
			Statement stmt = con.createStatement();

			String query = "INSERT INTO asientos_reservados(cliente_id, asiento_id, sala_id) values(" + this.cliente_id
					+ "," + this.asiento_id + "," + this.sala_id + ")";
			stmt.executeUpdate(query);

			this.status = "POST";
			con.close();

		} catch (Exception e) {
			this.status = "ERROR-INSERTAR-ASIENTOS_RESERVADOS";
			e.printStackTrace();
		}
	}
}
