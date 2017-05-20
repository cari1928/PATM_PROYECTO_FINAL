package controlador;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.categoria;
import modelo.colaborador;

@Path("/colaborador")
public class WSColaborador {

	/**
	 * App, muestra colaboradores con restricción de tiempo
	 * 
	 * @return
	 */
	@GET
	@Path("/listado/app")
	@Produces(MediaType.APPLICATION_JSON)
	public List<colaborador> getListApp() {
		colaborador objC = new colaborador();
		return objC.getListaCApp();
	}

	/**
	 * App, muestra colaboradores sin restricción de tiempo
	 * 
	 * @return
	 */
	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public List<colaborador> getListC() {
		colaborador objC = new colaborador();
		return objC.getListaC();
	}

	/**
	 * App, muestra un colaborador con restricción de tiempo
	 * 
	 * @param idCol
	 * @return
	 */
	@GET
	@Path("/ver/app/{idCol}")
	@Produces(MediaType.APPLICATION_JSON)
	public colaborador verCatApp(@PathParam("idCol") int idCol) {
		colaborador objC = new colaborador();
		objC.setColaborador_id(idCol);
		return objC.verColApp();
	}

	/**
	 * Para la app, muestra un colaborador SIN restricción de tiempo
	 * 
	 * @param idCol
	 * @return
	 */
	@GET
	@Path("/ver/{idCol}")
	@Produces(MediaType.APPLICATION_JSON)
	public colaborador verCategoria(@PathParam("idCol") int idCol) {
		colaborador objC = new colaborador();
		objC.setColaborador_id(idCol);
		return objC.verColaborador();
	}

}
