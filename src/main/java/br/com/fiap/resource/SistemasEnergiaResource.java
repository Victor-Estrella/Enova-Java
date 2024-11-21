package br.com.fiap.resource;

import br.com.fiap.bo.SistemasEnergiaBO;
import br.com.fiap.to.ManutencaoTO;
import br.com.fiap.to.SistemasEnergiaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/sistema")
public class SistemasEnergiaResource {
    private SistemasEnergiaBO sistemasEnergiaBO = new SistemasEnergiaBO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<SistemasEnergiaTO> resultado = sistemasEnergiaBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok(); // 200 (Ok)
        } else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }
    @GET
    @Path("/{id_energia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_energia") Long idEnergia){
        SistemasEnergiaTO resultado = sistemasEnergiaBO.findByCodigo(idEnergia);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        }else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid SistemasEnergiaTO sistema){
        SistemasEnergiaTO resultado = sistemasEnergiaBO.save(sistema);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);
        }else {
            response = Response.status(400);
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id_energia}")
    public Response delete(@PathParam("id_energia") Long idEnergia) {
        Response.ResponseBuilder response = null;
        if (sistemasEnergiaBO.delete(idEnergia)) {
            response = Response.status(204);  // 204  NO CONTENT
        } else {
            response = Response.status(404);  // 404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id_energia}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid SistemasEnergiaTO sistema, @PathParam("id_energia") Long idEnergia) {
        sistema.setIdEnergia(idEnergia);
        SistemasEnergiaTO resultado = sistemasEnergiaBO.update(sistema);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);  // 201 CREATED
        } else {
            response = Response.status(400);  // 400 BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }
}
