/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.libwebapp.persistence;
import com.library.libwebapp.model.Libro;
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
public class LibroRepository {
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void save(Libro libro){
        if(libro.getId()==null){
            em.persist(libro);
        }
        else{
            //usa save anche per aggiornare
            em.merge(libro);
        }
    }
    @Transactional
    public void delete(Libro libro){
        em.remove(em.contains(libro) ? libro : em.merge(libro));
    }
    public Libro findById(Long id){
        return em.find(Libro.class, id);
    }
    public List<Libro> findAll(){
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }
    public List<Libro> findByName(String name){
        return em.createQuery("SELECT l FROM Libro l WHERE nome = :name", Libro.class).
                setParameter("name", name).getResultList();
    }
}
