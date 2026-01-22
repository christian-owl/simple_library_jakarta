/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.libwebapp.resources;

import com.library.libwebapp.dto.LibroDTO;
import com.library.libwebapp.mapper.LibroMapper;
import com.library.libwebapp.model.Libro;
import com.library.libwebapp.persistence.LibroRepository;
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
@Path("/libri")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibroResource {
    @Inject
    private LibroRepository repo;
    @Inject
    private LibroMapper mapper;
    
    @GET
    public List<LibroDTO> trovaLibri(){
        return repo.findAll().stream().map(mapper::toDto).toList();
    }
    
    @GET
    @Path("/{id:[0-9]+}")
    public Response trovaLibroId(@PathParam("id") Long id){
        Libro l = repo.findById(id);
        if(l == null) return Response.status(404).build();
        LibroDTO libro = mapper.toDto(l);
        return Response.ok(libro).build();
    }
    
    @GET
    @Path("/cerca/{nome}")
    public List<LibroDTO> trovaLibroNome(@PathParam("nome") String nome){
        return repo.findByName(nome).stream().map(mapper::toDto).toList();
    }
    
    @POST
    public Response creaLibro(LibroDTO libro){
        if(libro == null) return Response.status(404).build();
        Libro l = mapper.toEntity(libro);
        repo.save(l);
        return Response.status(Response.Status.CREATED).
                entity(mapper.toDto(l)).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response cancellaLibro(@PathParam("id") Long id){
        Libro l = repo.findById(id);
        if (l == null) return Response.status(404).build();
        repo.delete(l);
        return Response.noContent().build();
    }
    
    @PUT
    public Response aggiornaLibro(LibroDTO libro){
        if(libro.id()==null || repo.findById(libro.id()) == null){
            return Response.status(404).build();
        }
        Libro l = mapper.toEntity(libro);
        repo.save(l);
        return Response.ok(libro).build();
    }
}
