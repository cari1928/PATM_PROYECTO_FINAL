package controlador;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
