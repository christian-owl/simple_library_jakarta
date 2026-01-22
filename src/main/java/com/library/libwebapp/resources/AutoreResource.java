/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.libwebapp.resources;

import com.library.libwebapp.dto.AutoreDTO;
import com.library.libwebapp.mapper.AutoreMapper;
import com.library.libwebapp.model.Autore;
import com.library.libwebapp.persistence.AutoreRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author chris
 */
@Path("/autori")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutoreResource {
    @Inject
    private AutoreRepository repo;
    @Inject
    private AutoreMapper mapper;
    
    @GET
    public List<AutoreDTO> listaAutori(){
        return repo.findAll().stream().map(mapper::toDto).toList();
    }
    
    @GET
    @Path("/{id:[0-9]+}") //indica che sono solo numeri
    public Response trovaAutoreId(@PathParam("id") Long id){
        Autore a = repo.findById(id);
        if(a==null) return Response.status(404).build();
        AutoreDTO dto = mapper.toDto(a);
        return Response.ok(dto).build();
    }
    
    @GET
    @Path("/cerca/{cognome}")
    public Response trovaAutoreCognome(@PathParam("cognome") String cognome){
        Autore a = repo.findBySurname(cognome);
        if(a==null) return Response.status(404).build();
        AutoreDTO dto = mapper.toDto(a);
        return Response.ok(dto).build();
    }
    
    @POST
    public Response registraAutore(AutoreDTO autore){
        if(autore == null) return Response.status(404).build();
        Autore a = mapper.toEntity(autore);
        repo.save(a);
        return Response.status(Response.Status.CREATED).
                entity(mapper.toDto(a)).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response eliminaAutore(@PathParam("id") Long id){
        Autore a = repo.findById(id);
        if (a == null) return Response.status(404).build();
        repo.delete(a);
        return Response.noContent().build(); //status 204
    }
    
    @PUT
    public Response aggiornaAutore(AutoreDTO autore){
        if (autore.id() == null || repo.findById(autore.id()) == null) {
            return Response.status(404).build();
        }
        Autore a = mapper.toEntity(autore);
        repo.save(a); //fa l'update grazie al merge interno
        return Response.ok(mapper.toDto(a)).build();
    }
}
