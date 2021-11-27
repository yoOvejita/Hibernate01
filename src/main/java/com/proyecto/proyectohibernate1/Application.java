package com.proyecto.proyectohibernate1;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Application {

    public static void main(String[] args) throws SQLException {
        //ejemploRegistro();
        //ejemploLecturaDeTabla();
        //ejemploLecturaDeTablaRelacionada();
        
        //ejemploIntervalos(20f, 100f);
        //ejemploUsoLike("registro");
        //ejemploOrdenamiento(true);
        //ejemploOrdenamiento(false);
        
        //ejemploLeerUnRegistro(4);
        //ejemploDeLimiteDeRegistroa(3);
        //ejemploConsultaPorFecha(15, 8, 2020);
        //ejemploSumatoria();
        //ejemploCount();
        //ejemploMaxMin(true);
        //ejemploMedia();
        //ejemploProyeccion();
        
        //ejemploConsultaNativaYobjeto();
        //ejemploLlamadaProcedimientoA();
        
        //ejemploUsoModeloProducto();
        
        empleado e1 = new empleado();
        e1.setNombre("P");
        e1.setApellido(null);
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //el.setFecha(sdf.parse("26-11-2021"));
        ejemploUsoModeloEmpleado(e1);
        
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
        
        for(RegistroVenta r : registrosV){
            Set<RegistroVenta> temp = null;
            temp = r.getProductoRelaciondo().getRegistros();
            System.out.println(r.getProductoRelaciondo().getCodigo() + "******************");
            for(RegistroVenta rr : temp)
                System.out.println(rr.getIdregistro_venta()+", "+ rr.getCantidad());
        }
            
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

    private static void ejemploIntervalos(float minimo, float maximo) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Producto> prods = null;
        Query consulta = entityManager.createQuery("SELECT p FROM Producto p WHERE p.precio_unitario > :numMin AND p.precio_unitario < :numMax");
        consulta.setParameter("numMin", minimo);
        consulta.setParameter("numMax", maximo);
        entityManager.getTransaction().begin();
        prods = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        for(Producto pp : prods)
            System.out.println(pp.getCodigo() +": "+ pp.getDescripcion() + "("+pp.getPrecio_unitario()+ ")");
    }

    private static void ejemploUsoLike(String txt) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Producto> prods = null;
        Query consulta = entityManager.createQuery("SELECT p FROM Producto p WHERE p.descripcion LIKE :texto");
        consulta.setParameter("texto","%"+txt+"%" );
        entityManager.getTransaction().begin();
        prods = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        for(Producto pp : prods)
            System.out.println(pp.getCodigo() +": "+ pp.getDescripcion() + "("+pp.getPrecio_unitario()+ ")");
    }

    private static void ejemploOrdenamiento(boolean asc) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RegistroVenta> registros = null;
        Query consulta;
        if(asc){
            consulta = entityManager.createQuery("SELECT r FROM RegistroVenta r ORDER BY r.cantidad ASC");
        }else{
            consulta = entityManager.createQuery("SELECT r FROM RegistroVenta r ORDER BY r.cantidad DESC");
        }
        entityManager.getTransaction().begin();
        registros = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        for(RegistroVenta rr : registros)
            System.out.println(rr.getFecha_venta() + ", " + rr.getProductoRelaciondo().getCodigo() + " - " + rr.getCantidad());
        System.out.println("++++++++++++++++++++++++++++++++++");
    }

    private static void ejemploLeerUnRegistro(int idProducto) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Producto prod = null;
        Query consulta = entityManager.createQuery("SELECT p FROM Producto p WHERE p.idProducto = :id");
        consulta.setParameter("id", idProducto);
        entityManager.getTransaction().begin();
        prod = (Producto)consulta.getSingleResult();
        entityManager.getTransaction().commit();
        
        System.out.println(prod.getCodigo() +": "+ prod.getDescripcion() + ", "+prod.getPrecio_unitario());
    }

    private static void ejemploDeLimiteDeRegistroa(int cantRegistros) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Producto> prods = null;
        Query consulta = entityManager.createQuery("SELECT p FROM Producto p ORDER BY p.precio_unitario DESC");
        consulta.setMaxResults(cantRegistros);
        entityManager.getTransaction().begin();
        prods = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        for(Producto pp : prods)
            System.out.println(pp.getCodigo() +": "+ pp.getDescripcion() + "("+pp.getPrecio_unitario()+ ")");
    }

    private static void ejemploConsultaPorFecha(int d, int m, int a) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RegistroVenta> registros = null;
        Query consulta = entityManager.createQuery(
                "SELECT r FROM RegistroVenta r "
                + "WHERE day(fecha_venta) = :d AND"
                + " month(fecha_venta) = :m AND year(fecha_venta) = :a");
        consulta.setParameter("d", d);
        consulta.setParameter("m", m);
        consulta.setParameter("a", a);
        entityManager.getTransaction().begin();
        registros = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        for(RegistroVenta rr : registros)
            System.out.println(rr.getFecha_venta() + ", " + rr.getProductoRelaciondo().getCodigo() + " - " + rr.getCantidad());
        System.out.println("++++++++++++++++++++++++++++++++++");
    }

    private static void ejemploSumatoria() {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query consulta = entityManager.createQuery("SELECT SUM(r.cantidad) FROM RegistroVenta r");
        
        entityManager.getTransaction().begin();
        Long cant = (Long)consulta.getResultList().get(0);
        entityManager.getTransaction().commit();
        System.out.println("La sumatoria es " + cant);
    }

    private static void ejemploCount() {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query consulta = entityManager.createQuery("SELECT COUNT(r.idregistro_venta) FROM RegistroVenta r");
        
        entityManager.getTransaction().begin();
        Long cant = (Long)consulta.getResultList().get(0);
        entityManager.getTransaction().commit();
        System.out.println("La cantidad es " + cant);
    }

    private static void ejemploMaxMin(boolean esMax) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query consulta;
        if(esMax){
            consulta = entityManager.createQuery("SELECT MAX(p.precio_unitario) FROM Producto p");
        }else{
            consulta = entityManager.createQuery("SELECT MIN(p.precio_unitario) FROM Producto p");
        }
        
        
        entityManager.getTransaction().begin();
        Float cant = (Float)consulta.getResultList().get(0);
        entityManager.getTransaction().commit();
        System.out.println("El precio "+ (esMax?"maximo":"minimo")+" es " + cant);
    }

    private static void ejemploMedia() {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query consulta = entityManager.createQuery("SELECT AVG(p.precio_unitario) FROM Producto p");
        entityManager.getTransaction().begin();
        Double cant = (Double)consulta.getResultList().get(0);
        entityManager.getTransaction().commit();
        System.out.println("La media es " + cant);
    }

    private static void ejemploProyeccion() {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<ProductoMinimalista> prodsM = null;
        Query consulta = entityManager.createQuery("SELECT new com.proyecto.proyectohibernate1.ProductoMinimalista(p.codigo, p.precio_unitario) FROM Producto p");
        entityManager.getTransaction().begin();
        prodsM = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        for(ProductoMinimalista rr : prodsM)
            System.out.println(rr.getCodigo() + ", " + rr.getPrecio_unitario());
        System.out.println("++++++++++++++++++++++++++++++++++");
    }

    private static void ejemploConsultaNativaYobjeto() {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Object[]> resultado = null;
        Query consulta = entityManager.createNativeQuery("SELECT precio_unitario, SUM(precio_unitario), COUNT(precio_unitario) FROM producto GROUP BY producto.precio_unitario;");
        entityManager.getTransaction().begin();
        resultado = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        for(Object[] o : resultado){
            String elem1 = o[0].toString();
            String elem2 = o[1].toString();
            String elem3 = o[2].toString();
            float valor1 = Float.parseFloat(elem1);
            float valor2 = Float.parseFloat(elem2);
            long valor3 = Long.parseLong(elem3);
            System.out.println("valor 1: " + valor1 + ", " + valor2 + ", " + valor3);
        }
            
        System.out.println("++++++++++++++++++++++++++++++++++");
        
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        //Date date = formatter.parse(o[1].toString());
        
    }

    private static void ejemploLlamadaProcedimientoA() {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.com.fredericci.pu");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Producto> resultado = null;
        Query consulta = entityManager.createNativeQuery("{CALL simple_proc()}", Producto.class);
        entityManager.getTransaction().begin();
        resultado = consulta.getResultList();
        entityManager.getTransaction().commit();
        
        for(Producto o : resultado){
            
            System.out.println("valor 1: " + o.getCodigo() + ", " + o.getDescripcion());
        }
    }

    private static void ejemploUsoModeloProducto() {
        ProductoModelo proM = new ProductoModelo();
        List<Producto> p = proM.obtenerTodosRegistros();
        for(Producto pp : p)
            System.out.println(pp.getCodigo());
    }
    private static void ejemploUsoModeloEmpleado(empleado em) {
        EmpleadoModelo empM = new EmpleadoModelo();
        
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator v = vf.getValidator();
        Set<ConstraintViolation<empleado>> conjuntoDeFaltas = v.validate(em);
        if(!conjuntoDeFaltas.isEmpty()){
            for(ConstraintViolation<empleado> falta : conjuntoDeFaltas){
                System.out.println(falta.getPropertyPath()+": "+ falta.getMessage());
               
                
            }
        }else{
            empM.registrar(em);
        }
        

    }
}
