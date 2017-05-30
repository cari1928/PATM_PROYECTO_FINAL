package controlador;

import java.util.ArrayList;
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
import modelo.privilegio;

@Path("/privilegio")
public class WSPrivilegio {

	@GET
	@Path("/listado/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<privilegio> getListado(@PathParam("idPer") int idPer, @PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			privilegio objP = new privilegio();
			return objP.getListaP();
		}
		return null;
	}

	/**
	 * Obtiene los roles de una persona en base a persona_id Usado por
	 * cineMaster
	 * 
	 * @param idPer
	 * @param token
	 * @return
	 */
	@GET
	@Path("/ver/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<privilegio> verEmpleado(@PathParam("idPer") int idPer, @PathParam("token") String token) {

		List<privilegio> arrP;
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		privilegio objP = new privilegio();
		if (objB.validaToken()) {
			objP.setPersona_id(idPer);
			return objP.verPrivilegio();
		}

		objP.setMensaje("ERROR-TOKEN-NO-VALIDO");
		arrP = new ArrayList<>();
		arrP.add(objP);
		return arrP;
	}

	@POST
	@Path("/insertar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public privilegio insEmpleado(privilegio objP, @PathParam("idPer") int idPer, @PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			objP.insPrivilegio();
			return objP;
		}
		return null;
	}

	@PUT
	@Path("/actualizar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public privilegio actEmpleado(privilegio objP, @PathParam("idPer") int idPer, @PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			objP.actPrivilegio();
			return objP;
		}
		return null;
	}

	@DELETE
	@Path("/borrar/{idPriv}/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public privilegio delEmpleado(@PathParam("idPriv") int idPriv, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			privilegio objP = new privilegio();
			objP.setPrivilegio_id(idPriv);
			objP.delPrivilegio();
			return objP;
		}

		return null;
	}

}
