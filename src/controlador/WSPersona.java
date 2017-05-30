package controlador;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.bitacora;
import modelo.persona;

//ubica a la clase
@Path("/persona")
public class WSPersona {

	@GET
	@Path("/validar/{usuario}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public persona validar(@PathParam("usuario") String usuario, @PathParam("password") String password) {
		persona objP = new persona();
		objP.setUsername(usuario);
		objP.setPass(password);
		objP.validaUsuario();
		return objP;
	}

	/**
	 * Obtiene una persona
	 * 
	 * @param idPer
	 * @param token
	 * @return
	 */
	@GET
	@Path("/verp/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public persona verPersonaById(@PathParam("idPer") int idPer, @PathParam("token") String token) {
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);
		persona objP = new persona();
		if (objB.validaToken()) {
			objP.setPersona_id(idPer);
			return objP.verPersonaById();
		}
		objP.setStatus("ERROR-TOKEN");
		return objP;
	}

	/**
	 * Obtiene un cliente en específico
	 * 
	 * @param idPer
	 * @param token
	 * @return
	 */
	@GET
	@Path("/ver/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public persona verPersona(@PathParam("idPer") int idPer, @PathParam("token") String token) {

		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			persona objP = new persona();
			objP.setPersona_id(idPer);
			return objP.verPersona();
		}
		return null;
	}

	/**
	 * No necesito verificar el token porque esto será usado cuando el cliente
	 * se registre desde la app
	 * 
	 * @param objP
	 * @return
	 */
	@POST
	@Path("/insertar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public persona insPersona(persona objP) {
		objP.insPersona();
		return objP;
	}

	@PUT
	@Path("/actualizar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public persona actPersona(persona objP, @PathParam("idPer") int idPer, @PathParam("token") String token) {
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			objP.actPersona();
			return objP;
		}
		return null;
	}

}
