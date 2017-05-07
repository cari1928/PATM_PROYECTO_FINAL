package controlador;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	public persona validar(
			@PathParam("usuario") String usuario, 
			@PathParam("password") String password) {
		persona objP = new persona();
		objP.setUsername(usuario);
		objP.setPass(password);
		objP.validaUsuario();
		return objP;
	}
	
	@GET
	@Path("/ver/{idPer}/{usr}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public persona verPersona(
			@PathParam("idPer") int idPer,
			@PathParam("usr") String usr,
			@PathParam("token") String token
			) 
	{
		
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);
		
		if(objB.validaToken()) {
			persona objP = new persona();
			objP.setPersona_id(idPer);
			return objP.verPersona();
		}
		return null;
	}

	@POST
	@Path("/insertar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public persona insPersona(
			persona objP, 
			@PathParam("idPer") int idPerB, 
			@PathParam("token") String tokenB) 
	{

		bitacora objB = new bitacora();
		objB.setPersona_id(idPerB);
		objB.setToken(tokenB);

		if (objB.validaToken()) {
			objP.insPersona();
			return objP;
		}
		return null;
	}

}
