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
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import modelo.ciclo;

@Path("/ciclo")
public class WSCiclo {

	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListado() {

		List<ciclo> ciclos = ciclo.getAllCiclo();

		if (ciclos != null) {
			String json = new Gson().toJson(ciclos);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}

		return Response.status(Response.Status.CREATED).entity("Error al consultar los datos").build();
	}

	@GET
	@Path("/ver/{idCiclo}")
	@Produces(MediaType.APPLICATION_JSON)
	public ciclo verCiclo(@PathParam("idCiclo") int idCiclo) {
		ciclo objP = new ciclo();
		objP.setPK_ID(idCiclo);
		return objP.verciclo();
	}

	@POST
	@Path("/insertar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insCiclo(ciclo objC) {
		objC.insCiclo();
		String json = new Gson().toJson(objC);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actCiclo(ciclo objC) {
		objC.actCiclo();
		String json = new Gson().toJson(objC);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	@DELETE
	@Path("/borrar/{idCiclo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delEmpleado(@PathParam("idCiclo") int idCiclo) {
		ciclo objC = new ciclo();
		objC.setPK_ID(idCiclo);
		objC.delCiclo();
		String json = new Gson().toJson(objC);
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

}
