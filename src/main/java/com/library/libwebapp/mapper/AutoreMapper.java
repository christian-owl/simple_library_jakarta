/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.libwebapp.mapper;

import com.library.libwebapp.dto.AutoreDTO;
import com.library.libwebapp.model.Autore;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author chris
 */
@ApplicationScoped
public class AutoreMapper {
    public AutoreDTO toDto(Autore autore){
        if(autore==null) return null;
        return new AutoreDTO(autore.getId(), autore.getNome(), autore.getCognome());
    }
    
    public Autore toEntity(AutoreDTO dto){
        if(dto==null) return null;
        Autore a = new Autore();
        a.setId(dto.id());
        a.setNome(dto.nome());
        a.setCognome(dto.cognome());
        return a;
    }
}
