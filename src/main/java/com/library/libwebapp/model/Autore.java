/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.libwebapp.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris
 */
@Entity
@Table(name="autore")
public class Autore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    /* IDENTITY usa le colonne SERIAL di Postgres, che sono più efficienti e
        semplici da gestire rispetto alle SEQUENCE generate da AUTO */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nome", nullable = false)
    private String nome;
    @Column(name="cognome", nullable = false)
    private String cognome;
    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libri=new ArrayList<>();

    public Autore() {
    }

    public Autore(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }
    
    //helper per aggiungere un libro all'autore
    public void aggLibro(Libro libro){
        this.libri.add(libro);
        libro.setAutore(this);
    }
    
    //helper per rimuovere un libro dall'autore
    public void rimLibro(Libro libro){
        this.libri.remove(libro);
        libro.setAutore(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    
    @Transient // Indica a Hibernate di NON creare una colonna per questo metodo
    public int getNumLibri() {
        return libri != null ? libri.size() : 0;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autore)) {
            return false;
        }
        Autore other = (Autore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.library.libwebapp.model.Autore[ id=" + id + " ]";
    }
    
}
