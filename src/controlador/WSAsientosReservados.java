package controlador;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.asientos_reservados;
import modelo.bitacora;

@Path("/asientos_reservados")
public class WSAsientosReservados {

	@POST
	@Path("/insertar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public asientos_reservados insAsientosReservados(asientos_reservados objAR, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {
		
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			objAR.insAsientosReservados();
			return objAR;
		}
		return null;
	}
}
