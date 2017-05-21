package controlador;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import modelo.categoria;
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

}
