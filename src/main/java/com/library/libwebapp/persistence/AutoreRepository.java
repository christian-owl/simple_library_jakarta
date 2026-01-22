/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.libwebapp.persistence;

import com.library.libwebapp.model.Autore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 *
 * @author chris
 */
@ApplicationScoped
public class AutoreRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void save(Autore autore){
        if(autore.getId()==null){
            em.persist(autore);
        }
        else{
            //usa save anche per aggiornare
            em.merge(autore);
        }
    }
    @Transactional
    public void delete(Autore autore){
        em.remove(em.contains(autore) ? autore : em.merge(autore));
    }
    public Autore findById(Long id){
        return em.find(Autore.class, id);
    }
    public Autore findBySurname(String surname){
        return em.find(Autore.class, surname);
    }
    public List<Autore> findAll(){
        return em.createQuery("SELECT a FROM Autore a", Autore.class).getResultList();
    }
}
