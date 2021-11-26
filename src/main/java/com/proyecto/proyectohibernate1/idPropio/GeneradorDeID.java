/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyectohibernate1.idPropio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 *
 * @author rusok
 */
public class GeneradorDeID implements IdentifierGenerator{

    @Override
    public Serializable generate(SharedSessionContractImplementor ssci, Object o) throws HibernateException {
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-mm-ss");
            String cad = sdf.format(new Date());
            return cad;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
}
