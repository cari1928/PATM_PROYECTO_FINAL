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
import modelo.categoria_pelicula;

@Path("/categoria_pelicula")
public class WSCategoriaPelicula {

	/**
	 * Para la app, muestra categorías-películas con restricción de tiempo
	 * 
	 * @return
	 */
	@GET
	@Path("/listado/app")
	@Produces(MediaType.APPLICATION_JSON)
	public List<categoria_pelicula> getListadoApp() {
		categoria_pelicula objC = new categoria_pelicula();
		return objC.verListaCApp();
	}

	/**
	 * Para la app, muestra categorías-películas sin restricción de tiempo
	 * 
	 * @return
	 */
	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public List<categoria_pelicula> getListado() {
		categoria_pelicula objC = new categoria_pelicula();
		return objC.verListaCP();
	}

	/**
	 * Listado de todas las categorias
	 * 
	 * @return
	 */
	@GET
	@Path("/listado/full/{idPer}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<categoria_pelicula> getListadoFull(@PathParam("idPer") int idPer, @PathParam("token") String token) {
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);
		categoria_pelicula objC = new categoria_pelicula();
		if (objB.validaToken()) {
			return objC.verFullListado();
		}
		return null;
	}

	@POST
	@Path("/insertar/{idPer}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public categoria_pelicula insCategoria(categoria_pelicula objC, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {
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
	public categoria_pelicula actCategoria(categoria_pelicula objC, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {
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
	public categoria_pelicula delCategoria(@PathParam("idCat") int idCat, @PathParam("idPer") int idPer,
			@PathParam("token") String token) {
		bitacora objB = new bitacora();
		objB.setPersona_id(idPer);
		objB.setToken(token);
		categoria_pelicula objC = new categoria_pelicula();
		if (objB.validaToken()) {
			objC.setCategoria_pelicula_id(idCat);
			objC.delCategoria();
			return objC;
		}
		objC.setStatus("Error token no valido");
		return null;
	}

}
