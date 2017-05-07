package controlador;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.rol;

@Path("/rol")
public class RolService {

	@GET
	@Path("/get/{idRol}")
	@Produces(MediaType.APPLICATION_JSON)
	public rol getUsuario(@PathParam("idRol") String idRol) {
		rol objD = new rol();
		objD.setIdRol(Integer.parseInt(idRol));
		objD.getARol();
		return objD;
	}
}
