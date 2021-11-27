/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyectohibernate1;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author rusok
 */
@Entity
public class empleado implements Serializable{
    @Id
    @GenericGenerator(name= "miGenerador", strategy="com.proyecto.proyectohibernate1.idPropio.GeneradorDeID")
    @GeneratedValue(generator = "miGenerador")
    private String id;
    @Length(min = 3, max = 200)
    @URL
    private String nombre;
    @NotNull
    @Email
    private String apellido;

    public empleado() {
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
}
