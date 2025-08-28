package br.com.fiap.resource;

import br.com.fiap.bo.AnaliseEficienciaBO;
import br.com.fiap.to.AnaliseEficienciaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/analise")
public class AnaliseEficienciaResource {
    private AnaliseEficienciaBO analiseEficienciaBO = new AnaliseEficienciaBO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            ArrayList<AnaliseEficienciaTO> resultado = analiseEficienciaBO.findAll();
            if (resultado == null) resultado = new ArrayList<>();
            return Response.ok(resultado).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity("Erro interno: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id_analise}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_analise") Long idAnalise) {
        AnaliseEficienciaTO resultado = analiseEficienciaBO.findByCodigo(idAnalise);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();
        } else {
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
            ArrayList<AnaliseEficienciaTO> analises = analiseEficienciaBO.findByIdEnergia(idEnergia);

            if (analises != null && !analises.isEmpty()) {
                return Response.ok(analises).build(); // HTTP 200 com os dados
            } else {
                return Response.status(404).entity("Nenhuma análise encontrada para este ID de energia").build(); // HTTP 404
            }
        } catch (Exception e) {
            System.out.println("Erro no endpoint: " + e.getMessage());
            e.printStackTrace();
            return Response.status(500).entity("Erro interno no servidor.").build(); // HTTP 500
        }
    }


    @GET
    @Path("/resumo/{id_analise}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response obterResumoAnalise(@PathParam("id_analise") Long idAnalise) {
        AnaliseEficienciaTO analise = analiseEficienciaBO.findByCodigo(idAnalise);

        // Se a análise não for encontrada, retorna 404
        if (analise == null) {
            return Response.status(404).entity("Análise não encontrada").build();
        }

        String resumo = analiseEficienciaBO.gerarResumoAnalise(analise);

        return Response.ok(resumo).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(AnaliseEficienciaTO analise) {
        try {
            if (analise.getIdEnergia() == null) {
                return Response.status(400).entity("O ID do sistema de energia é obrigatório.").build();
            }

            AnaliseEficienciaTO resultado = analiseEficienciaBO.save(analise, analise.getIdEnergia());
            if (resultado != null) {
                return Response.status(201).entity(resultado).build();
            } else {
                return Response.status(400).entity("Erro ao salvar a análise.").build();
            }
        } catch (Exception e) {
            System.out.println("Erro no endpoint: " + e.getMessage());
            e.printStackTrace();
            return Response.status(500).entity("Erro interno no servidor.").build();
        }
    }





    @DELETE
    @Path("/{id_analise}")
    public Response delete(@PathParam("id_analise") Long idAnalise) {
        Response.ResponseBuilder response = null;
        if (analiseEficienciaBO.delete(idAnalise)) {
            response = Response.status(204);  // 204  NO CONTENT
        } else {
            response = Response.status(404);  // 404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id_analise}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid AnaliseEficienciaTO analise, @PathParam("id_analise") Long idAnalise) {
        analise.setIdAnalise(idAnalise);
        AnaliseEficienciaTO resultado = analiseEficienciaBO.update(analise);
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
