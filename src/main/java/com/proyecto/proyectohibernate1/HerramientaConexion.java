/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyectohibernate1;

/**
 *
 * @author rusok-pro
 */


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

 public class HerramientaConexion{
	private static EntityManager entityManager;
	public HerramientaConexion(){
            try{
			final EntityManagerFactory entityManagerFactory = 	
                                Persistence.createEntityManagerFactory("br.com.fredericci.pu");
			entityManager = entityManagerFactory.createEntityManager();
		}catch(Exception e){
                    e.printStackTrace();
                }
        }
		
	
	public static EntityManager getEntityManager(){
		return entityManager;
	}
}