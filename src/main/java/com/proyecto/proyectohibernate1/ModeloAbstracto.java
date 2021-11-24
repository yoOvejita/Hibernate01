/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.proyectohibernate1;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author rusok-pro
 */
public abstract class ModeloAbstracto<T>{
	private Class<T> entidad;
	protected EntityManager entityManager = new HerramientaConexion().getEntityManager();
	public ModeloAbstracto(Class<T> entidad){
		this.entidad = entidad;
	}
	//obtener todos los registros
	public List<T> obtenerTodosRegistros(){
		List<T> resultado =null;
                Query consulta = entityManager.createQuery("FROM " + entidad.getName());
		entityManager.getTransaction().begin();
        	resultado = consulta.getResultList();
	         entityManager.getTransaction().commit();
                return resultado;
	}
	//Obtener un solo registro
	public T obtenerRegistro(Object id){
		T resultado =null;
		entityManager.getTransaction().begin();
        		resultado = (T)entityManager.find(entidad, (Serializable) id);
	         entityManager.getTransaction().commit();
                 return resultado;
	}
	//Ingresar un registro
	public boolean registrar(T entidad){
		boolean resultado = true;
		entityManager.getTransaction().begin();
        	entityManager.persist(entidad);
        	entityManager.getTransaction().commit();
		return resultado;//en try y dentro el catch cambia a false
	}
	//actualizar un registro
	public boolean actualizar(T entidad){
		boolean resultado = true;
		entityManager.getTransaction().begin();
        	entityManager.refresh(entidad);
        	entityManager.getTransaction().commit();
		return resultado;//en try y dentro el catch cambia a false
	}
	//eliminar un registro
	public boolean eliminar(T entidad){
		boolean resultado = true;
		entityManager.getTransaction().begin();
        	entityManager.remove(entidad);
        	entityManager.getTransaction().commit();
		return resultado;//en try y dentro el catch cambia a false
	}
}
