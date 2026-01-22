/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.libwebapp.mapper;

import com.library.libwebapp.dto.LibroDTO;
import com.library.libwebapp.model.Autore;
import com.library.libwebapp.model.Libro;
import com.library.libwebapp.persistence.AutoreRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;

/**
 *
 * @author chris
 */
@ApplicationScoped
public class LibroMapper {
    @Inject
    private AutoreRepository repo;
    
    public LibroDTO toDto(Libro libro){
        if(libro==null) return null;
        return new LibroDTO(libro.getId(), libro.getNome(), 
                libro.getPagine(), libro.getPrezzo(), libro.getAutore().getId());
    }
    
    public Libro toEntity(LibroDTO dto){
        if(dto==null) return null;
        Libro l = new Libro();
        l.setId(dto.id());
        l.setNome(dto.nome());
        l.setPagine(dto.pagine());
        l.setPrezzo(dto.prezzo());
        if(dto.autore()!=null){
            Autore a = repo.findById(dto.autore());
            if(a == null) throw new WebApplicationException("Autore non trovato", 400);
            l.setAutore(a);
        }
        return l;
    }
}
