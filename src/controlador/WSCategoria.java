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
import modelo.categoria;

@Path("/categoria")
public class WSCategoria {

	/**
	 * Para la app, muestra categorías con restricción de tiempo
	 * 
	 * @return
	 */
	@GET
	@Path("/listado/app")
	@Produces(MediaType.APPLICATION_JSON)
	public List<categoria> getListadoApp() {
		categoria objC = new categoria();
		return objC.getListaCApp();
	}

	/**
	 * Para la app, muestra categorías sin restricción de tiempo
	 * 
	 * @return
	 */
	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public List<categoria> getListado() {
		categoria objC = new categoria();
		return objC.getListaC();
	}

	/**
	 * Para la app, muestra una categoría con restricción de tiempo
	 * 
	 * @param idCat
	 * @return
	 */
	@GET
	@Path("/ver/app/{idCat}")
	@Produces(MediaType.APPLICATION_JSON)
	public categoria verCatApp(@PathParam("idCat") int idCat) {
		categoria objC = new categoria();
		objC.setCategoria_id(idCat);
		return objC.verCatApp();
	}

	/**
	 * Para la app, muestra una categoría SIN restricción de tiempo
	 * 
	 * @param idCat
	 * @return
	 */
	@GET
	@Path("/ver/{idCat}")
	@Produces(MediaType.APPLICATION_JSON)
	public categoria verCategoria(@PathParam("idCat") int idCat) {
		categoria objC = new categoria();
		objC.setCategoria_id(idCat);
		return objC.verCategoria();
	}

	@POST
	@Path("/insertar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public categoria insCategoria(categoria objC, @PathParam("idPer") int idPer, @PathParam("token") String token) {
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);
		if (objB.validaToken()) {
			objC.insCategoria();
			return objC;
		}
		objC.setStatus("Error token no valido");
		return objC;
	}

	@PUT
	@Path("/actualizar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public categoria actCategoria(categoria objC, @PathParam("idPer") int idPer, @PathParam("token") String token) {
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);
		if (objB.validaToken()) {
			objC.actCategoria();
			return objC;
		}
		objC.setStatus("Error token no valido");
		return null;
	}

	@DELETE
	@Path("/borrar/{idCat}/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public categoria delCategoria(@PathParam("idCat") int idCat, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);
		categoria objC = new categoria();
		if (objB.validaToken()) {
			objC.setCategoria_id(idCat);
			objC.delCategoria();
			return objC;
		}
		objC.setStatus("Error token no valido");
		return null;
	}

}
