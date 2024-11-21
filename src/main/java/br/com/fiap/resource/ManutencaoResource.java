package br.com.fiap.resource;

import br.com.fiap.bo.ManutencaoBO;
import br.com.fiap.exceptions.*;
import br.com.fiap.to.AvaliacaoTO;
import br.com.fiap.to.ManutencaoTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/manutencao")
public class ManutencaoResource {
    private ManutencaoBO manutencaoBO = new ManutencaoBO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<ManutencaoTO> resultado = manutencaoBO.findAll();
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
    @Path("/{id_manutencao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_manutencao") Long idManutencao) throws ManutencaoNotFoundException, ManutencaoInvalidIdException {
        ManutencaoTO resultado = manutencaoBO.findByCodigo(idManutencao);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        }else {
            response = Response.status(404);
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/energia/{id_energia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByIdEnergia(@PathParam("id_energia") Long idEnergia) {
        try {
            ArrayList<ManutencaoTO> manutencoes = manutencaoBO.findByIdEnergia(idEnergia);
            if (manutencoes == null || manutencoes.isEmpty()) {
                return Response.status(404).entity("Nenhuma manutenção encontrada para o ID de energia.").build();
            }
            return Response.ok(manutencoes).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Erro interno no servidor: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ManutencaoTO manutencao) {
        try {
            if (manutencao.getIdEnergia() == null) {
                return Response.status(400).entity("ID do sistema de energia é obrigatório.").build();
            }
            ManutencaoTO resultado = manutencaoBO.save(manutencao, manutencao.getIdEnergia());
            return Response.status(201).entity(resultado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Erro interno no servidor.").build();
        }
    }

    @DELETE
    @Path("/{id_manutencao}")
    public Response delete(@PathParam("id_manutencao") Long idManutencao) throws ManutencaoDeleteException {
        Response.ResponseBuilder response = null;
        if (manutencaoBO.delete(idManutencao)) {
            response = Response.status(204);  // 204  NO CONTENT
        } else {
            response = Response.status(404);  // 404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id_manutencao}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid ManutencaoTO manutencao, @PathParam("id_manutencao") Long idManutencao) throws ManutencaoUpdateException {
        manutencao.setIdManutencao(idManutencao);
        ManutencaoTO resultado = manutencaoBO.update(manutencao);
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
