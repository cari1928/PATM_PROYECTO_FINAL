package controlador;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.bitacora;
import modelo.rol;

@Path("/rol")
public class WSRol {

	@GET
	@Path("/listado/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<rol> getListado(@PathParam("idPer") int idPer, @PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			rol objR = new rol();
			return objR.getListaR();
		}
		return null;
	}

	@GET
	@Path("/ver/{idRol}/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public rol verEmpleado(@PathParam("idRol") int idRol, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			rol objR = new rol();
			objR.setRol_id(idRol);
			return objR.verRol();
		}
		return null;
	}

	@POST
	@Path("/insertar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public rol insEmpleado(rol objR, @PathParam("idPer") int idPer, @PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			objR.insRol();
			return objR;
		}
		return null;
	}

	@PUT
	@Path("/actualizar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public rol actEmpleado(rol objR, @PathParam("idPer") int idPer, @PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			objR.actRol();
			return objR;
		}
		return null;
	}

	@DELETE
	@Path("/borrar/{idRol}/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public rol delEmpleado(@PathParam("idRol") int idRol, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			rol objR = new rol();
			objR.setRol_id(idRol);
			objR.delRol();
			return objR;
		}

		return null;
	}

}
