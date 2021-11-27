/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyectohibernate1;

import jakarta.validation.constraints.Past;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author rusok-pro
 */
@Entity
@Table(name = "registro_venta")
public class RegistroVenta {//Producto 1 ----- n RegistroVenta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idregistro_venta;
    //@Column(name = "fecha_venta")
    @Past
    private Date fecha_venta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprod", nullable = false)
    private Producto productoRelaciondo;
    private int cantidad;

    public int getIdregistro_venta() {
        return idregistro_venta;
    }

    public void setIdregistro_venta(int idregistro_venta) {
        this.idregistro_venta = idregistro_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Producto getProductoRelaciondo() {
        return productoRelaciondo;
    }

    public void setProductoRelaciondo(Producto productoRelaciondo) {
        this.productoRelaciondo = productoRelaciondo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
