package com.proyecto.proyectohibernate1;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Application {

    public static void main(String[] args) throws SQLException {
        
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        Producto pro = new Producto();
        pro.setCodigo("PEPE");
        pro.setDescripcion("Ejemplo desde Hibernate");
        pro.setPrecio_unitario(99.9f);

        entityManager.getTransaction().begin();
        entityManager.persist(pro);
        entityManager.getTransaction().commit();

        System.out.println("EXITO");

    }

    
}
