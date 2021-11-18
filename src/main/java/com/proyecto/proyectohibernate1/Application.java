package com.proyecto.proyectohibernate1;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Application {

    public static void main(String[] args) throws SQLException {
        //ejemploRegistro();
        //ejemploLecturaDeTabla();
        ejemploLecturaDeTablaRelacionada();

        

    }
    
    public static void ejemploLecturaDeTablaRelacionada(){
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RegistroVenta> registrosV = null;
        Query consulta = entityManager.createQuery("SELECT r FROM RegistroVenta r");
        entityManager.getTransaction().begin();
        registrosV = consulta.getResultList();
        entityManager.getTransaction().commit();
        for(RegistroVenta r : registrosV)
            System.out.println(r.getCantidad() + ", " + r.getFecha_venta() + ", "
                    + r.getProductoRelaciondo().getCodigo() + ", "
                    + r.getProductoRelaciondo().getDescripcion());
        
    }
    public static void ejemploLecturaDeTabla(){
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Producto> productos = null;
        Query consulta = entityManager.createQuery("SELECT p FROM Producto p");//Producto es el nombre de la clase
        entityManager.getTransaction().begin();
        productos = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        //usandolos
        for(Producto pro : productos)
            System.out.println(pro.getCodigo() + ", " + pro.getDescripcion() + ", " +pro.getPrecio_unitario());
    }
    
    public static void ejemploRegistro(){
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
