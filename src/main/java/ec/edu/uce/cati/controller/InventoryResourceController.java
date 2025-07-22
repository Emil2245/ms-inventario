package ec.edu.uce.cati.controller;


import ec.edu.uce.cati.repository.dto.IntellectualPropertyPublicDTO;
import ec.edu.uce.cati.service.InventoryService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;


@Path("/inventory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InventoryResourceController {

    @Inject
    InventoryService inventoryService;

    /**
     * Endpoint reactivo que devuelve un stream de propiedades.
     * Quarkus manejará el Multi y lo enviará al cliente como un JSON array.
     */
    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Multi<IntellectualPropertyPublicDTO> getAll(@QueryParam("type") String type) {
        if (type != null && !type.isBlank()) {
            return inventoryService.findByType(type);
        }
        return inventoryService.getAll();
    }

    /**
     * Endpoint reactivo que devuelve una propiedad o un 404.
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> getById(@PathParam("id") String id) {
        return inventoryService.getById(id)
                .onItem().transform(ip -> Response.ok(ip).build())
                // Si el Uni falla (e.g., con NotFoundException), esta función se ejecuta.
                .onFailure().recoverWithItem(failure -> {
                    if (failure instanceof NotFoundException) {
                        return Response.status(Response.Status.NOT_FOUND).build();
                    }
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(failure.getMessage()).build();
                });
    }

    /**
     * Endpoint reactivo para crear una propiedad.
     */
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> create(IntellectualPropertyPublicDTO ip) {
        return inventoryService.create(ip)
                .onItem().transform(createdIp ->
                        Response.created(URI.create("/inventory/" + createdIp.id)).entity(createdIp).build()
                )
                .onFailure().recoverWithItem(failure -> {
                    if (failure instanceof IllegalArgumentException) {
                        return Response.status(Response.Status.BAD_REQUEST).entity(failure.getMessage()).build();
                    }
                    return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                });
    }

    /**
     * Endpoint reactivo para eliminar una propiedad.
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> delete(@PathParam("id") String id) {
        return inventoryService.deleteById(id)
                .onItem().transform(deleted -> deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build());
    }
}