/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.libwebapp.dto;

import java.math.BigDecimal;

/**
 *
 * @author chris
 */
public record LibroDTO(Long id, String nome, int pagine, BigDecimal prezzo, Long autore){}