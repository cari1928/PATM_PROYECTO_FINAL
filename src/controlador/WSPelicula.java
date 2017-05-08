package controlador;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.bitacora;
import modelo.pelicula;

@Path("/pelicula")
public class WSPelicula {

	@GET
	@Path("/listado/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<pelicula> getListado(@PathParam("idPer") int idPer, @PathParam("token") String token) {
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			pelicula objP = new pelicula();
			return objP.verListaP();
		}

		return null;
	}

	@GET
	@Path("/ver/{idPeli}/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public pelicula verPelicula(@PathParam("idPeli") int idPeli, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {

		pelicula objP = new pelicula();
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);

		if (objB.validaToken()) {
			objP.setPelicula_id(idPeli);
			return objP.verPelicula();
		}

		objP.setStatus("ERROR-VALIDACION-TOKEN");
		return objP;
	}

}
