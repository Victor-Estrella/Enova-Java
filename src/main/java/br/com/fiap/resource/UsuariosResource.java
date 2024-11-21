package br.com.fiap.resource;

import br.com.fiap.bo.UsuariosBO;
import br.com.fiap.to.UsuariosTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/usuario")
public class UsuariosResource {
    private UsuariosBO usuariosBO = new UsuariosBO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<UsuariosTO> resultado = usuariosBO.findAll();
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
    @Path("/{id_usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_usuario") Long idUsuario){
        UsuariosTO resultado = usuariosBO.findByCodigo(idUsuario);
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
    @Path("/validar-email/{id_usuario}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response validarEmail(@PathParam("id_usuario") Long idUsuario) {
        // Valida o e-mail do usuário
        boolean emailValido = usuariosBO.isEmailValido(idUsuario);

        if (emailValido) {
            return Response.ok("E-mail válido!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("E-mail inválido.")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuariosTO usuario){
        UsuariosTO resultado = usuariosBO.save(usuario);
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
    @Path("/{id_usuario}")
    public Response delete(@PathParam("id_usuario") Long idUsuario) {
        Response.ResponseBuilder response = null;
        if (usuariosBO.delete(idUsuario)) {
            response = Response.status(204);  // 204  NO CONTENT
        } else {
            response = Response.status(404);  // 404 NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id_usuario}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid UsuariosTO usuario, @PathParam("id_usuario") Long idUsuario) {
        usuario.setIdUsuario(idUsuario);
        UsuariosTO resultado = usuariosBO.update(usuario);
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
