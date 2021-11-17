/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyectohibernate1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author rusok-pro
 */
@Entity
public class Producto {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private int idProducto;
      private String descripcion;
      private String codigo;
      private float precio_unitario;

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

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
      
}