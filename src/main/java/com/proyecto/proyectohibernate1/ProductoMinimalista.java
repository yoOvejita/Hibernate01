/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyectohibernate1;

/**
 *
 * @author rusok-pro
 */
public class ProductoMinimalista {
    private String codigo;
      private float precio_unitario;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public ProductoMinimalista(String codigo, float precio_unitario) {
        this.codigo = codigo;
        this.precio_unitario = precio_unitario;
    }

    public ProductoMinimalista() {
    }
      
}
