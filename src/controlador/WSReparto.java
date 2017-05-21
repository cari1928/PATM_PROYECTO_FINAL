package controlador;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.reparto;

@Path("/reparto")
public class WSReparto {

	/**
	 * Para la app, muestra repartos con restricción de tiempo
	 * 
	 * @return
	 */
	@GET
	@Path("/listado/app")
	@Produces(MediaType.APPLICATION_JSON)
	public List<reparto> getListadoApp() {
		reparto objR = new reparto();
		return objR.verListaRApp();
	}

	/**
	 * Para la app, muestra repartos sin restricción de tiempo
	 * 
	 * @return
	 */
	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public List<reparto> getListado() {
		reparto objR = new reparto();
		return objR.verListaR();
	}

	/**
	 * Para la app, muestra un reparto con restricción de tiempo
	 * 
	 * @param idPeli
	 * @return
	 */
	@GET
	@Path("/ver/app/{idPeli}")
	@Produces(MediaType.APPLICATION_JSON)
	public reparto verRepApp(@PathParam("idPeli") int idPeli) {
		reparto objR = new reparto();
		objR.setPelicula_id(idPeli);
		return objR.verRepartoApp();
	}

	/**
	 * Para la app, muestra una categoría SIN restricción de tiempo
	 * 
	 * @param idPeli
	 * @return
	 */
	@GET
	@Path("/ver/{idPeli}")
	@Produces(MediaType.APPLICATION_JSON)
	public reparto verReparto(@PathParam("idPeli") int idPeli) {
		reparto objR = new reparto();
		objR.setPelicula_id(idPeli);
		return objR.verReparto();
	}

}
