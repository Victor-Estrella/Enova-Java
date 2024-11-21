package br.com.fiap.resource;

import br.com.fiap.bo.AvaliacaoBO;
import br.com.fiap.to.AnaliseEficienciaTO;
import br.com.fiap.to.AvaliacaoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/avaliacao")
public class AvaliacaoResource {
    private AvaliacaoBO avaliacaoBO = new AvaliacaoBO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<AvaliacaoTO> resultado = avaliacaoBO.findAll();
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
    @Path("/{id_avaliacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_avaliacao") Long idAvaliacao){
        AvaliacaoTO resultado = avaliacaoBO.findByCodigo(idAvaliacao);
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
    public Response save(@Valid AvaliacaoTO avaliacao){
        AvaliacaoTO resultado = avaliacaoBO.save(avaliacao);
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
    @Path("/{id_avaliacao}")
    public Response delete(@PathParam("id_avaliacao") Long idAvaliacao) {
        Response.ResponseBuilder response = null;
        if (avaliacaoBO.delete(idAvaliacao)) {
            response = Response.status(204);  // 204  NO CONTENT
        } else {
            response = Response.status(404);  // 404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id_avaliacao}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid AvaliacaoTO avaliacao, @PathParam("id_avaliacao") Long idAvaliacao) {
        avaliacao.setIdAvaliacao(idAvaliacao);
        AvaliacaoTO resultado = avaliacaoBO.update(avaliacao);
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
